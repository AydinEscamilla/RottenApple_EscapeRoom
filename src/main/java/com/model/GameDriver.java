/**
 * @Author: Rotten Apple
 * CSCE247
 */

package com.model;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@code GameDriver} demonstrates and tests the functionality of the
 * {@link GameSystemFacade} backend system for an escape room-style game.
 * 
 * This class simulates the actions of a user named Leni Rivers as she:
 *  Creates and logs into an account</li>
 *  Enters a room and plays through several puzzles</li>
 *  Demonstrates data persistence</li>
 *  Completes the game and receives a certificate</li>
 *  Prints the leaderboard of all users</li>
 * 
 * The purpose is to show key backend interactions such as signup, login,
 * puzzle progression, hint usage, and scoring.
 */
public class GameDriver {     
    

    /** Facade providing access to system-level game operations. */
    public GameSystemFacade facade;
    /** Currently active user in the demo. */
    public User user;
    /** Currently active room for gameplay. */
    public Room room;

    /**
     * Constructs a new {@code GameDriver} and initializes the
     * {@link GameSystemFacade}.
     */
    public GameDriver() {
        this.facade = new GameSystemFacade();
    }

    
    /**
     * Main entry point for running the game demo.
     * 
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) { 
        GameDriver driver = new GameDriver(); 
        driver.run(); 
    } 
    
    /**
     * Runs the entire backend demonstration sequence in order:
     * account creation, entering room, solving puzzles,
     * persistence, and completion.
     */
    public void run() { 
        DuplicateAccount(); 
        AwaitEnter();

        CreateAccount(); 
        AwaitEnter();
        AwaitEnter();

        EnterRoom();
        AwaitEnter();
        AwaitEnter();
        
        ThreePuzzles();
        AwaitEnter();
        AwaitEnter();
        
        DataPersistence();
        AwaitEnter();
        AwaitEnter();
        
        GameCompletion();
        AwaitEnter();
        AwaitEnter();
        System.out.println("Thank you for watching our backend presentation!");
    } 
    
    /**
     * Demonstrates the system preventing duplicate account creation by
     * attempting to sign up with existing username
     */
    public void DuplicateAccount() { 
        String username = "LRivers";
        String password = "password1";
        
        System.out.println("Leni is attempting to create an account with username LRivers.");

        User result = facade.signup(username, password);

        System.out.println(result + "; account creation failed due to existing LRivers.");
        System.out.println("Account creation failed due to duplicate user (1/6)\n");   
    } 
    
    /**
     * Demonstrates successful account creation with a new unique username
     */
    public void CreateAccount() { 
        String username = "LeniRivers";
        String password = "password4";

        System.out.println("Leni is attempting to create an account with username LeniRivers.");

        User newUser = facade.signup(username, password);
        System.out.println("signup returned: " + newUser);

        System.out.println("Unique username used; account creation succeeded: " + newUser);
        System.out.println("Account creation succeeded (2/6)\n"); 
    } 

    /**
     * Demonstrates selecting and entering an escape room.
     * Retrieves room details and starts a new game session.
     */
    public void EnterRoom() {
        System.out.println("Leni is entering an escape room for the first time.");

        Room roomChoice = facade.getRoomByID(101);

        System.out.println("Leni has chosen " + roomChoice);

        if (roomChoice != null) {
            System.out.println(roomChoice.getDescription(roomChoice));
        } else {
            System.out.println("(no room description available)");
        }

        facade.startNewGame(roomChoice);

        System.out.println("Leni has listened to the story's opening.");
        System.out.println("Starting story heard (3/6)\n");
    }

    /**
     * Demonstrates solving three different puzzles (with and without hints).
     * Shows item collection and puzzle-solving logic.
     */
    public void ThreePuzzles() {
        String userAnswer;
        Integer currentPuzzle;
        boolean hint;

        System.out.println("Leni is attempting three puzzles within the escape room.");
        System.out.println();

        // Puzzle 101
        currentPuzzle = 101;
        userAnswer = "Friday";
        hint = false;

        playPuzzle(currentPuzzle, userAnswer, hint);

        // Puzzle 102
        currentPuzzle = 102;
        userAnswer = "Three";
        hint = true;

        playPuzzle(currentPuzzle, userAnswer, hint);

        // Puzzle 201
        currentPuzzle = 201;
        userAnswer = "25";
        hint = true;

        playPuzzle(currentPuzzle, userAnswer, hint);

        System.out.println("Leni has completed three puzzles.");
        System.out.println("Three puzzles demonstrated (4/6)\n");

        User cu = facade.getCurrentUser();
        if (cu != null) {
            System.out.println("Items collected so far: " + cu.getItems());
        }
    }

