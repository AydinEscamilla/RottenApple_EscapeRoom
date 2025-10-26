package com.model;

import java.util.*;

public abstract class Puzzle {

    public enum PuzzleStatus {
        NOT_STARTED, 
        IN_PROGRESS, 
        SOLVED, 
        FAILED
    }

    public enum PuzzleType {
        LOGIC,
        MATH,
        PICTURE
    }

    private int puzzleID;
    private PuzzleType puzzleType;
    private String question;
    private String solution;
    private List<String> hints;
    private List<String> imagePaths;
    private boolean isSolved = false;
    private int attempts = 0;
    private int maxAttempts = 3;
    private int scoreValue = 0;
    private int hintUsedCount = 0;
    private Difficulty difficulty = Difficulty.HARD;
    private String item;
        
    public Puzzle(int puzzleID,
                  PuzzleType puzzleType,
                  String question,
                  String solution,
                  List<String> hints,
                  List<String> imagePaths,
                  boolean isSolved,
                  int attempts,
                  int maxAttempts,
                  int scoreValue,
                  int hintUsedCount,
                  Difficulty difficulty,
                  String item) {
        this.puzzleID = puzzleID;
        this.puzzleType = puzzleType;
        this.question = question;
        this.solution = solution;
        this.hints = hints;
        this.imagePaths = imagePaths;
        this.isSolved = isSolved;
        this.attempts = attempts;
        this.maxAttempts = maxAttempts;
        this.scoreValue = scoreValue;
        this.hintUsedCount = hintUsedCount;
        this.difficulty = difficulty;
        this.item = item;
    }

    // Scoring
    private int baseScore = 10;
    private int perHintPenalty = 2;
    private int perExtraAttempt = 1;

    //  Items
    private final List <String> itemsRequired = new ArrayList<>();
    private final List <String> itemsGranted = new ArrayList<>();

    

    public Puzzle (int puzzleID, PuzzleType type, String question, String solution) {
        this.puzzleID = puzzleID;
        this.puzzleType = type;
        this.question = question;
        this.solution = solution;
         

    }


    /*
     * User attempts to answer, updates attempts and status and returns if it was correct
     */
    public boolean attempt (String answer) {

        if (isSolved) return true;
        if (attempts >= maxAttempts) return false;

        attempts++;

        boolean fixed = isCorrect(FixedString(answer));
        if (fixed) {
            isSolved = true;
            return true;
        }
        return false;


    }

    public List<String> getHints() {
        return hints;
    }

    /*
     * Checks if Answer is Correct
     * @returns if user got answer correct
     */
    protected boolean isCorrect (String FixedAnswer) {
        return FixedAnswer.equals(FixedString(solution));
    }

    /*
     * @returns a string trimed and lowercased
     */
    public String FixedString(String s) {
       return s == null ? "" : s.trim().toLowerCase();
    }

   

    /*
     * To better determine a Puzzle's Progress
     * @returns status on Puzzle
     */
    public PuzzleStatus getStatus() {
        if (isSolved) return PuzzleStatus.SOLVED;
        if (attempts == 0) return PuzzleStatus.NOT_STARTED;
        if (attempts >= maxAttempts) return PuzzleStatus.FAILED;
        return PuzzleStatus.IN_PROGRESS;

    }

    public void giveUp() {
        if (!isSolved) attempts = maxAttempts;
    }

     public int getPuzzleID() {
        return puzzleID;
    }

    public PuzzleType getPuzzleType() {
        return puzzleType;
    }

     public String getQuestion() {
        return question;
    }

    public String getSolution() {
        return solution;

    }

    public boolean solved() {
        return isSolved;

    }


    public int getAttempts() {
        return attempts;

    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    //  Scoring Methods

    public int getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }

    public void setPerHintPenalty(int per) {
        this.perHintPenalty = Math.max(0, per);
    }

    public void setPerExtraAttempt(int per) {
        this.perExtraAttempt = Math.max(0, per);
    }

    /*
     * @return the score value of the puzzle based on hints used and extra attempts
     */
    public int getScoreValue() {
        if(!solved()) return 0;
        int extraAttempts = Math.max(0, getAttempts() - 1); 
        int totalPenalty = (getHintsUsed() * perHintPenalty) + (extraAttempts * perExtraAttempt);
        int scoreValue = Math.max(0, baseScore - totalPenalty);


        return scoreValue;

    }

    //  Inventory/Item Methods


    

    public Puzzle grantItem(String item) {
        if (item != null && !item.isBlank()) {
            itemsGranted.add(item);
        }
        return this;
    }

    public Puzzle requireItem(String itemID) {
        if (itemID != null && !itemID.isBlank()) {
            itemsRequired.add(itemID);
        }
        return this;
    }

    public List<String> getRequiredItems() {
        return itemsRequired;
    }

    public List<String> getGrantedItems() {
        return itemsGranted;
    }

    public String getItem() {
        return item;
    }

    /*
     * Checks if the player can attempt the given puzzle based on their inventory
     * @return true if the player has all required items, false otherwise
     */



    /*
     * Overriden by subclasses
     */
    public String getHint() {
        return null; 

    }

    public int getHintsUsed() {
        return hintUsedCount;
    }

    public void increaseHintUsed() {
        hintUsedCount++;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty (Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    

    public abstract void addHint (String hint); //  abstract hint method to be overriden

    // public abstract void addItem (String item);

}
