/**
 * @Author: Rotten Apple
 * CSCE247
 */

package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code GameSystemFacade} is a lightweight facade exposing core gameplay methods to the backend.
 * Mediates user actions for almost everything i.e. signup, login, starting and answering puzzles.
 * 
 * Keeps a reference to {@link User} and uses {@link Users} and {@link DataLoader} utilities for
 * persistence and data. 
 */
public class GameSystemFacade {

    // The currently logged-in user (may be {@code null} if no user is logged in)
    private User currentUser;

    // Creates a new {@code GameSystemFacade} instance.
    public GameSystemFacade() {}

    /**
     * Attempts to create a new user account and sets that user as the current user
     * on success. New users are persisted via {@link Users#saveUsers()}.
     *
     * @param username the requested username
     * @param password the requested password
     * @return the newly created {@link User} when creation succeeds, or {@code null}
     *         if account creation failed
     */
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

    /**
     * Returns the currently active user.
     *
     * @return the current {@link User}, or {@code null} if nobody is logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the currently active user for the facade.
     * 
     * @param user the {@link User} to set as current (may be {@code null} to log out)
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Starts a new game session for the current user in the provided {@link Room}.
     * This updates the user's current game/room and resets the last puzzle marker.
     * The method persists the change to disk.
     *
     * @param room the room to start (must not be {@code null})
     * @throws IllegalStateException if no user is currently logged in
    */
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

    /**
     * Retrieves the room from {@link DataLoader#getRooms()}.
     *
     * @return the first {@link Room} if one exists, otherwise {@code null}
     */
    public Room getRoom() {
        List<Room> rooms = DataLoader.getRooms();
        return (rooms == null || rooms.isEmpty()) ? null : rooms.get(0);
    }

    /**
     * Finds a room by its numeric ID.
     *
     * @param roomId the room identifier to look up
     * @return the {@link Room} with matching ID or {@code null} if not found
     */
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

    /**
     * Locates a {@link Puzzle} by its ID across all loaded rooms.
     *
     * @param puzzleID the puzzle ID to search for (may be {@code null})
     * @return the matching {@link Puzzle} or {@code null} if not found
     */
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

    /**
     * Attempts to answer a puzzle for the current user. Verifies required
     * items are present, passes the answer attempt to {@link Puzzle#attempt(String)},
     * on success granting rewards and persistence.
     *
     * @param puzzleID the puzzle id to answer
     * @param userAnswer the user's submitted answer
     * @return {@code true} if the answer is correct and processed; {@code false} otherwise
     */
    public boolean answerPuzzle(int puzzleID, String userAnswer) {
        Puzzle puzzle = getPuzzle(puzzleID);
        if (puzzle == null) {
            return false;
        }

        if (currentUser == null) {
            return false;
        }

        // check requirements
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

    /**
     * Retrieves a hint for the given puzzle. Also tracks hint usage
     * on the {@link Puzzle} and increments the hints-used counters for future use.
     *
     * @param puzzleID the puzzle id for which to request a hint
     * @return the hint {@link String} if available, or {@code null} if no hints remain
     */
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

    /**
     * Checks whether a solved puzzle has an item to grant and grants it to the current
     * user. Persists user state.
     *
     * @param puzzleID the puzzle id to check for an item
     * @return the item {@link String} if present, otherwise {@code null}
     */
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

    // Persist currently loaded users to disk
    public void saveData() {
        Users.getInstance().saveUsers();
    }

    // Log out current user (does not clear persisted data)
    public void logout() {
        this.currentUser = null;
    }

    /**
     * Attempts to authenticate a user by username and password. On success the matched
     * {@link User} becomes the current user.
     *
     * @param username the username to authenticate
     * @param password the password to authenticate
     * @return {@code true} if logged in and the user is set as current; {@code false} otherwise
     */
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

    /**
     * Compact summary of progress for the current user. Contains total puzzle count
     * (if known), list of answered puzzle IDs, and a map of hints used per puzzle.
     */
    public static class ProgressSummary {
        // Total number of puzzles
        public final int totalPuzzles;
        // Puzzle IDs of answered
        public final List<Integer> answeredPuzzleIds;
        // Mapping from puzzle ID and # of hints
        public final Map<Integer, Integer> hintsUsedByPuzzle;
        // 0-100% of % of puzzles in a game complete
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

    /**
     * Returns a {@link ProgressSummary} for the currently logged-in user.
     * Attempts to read hints used from the user object.
     *
     * @return a {@link ProgressSummary} for the current user, or {@code null} if no user is logged in
     */
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

    /**
     * Attempts to mark current game as complete, requiring having solved all required puzzles
     * 
     * On successful completion, current game and room are cleared from the json and this is persisted
     * @return {@code true} if game is marked complete, otherwise {@code false}
     */
    public boolean completeGame() {
        if (currentUser == null) return false;

        int roomId = currentUser.getCurrentRoom();
        if (roomId <= 0) return false;

        Room room = getRoomByID(roomId);
        if (room == null) return false;

        List<Puzzle> puzzles = room.getPuzzles();
        if (puzzles == null) puzzles = List.of();

        // Count required puzzles for completion
        List<Integer> requiredPuzzleIds = new ArrayList<>();
        for (Puzzle p : puzzles) {
            if (p == null) continue;

            // Decide which puzzles count toward completion.
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