    /**
     * Demonstrates persistence of user progress across sessions.
     * Saves, logs out, and logs back in to verify data restoration.
     */
    public void DataPersistence() {
        String username = "LeniRivers";
        String password = "password4";

        System.out.println("\nLeni will now log out and back in, resuming progress.");
        
        // Persist
        facade.saveData();
        System.out.println("Data saved.");

        // Logout
        facade.logout();
        System.out.println("Leni has been logged out.");

        // Log back in
        boolean loggedIn = facade.login(username, password);
        if (!loggedIn) {
            System.out.println("Login failed.");
        }
        System.out.println("Leni has been logged back in.");

        GameSystemFacade.ProgressSummary summary = facade.getProgressCurrent();

        int logicMathTotal = 0;
        List<Integer> logicMathAnswered = new ArrayList<>();
        List<Integer> logicMathHintsUsed = new ArrayList<>();

        List<Room> rooms = DataLoader.getRooms();
        if (rooms != null) {
            for (Room r : rooms) {
                if (r == null) continue;
                List<Puzzle> puzzles = r.getPuzzles();
                if (puzzles == null) continue;
                for (Puzzle p : puzzles) {
                    if (p == null) continue;
                    Puzzle.PuzzleType t = p.getPuzzleType();
                    if (t == Puzzle.PuzzleType.LOGIC || t == Puzzle.PuzzleType.MATH) {
                        logicMathTotal++;
                        // if facade answered list contains this id, count it
                        if (summary != null && summary.answeredPuzzleIds != null
                                && summary.answeredPuzzleIds.contains(p.getPuzzleID())) {
                            logicMathAnswered.add(p.getPuzzleID());
                        }
                        // if the facade hints map shows hints for this puzzle, record it
                        if (summary != null && summary.hintsUsedByPuzzle != null
                                && summary.hintsUsedByPuzzle.containsKey(p.getPuzzleID())) {
                            logicMathHintsUsed.add(p.getPuzzleID());
                        }
                    }
                }
            }
        }

        double percent = logicMathTotal == 0 ? 0.0
            : (logicMathAnswered.size() / (double) logicMathTotal) * 100.0;

        
        System.out.println();
        System.out.println("Lenis progress so far:");
        System.out.printf(" - %.0f%% complete (%d of %d puzzles solved)%n",
                percent, logicMathAnswered.size(), logicMathTotal);

        System.out.print(" - Puzzles answered: ");
        if (logicMathAnswered.isEmpty()) {
            System.out.println("(none)");
        } else {
            System.out.println(logicMathAnswered);
        }

        System.out.print(" - Hints used on puzzles of ID: ");
        if (logicMathHintsUsed.isEmpty()) {
            System.out.println("(none)");
        } else {
            System.out.println(logicMathHintsUsed);
        }

        // Show the saved user JSON for demonstration
        System.out.println();
        System.out.println("Saved user data (json/user.json):");
        try {
            java.nio.file.Path p = java.nio.file.Paths.get("json", "user.json");
            if (java.nio.file.Files.exists(p)) {
                String contents = new String(java.nio.file.Files.readAllBytes(p));
                System.out.println(contents);
            } else {
                System.out.println("user.json not found.");
            }
        } catch (Exception e) {
            System.out.println("Could not read user.json: " + e.getMessage());
        }

        System.out.println("Leni has logged out and maintained progress.");
        System.out.println("Data persistence shown (5/6)\n");
        return;
    }

