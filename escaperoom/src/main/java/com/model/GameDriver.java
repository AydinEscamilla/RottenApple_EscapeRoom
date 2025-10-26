package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameDriver {     
    
    public GameSystemFacade facade;
    public User user;
    public Room room;

    public GameDriver() {
        this.facade = new GameSystemFacade();
    }

    public static void main(String[] args) { 
        GameDriver driver = new GameDriver(); 
        driver.run(); 
    } 
    
    public void run() { 
        DuplicateAccount(); 
        
        CreateAccount(); 

        EnterRoom();

        ThreePuzzles();

        DataPersistence();
        
        //GameCompletion();

        System.out.println("Thank you for watching our backend presentation!");
    } 
    
    public void DuplicateAccount() { 
        String username = "LRivers";
        String password = "password1";
        
        System.out.println("Leni is attempting to create an account with username LRivers.");

        User result = facade.signup(username, password);

        System.out.println(result + "; account creation failed due to existing LRivers.");
        System.out.println("Account creation failed due to duplicate user (1/6)\n");   
    } 
    
    public void CreateAccount() { 
        String username = "LeniRivers";
        String password = "password4";

        System.out.println("Leni is attempting to create an account with username LeniRivers.");

        User newUser = facade.signup(username, password);
        System.out.println("signup returned: " + newUser);
        System.out.println("All users after signup: " + Users.getInstance().getUsers());

        System.out.println("Unique username used; account creation succeeded: " + newUser);
        System.out.println("Account creation succeeded (2/6)\n"); 
    } 

    public void EnterRoom() {
        System.out.println("Leni is entering an escape room for the first time.");

        Room roomChoice = facade.getRoomByID(101);

        System.out.println("Leni has chosen " + roomChoice);
        // USE TTS HERE
        System.out.println(roomChoice.getDescription(roomChoice));

        facade.startNewGame(roomChoice);

        System.out.println("Leni has listened to the story's opening.");
        System.out.println("Starting story heard (3/6)\n");
    }

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

        
        // Presentation output (short, simple)
        System.out.println();
        System.out.println("Leni’s progress so far:");
        System.out.printf(" - %.0f%% complete (%d of %d puzzles solved)%n",
                percent, logicMathAnswered.size(), logicMathTotal);

        System.out.print(" - Puzzles answered: ");
        if (logicMathAnswered.isEmpty()) {
            System.out.println("(none)");
        } else {
            System.out.println(logicMathAnswered);
        }

        System.out.print(" - Hints used on: ");
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

    public void GameCompletion() {
        System.out.println("Leni will now complete the last puzzle and therefore the game.");
        
        /*
        facade.openGame();
        facade.getPuzzle(202);
        facade.answerPuzzle();
        facade.completeGame(LeniRivers, password4);
        facade.showLeaderboard();
        facade.printCertificate();
        */

        System.out.println("Leni has finished the game and been given her certificate.");
        System.out.println("Game completed (6/6)\n");
        return;
    }

    public void AwaitEnter() {
        try {
            System.in.read();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void RequestHint(int currentPuzzle) {
        System.out.println("Using hint.");
        String hint = facade.getHint(currentPuzzle);
        if (hint == null) {
            System.out.println("No hints remaining.");
        } else {
            System.out.println("Hint: " + hint);
        }
    }

    public void CheckForItem(int currentPuzzle) {
        System.out.println("Checking for item...");
        String item = facade.checkItem(currentPuzzle);
        if (item == null) {
            System.out.println("No item available (or puzzle not solved).");
        } else {
            System.out.println("Item: " + item);
        }
    }

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

}