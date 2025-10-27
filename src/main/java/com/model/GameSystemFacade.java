
package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSystemFacade {

    private User currentUser;

    public GameSystemFacade() {}

    public User signup(String username, String password) {
        Users users = Users.getInstance();
        User newUser = new User(username, password);
        if (users.addUser(newUser)) {
            users.saveUsers();
            this.currentUser = newUser;
            return newUser;
        }
        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void startNewGame(Room room) {
        if (currentUser == null) {
            throw new IllegalStateException("No user is currently logged in.");
        }

        // Assuming Room has getRoomID() that returns an int
        int roomId = room.getRoomID();

        currentUser.setCurrentGame(roomId);
        currentUser.setCurrentRoom(roomId);
        currentUser.setLastPuzzle(0);

        Users.getInstance().saveUsers();
    }

    public Room getRoom() {
        List<Room> rooms = DataLoader.getRooms();
        return (rooms == null || rooms.isEmpty()) ? null : rooms.get(0);
    }

    public Room getRoomByID(int roomId) {
        List<Room> rooms = DataLoader.getRooms();
        if (rooms == null) return null;
        for (Room r : rooms) {
            if (r != null && r.getRoomID() == roomId) {
                return r;
            }
        }
        return null;
    }

    public Puzzle getPuzzle(Integer puzzleID) {
        if (puzzleID == null) return null;
        
        List<Room> rooms = DataLoader.getRooms();
        if (rooms == null) return null;

        for (Room r : rooms) {
            if (r == null) continue;
            List<Puzzle> puzzles = r.getPuzzles();
            if (puzzles == null) continue;

            for (Puzzle p : puzzles) {
                if (p != null && Integer.valueOf(p.getPuzzleID()).equals(puzzleID)) {
                    return p;
                }
            }
        }

        return null;
    }

    public boolean answerPuzzle(int puzzleID, String userAnswer) {
        Puzzle puzzle = getPuzzle(puzzleID);
        if (puzzle == null) {
            return false;
        }

        if (currentUser == null) {
            return false;
        }

        // check requirements...
        List<String> reqs = puzzle.getRequiredItems();
        if (reqs != null && !reqs.isEmpty()) {
            List<String> userItems = currentUser.getItems();
            if (userItems == null) userItems = new ArrayList<>();
            for (String r : reqs) {
                if (!userItems.contains(r)) {
                    return false;
                }
            }
        }

        boolean correct = puzzle.attempt(userAnswer);

        if (correct) {
            // record puzzle as completed on user (avoid duplicates)
            List<Integer> completed = currentUser.getPuzzlesComplete();
            if (completed == null) {
                completed = new ArrayList<>();

            }
            if (!completed.contains(puzzleID)) {
                completed.add(puzzleID);
            }
            currentUser.setLastPuzzle(puzzleID);

            // grant attached item to current user (once)
            String item = puzzle.getItem();
            if (item != null && !item.isBlank()) {
                if (!currentUser.getItems().contains(item)) {
                    currentUser.addItem(item);
                    puzzle.grantItem(item);
                }
            }

            // persist user changes so json shows progress/items
            Users.getInstance().saveUsers();
        }

        return correct;
    }

    public String getHint(int puzzleID) {
        Puzzle puzzle = getPuzzle(puzzleID);
        if (puzzle == null) return null;

        List<String> hints = puzzle.getHints();
        if (hints == null || hints.isEmpty()) return null;

        int used = puzzle.getHintsUsed();
        if (used >= hints.size()) return null;

        String hint = hints.get(used);
        puzzle.increaseHintUsed();

        if (currentUser != null) {
            currentUser.incrementHintUsed(puzzleID);
            Users.getInstance().saveUsers();
        }

        return hint;
    }

    public String checkItem(int puzzleID) {
        Puzzle puzzle = getPuzzle(puzzleID);
        if (puzzle == null) return null;

        if (!puzzle.solved()) return null;

        String item = puzzle.getItem();
        if (item == null || item.isBlank()) return null;

        // grant item to user if they don't have it yet
        if (currentUser != null) {
            List<String> items = currentUser.getItems();
            if (items == null || !items.contains(item)) {
                currentUser.addItem(item);
                // mark granted
                puzzle.grantItem(item);
                Users.getInstance().saveUsers();
            }
        }

        return item;
    }

    // Save currently loaded users to disk
    public void saveData() {
        Users.getInstance().saveUsers();
    }

    // Log out current user (does not clear persisted data)
    public void logout() {
        this.currentUser = null;
    }

    // Log in by username+password (returns true on success)
    public boolean login(String username, String password) {
        Users users = Users.getInstance();
        if (users == null) return false;
        for (User u : users.getUsers()) {
            if (u != null && u.userMatch(username, password)) {
                this.currentUser = u;
                return true;
            }
        }
        return false;

    }

    public static class ProgressSummary {
        public final int totalPuzzles;
        public final List<Integer> answeredPuzzleIds;
        public final Map<Integer, Integer> hintsUsedByPuzzle;
        public final double percentComplete;

        public ProgressSummary(int totalPuzzles,
                            List<Integer> answeredPuzzleIds,
                            Map<Integer, Integer> hintsUsedByPuzzle,
                            double percentComplete) {
            this.totalPuzzles = totalPuzzles;
            this.answeredPuzzleIds = answeredPuzzleIds;
            this.hintsUsedByPuzzle = hintsUsedByPuzzle;
            this.percentComplete = percentComplete;
        }
    }

    public ProgressSummary getProgressCurrent() {
        if (currentUser == null) return null;

        List<Room> rooms = DataLoader.getRooms();
        int totalPuzzles = 0;
        Map<Integer, Integer> hintsUsedMap = new HashMap<>();

        if (currentUser != null && currentUser.getHintsUsedMap() != null) {
            hintsUsedMap.putAll(currentUser.getHintsUsedMap());
        } else {
            if (rooms != null) {
                for (Room r : rooms) {
                    if (r == null) continue;
                    List<Puzzle> puzzles = r.getPuzzles();
                    if (puzzles == null) continue;
                    for (Puzzle p : puzzles) {
                        if (p == null) continue;
                        int used = p.getHintsUsed();
                        if (used > 0) hintsUsedMap.put(p.getPuzzleID(), used);
                    }
                }
            }
        }

        List<Integer> answered = new ArrayList<>(currentUser.getPuzzlesComplete());
        double percent = totalPuzzles == 0 ? 0.0 :
                (answered.size() / (double) totalPuzzles) * 100.0;

        return new ProgressSummary(totalPuzzles, answered, hintsUsedMap, percent);
    }

    public boolean completeGame() {
        if (currentUser == null) return false;

        int roomId = currentUser.getCurrentRoom();
        if (roomId <= 0) return false;

        Room room = getRoomByID(roomId);
        if (room == null) return false;

        List<Puzzle> puzzles = room.getPuzzles();
        if (puzzles == null) puzzles = List.of();

        // Count required puzzles for completion (ignore PICTURE puzzles if you prefer)
        List<Integer> requiredPuzzleIds = new ArrayList<>();
        for (Puzzle p : puzzles) {
            if (p == null) continue;
            // Decide which puzzles count toward completion. We will count LOGIC and MATH only.
            Puzzle.PuzzleType t = p.getPuzzleType();
            if (t == Puzzle.PuzzleType.LOGIC || t == Puzzle.PuzzleType.MATH) {
                requiredPuzzleIds.add(p.getPuzzleID());
            }
        }

        // Compare with user's completed list
        List<Integer> completed = currentUser.getPuzzlesComplete() != null
                ? currentUser.getPuzzlesComplete() : new ArrayList<>();

        for (Integer req : requiredPuzzleIds) {
            if (!completed.contains(req)) {
                // Not complete yet
                return false;
            }
        }

        // All required puzzles solved -> mark game complete
        // For demo purposes: clear current game & room, keep lastPuzzle as the last solved
        currentUser.setCurrentGame(0);
        currentUser.setCurrentRoom(0);
        if (!requiredPuzzleIds.isEmpty()) {
            // set lastPuzzle to the highest solved id among required ones that user completed
            int last = completed.stream().filter(requiredPuzzleIds::contains).mapToInt(i -> i).max().orElse(currentUser.getLastPuzzle());
            currentUser.setLastPuzzle(last);
        }

        // Persist user update
        Users.getInstance().saveUsers();

        return true;
    }
}