    /**
     * Demonstrates final puzzle completion and generation of a certificate
     * of completion with score/leaderboard
     */
    public void GameCompletion() {
        String userAnswer = "24";
        Integer currentPuzzle = 202;
        boolean hint = false;

        System.out.println("Leni will now complete the last puzzle and therefore the game.");

        // Puzzle 202 (final puzzle)
        playPuzzle(currentPuzzle, userAnswer, hint);

        boolean completed = facade.completeGame();
        if (completed) {
            System.out.println("The clues are starting to come together but what could it mean...");
            System.out.println("\"That's it! I know what to check next!\"");
            System.out.println("The room fades to black as you run towards the computer to verify your theory...");
            System.out.println("Leni has completed the demo!\n");
            
            System.out.println();
        } else {
            System.out.println("Fatal error; not completed as expected");
            return;
        }

        // Build certificate variables from facade/user/rooms
        User cu = facade.getCurrentUser();
        String username = (cu != null) ? cu.getUsername() : "Player";

        // Find room: prefer currentRoom, otherwise fallback to find by lastPuzzle
        Room room = null;
        if (cu != null) {
            room = facade.getRoomByID(cu.getCurrentRoom());
            if (room == null) {
                int lastPid = cu.getLastPuzzle();
                List<Room> allRooms = DataLoader.getRooms();
                if (allRooms != null) {
                    for (Room r : allRooms) {
                        if (r == null) continue;
                        List<Puzzle> ps = r.getPuzzles();
                        if (ps == null) continue;
                        for (Puzzle p : ps) {
                            if (p != null && p.getPuzzleID() == lastPid) {
                                room = r;
                                break;
                            }
                        }
                        if (room != null) break;
                    }
                }
            }
        }

        String roomName = (room != null) ? room.getRoomName() : "Unknown Room";

        // Build list of required puzzle IDs (Logic + Math)
        List<Integer> requiredPuzzleIds = new ArrayList<>();
        if (room != null && room.getPuzzles() != null) {
            for (Puzzle p : room.getPuzzles()) {
                if (p == null) continue;
                Puzzle.PuzzleType t = p.getPuzzleType();
                if (t == Puzzle.PuzzleType.LOGIC || t == Puzzle.PuzzleType.MATH) {
                    requiredPuzzleIds.add(p.getPuzzleID());
                }
            }
        }

        // Count how many of those the user has completed
        List<Integer> userCompleted = (cu != null && cu.getPuzzlesComplete() != null) ? cu.getPuzzlesComplete() : List.of();
        int completedCount = 0;
        List<Integer> completedInRoom = new ArrayList<>();
        for (Integer pid : requiredPuzzleIds) {
            if (userCompleted.contains(pid)) {
                completedCount++;
                completedInRoom.add(pid);
            }
        }

        // Get persisted hints-used map from facade progress summary
        GameSystemFacade.ProgressSummary progress = facade.getProgressCurrent();
        Map<Integer, Integer> hintsMap = (progress != null && progress.hintsUsedByPuzzle != null)
                ? progress.hintsUsedByPuzzle : Map.of();

        // Sum hints used for the relevant puzzles (in the room)
        int hintsUsedTotal = 0;
        for (Integer pid : requiredPuzzleIds) {
            hintsUsedTotal += hintsMap.getOrDefault(pid, 0);
        }

        // Determine difficulty and multiplier (Hard -> multiplier 2)
        String difficultyLabel = "Easy";
        int multiplier = 1;
        if (room != null && room.getPuzzles() != null) {
            for (Puzzle p : room.getPuzzles()) {
                if (p == null) continue;
                if (p.getDifficulty() == Difficulty.HARD) {
                    difficultyLabel = "Hard";
                    multiplier = 2;
                    break;
                }
            }
        }

        // Score formula: (#puzzles completed * 250 * multiplier) - (#hints used * 125)
        int basePerPuzzle = 250;
        int hintPenalty = 125;
        int rawScore = (completedCount * basePerPuzzle * multiplier) - (hintsUsedTotal * hintPenalty);
        int finalScore = Math.max(0, rawScore);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("**********************************************");
        System.out.println("          CERTIFICATE OF COMPLETION");
        System.out.println("**********************************************");
        System.out.printf("%-25s %s%n", "For", username);
        System.out.printf("%-25s %s%n", "Completion of", roomName);
        System.out.printf("%-25s %s%n", "On date", now.format(formatter));
        System.out.println();
        System.out.printf("%-25s %s%n", "On difficulty", difficultyLabel);
        System.out.printf("%-25s %d%n", "With hints used", hintsUsedTotal);
        System.out.printf("%-25s %d%n", "Granting final score", finalScore);
        System.out.println();
        System.out.println("Congratulations on stomping out corruption!");
        System.out.println("**********************************************");

        // Leaderboard output created here; meant to do the same for certificate above, couldn't
        printLeaderboard();

        System.out.println("Leni has finished the game and been given her certificate.");
        System.out.println("Game completed (6/6)\n");
        return;
    }

    // Pauses the demonstration until extra Enter is pressed to allow speaking roles
    public void AwaitEnter() {
        try {
            System.in.read();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Requests a hint for a specific puzzle through the {@link GameSystemFacade}.
     * 
     * @param currentPuzzle puzzle ID for which a hint is requested
     */
    public void RequestHint(int currentPuzzle) {
        System.out.println("Using hint.");
        String hint = facade.getHint(currentPuzzle);
        if (hint == null) {
            System.out.println("No hints remaining.");
        } else {
            System.out.println("Hint: " + hint);
        }
    }

    /**
     * Checks if solving a puzzle yields a collectible item.
     * 
     * @param currentPuzzle puzzle ID being checked
     */
    public void CheckForItem(int currentPuzzle) {
        System.out.println("Checking for item...");
        String item = facade.checkItem(currentPuzzle);
        if (item == null) {
            System.out.println("No item available (or puzzle not solved).");
        } else {
            System.out.println("Item: " + item);
        }
    }

    /**
     * Handles logic for attempting and answering a specific puzzle.
     * Includes hint usage, correctness checking, and item acquisition.
     * 
     * @param puzzleID the unique ID of the puzzle
     * @param userAnswer the player's answer submission
     * @param useHint true if a hint should be used before answering
     */
    private void playPuzzle(int puzzleID, String userAnswer, boolean useHint) {
        Puzzle puzzle = facade.getPuzzle(puzzleID);
        if (puzzle == null) {
            System.out.println("Puzzle not found.");
            return;
        }

        System.out.println(puzzle.getQuestion());

        if (useHint) {
            RequestHint(puzzleID);
        }

        System.out.println("Answering: " + userAnswer);

        if (!canAttemptCurrent(puzzle)) {
            System.out.println("You can't attempt that yet. You need: " + puzzle.getRequiredItems());
            return;
        }

        // snapshot items before attempting
        User cu = facade.getCurrentUser();
        List<String> beforeItems = cu != null && cu.getItems() != null ? new ArrayList<>(cu.getItems()) : new ArrayList<>();

        boolean solved = facade.answerPuzzle(puzzleID, userAnswer);
        if (solved) {
            System.out.println("You have a feeling that this is what they meant... what next?");

            // detect newly granted items
            List<String> afterItems = cu != null && cu.getItems() != null ? cu.getItems() : new ArrayList<>();
            for (String it : afterItems) {
                if (!beforeItems.contains(it)) {
                    System.out.println("You received: " + it);
                }
            }
        } else {
            System.out.println("No, that's incorrect.");
        }

        System.out.println();
    }

    /**
     * Determines if the current user can attempt a given puzzle
     * based on required item possession.
     * 
     * @param puzzle the {@link Puzzle} to check
     * @return true if all required items are held, false otherwise
     */
    private boolean canAttemptCurrent(Puzzle puzzle) {

        if (puzzle == null) return false;

        User cu = facade.getCurrentUser();
        if (cu == null) return false;

        List<String> required = puzzle.getRequiredItems();
        if (required == null || required.isEmpty()) {
            // No item needed
            return true;
        }

        List<String> userItems = cu.getItems();
        if (userItems == null) {
            System.out.println("You don't have any items yet. This puzzle requires: " + required);
            return false;
        }

        // check which required items are owned
        boolean hasAll = true;
        for (String r : required) {
            if (userItems.contains(r)) {
                System.out.println("Used item: " + r);
            } else {
                System.out.println("Missing required item: " + r);
                hasAll = false;
            }
        }

        return hasAll;
    }

    /**
     * Prints a leaderboard of all users sorted by score.
     * Scores are calculated based on completed puzzles,
     * hints used, and difficulty multiplier.
     */
    private void printLeaderboard() {
        List<User> allUsers = Users.getInstance().getUsers();
        if (allUsers == null || allUsers.isEmpty()) {
            System.out.println("Leaderboard: (no users)");
            return;
        }

        class Entry {
            final String username;
            final int score;
            Entry(String u, int s) { username = u; score = s; }
        }

        List<Entry> entries = new ArrayList<>();

        // Prepare room list once
        List<Room> allRooms = DataLoader.getRooms();

        for (User u : allUsers) {
            if (u == null) continue;
            String username = u.getUsername();

            // Find the room for this user
            Room room = null;
            try {
                int roomId = u.getCurrentRoom();
                if (roomId > 0) {
                room = facade.getRoomByID(roomId);
            }
            } catch (Exception ignored) {}

        if (room == null) {
            int lastPid = u.getLastPuzzle();
            if (allRooms != null) {
                outer:
                for (Room r : allRooms) {
                    if (r == null) continue;
                    List<Puzzle> ps = r.getPuzzles();
                    if (ps == null) continue;
                    for (Puzzle p : ps) {
                        if (p != null && p.getPuzzleID() == lastPid) {
                            room = r;
                            break outer;
                        }
                    }
                }
            }
        }

        // Build the set of logic+math puzzle IDs in that room
        List<Integer> roomLogicMathIds = new ArrayList<>();
        if (room != null && room.getPuzzles() != null) {
            for (Puzzle p : room.getPuzzles()) {
                if (p == null) continue;
                Puzzle.PuzzleType t = p.getPuzzleType();
                if (t == Puzzle.PuzzleType.LOGIC || t == Puzzle.PuzzleType.MATH) {
                    roomLogicMathIds.add(p.getPuzzleID());
                }
            }
        }

        // Count how many of those puzzles this user has completed
        List<Integer> userCompleted = u.getPuzzlesComplete() == null ? List.of() : u.getPuzzlesComplete();
        int completedCount = 0;
        for (Integer pid : roomLogicMathIds) {
            if (userCompleted.contains(pid)) completedCount++;
        }

        // Read hints used for this user (if available) via reflection
        int hintsUsedForUser = 0;
        try {
            Method m = u.getClass().getMethod("getHintsUsed");
            Object hm = m.invoke(u);
            if (hm instanceof Map) {
                Map<?,?> map = (Map<?,?>) hm;
                // Sum integer values (keys might be strings or numbers)
                for (Object val : map.values()) {
                    if (val instanceof Number) {
                        hintsUsedForUser += ((Number) val).intValue();
                    } else {
                        try {
                            hintsUsedForUser += Integer.parseInt(String.valueOf(val));
                        } catch (NumberFormatException ignored) {}
                    }
                }
            }
        } catch (NoSuchMethodException nsme) {
            // fallback: try to use facade progress summary if per-user hints not stored
            GameSystemFacade.ProgressSummary prog = facade.getProgressCurrent();
            if (prog != null && prog.hintsUsedByPuzzle != null) {
                // count only hints for puzzles the user has completed (roomLogicMathIds or userCompleted)
                for (Integer pid : userCompleted) {
                    hintsUsedForUser += prog.hintsUsedByPuzzle.getOrDefault(pid, 0);
                }
            }
        } catch (Exception ex) {
            // any other reflection error: just ignore and treat hints as zero
        }

        // Determine difficulty multiplier
        int multiplier = 1;
        if (room != null && room.getPuzzles() != null) {
            for (Puzzle p : room.getPuzzles()) {
                if (p != null && p.getDifficulty() == Difficulty.HARD) {
                    multiplier = 2;
                    break;
                }
            }
        }

        // Compute score: (#completed_in_room * 250 * multiplier) - (#hints_used * 125)
        int score = Math.max(0, (completedCount * 250 * multiplier) - (hintsUsedForUser * 125));

        entries.add(new Entry(username, score));
    }

    // sort descending
    entries.sort(Comparator.comparingInt((Entry e) -> e.score).reversed());

    // print nicely
    System.out.println();
    System.out.println("=========================================");
    System.out.println("               LEADERBOARD");
    System.out.println("=========================================");
    System.out.printf("%-4s %-20s %10s%n", "Rank", "Username", "Score");
    System.out.println("-----------------------------------------");

    int rank = 1;
    for (Entry e : entries) {
        System.out.printf("%-4d %-20s %10d%n", rank, e.username, e.score);
        rank++;
    }

    System.out.println("=========================================");
    System.out.println();
    }
}