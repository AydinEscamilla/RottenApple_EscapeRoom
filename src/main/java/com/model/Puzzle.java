/**
 * @Author: Rotten Apple
 * CSCE247
 */
package com.model;
import java.util.*;

/**
 * This class represents a puzzle in the escape room
 */
public abstract class Puzzle {

    /**
     * Enum representing puzzle's status
     */
    public enum PuzzleStatus {
        NOT_STARTED, 
        IN_PROGRESS, 
        SOLVED, 
        FAILED
    }

    /**
     * Enum representing puzzle's type
     */
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
    
    /**
     * 
     * @param puzzleID the puzzle's specific ID
     * @param puzzleType the puzzle's type
     * @param question the puzzle's question
     * @param solution the puzzle's solution
     * @param hints list of hints for the puzzle
     * @param imagePaths the images for the puzzle
     * @param isSolved is the puzzle solved or not
     * @param attempts number of attempts the player has made
     * @param maxAttempts total attempts allowed on the puzzle
     * @param scoreValue current score
     * @param hintUsedCount number of hints the player has used
     * @param difficulty difficulty level of the puzzle
     * @param item item contained by the puzzle
     */
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

    /**
     * Values for scoring
     */
    private int baseScore = 10;
    private int perHintPenalty = 2;
    private int perExtraAttempt = 1;

    
    private final List <String> itemsRequired = new ArrayList<>();
    private final List <String> itemsGranted = new ArrayList<>();

    
    /**
     * Constructs a puzzle with specific parameters
     * @param puzzleID the puzzle's ID 
     * @param type the puzzle's type
     * @param question the puzzle question
     * @param solution the solution for the question 
     */
    public Puzzle (int puzzleID, PuzzleType type, String question, String solution) {
        this.puzzleID = puzzleID;
        this.puzzleType = type;
        this.question = question;
        this.solution = solution;
         

    }


    /**
     * Player attempts to solve the puzzle with an answer
     * @param answer the player's answer to the question
     * @return true if the answer is correct and false if not
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

    /**
     * 
     * @return list of hints for the puzzle
     */
    public List<String> getHints() {
        return hints;
    }

    /**
     * Checks if the answer is correct
     * @param FixedAnswer answer being checked
     * @return true if the answer is correct and false if not 
     */
    protected boolean isCorrect (String FixedAnswer) {
        return FixedAnswer.equals(FixedString(solution));
    }

    /**
     * Changes a string to lowercase and trims it
     * @param s input string
     * @return standard string
     */
    public String FixedString(String s) {
       return s == null ? "" : s.trim().toLowerCase();
    }

   

    /**
     * 
     * @return the puzzle's status
     */
    public PuzzleStatus getStatus() {
        if (isSolved) return PuzzleStatus.SOLVED;
        if (attempts == 0) return PuzzleStatus.NOT_STARTED;
        if (attempts >= maxAttempts) return PuzzleStatus.FAILED;
        return PuzzleStatus.IN_PROGRESS;

    }

    /**
     * Sets the puzzle as given up
     */
    public void giveUp() {
        if (!isSolved) attempts = maxAttempts;
    }

    /**
     * 
     * @return the puzzle's specific ID
     */
     public int getPuzzleID() {
        return puzzleID;
    }

    /**
     * 
     * @return the puzzle's type
     */
    public PuzzleType getPuzzleType() {
        return puzzleType;
    }

    /**
     * 
     * @return the puzzle's question
     */
     public String getQuestion() {
        return question;
    }

    /**
     * 
     * @return the solution to the puzzle's question
     */
    public String getSolution() {
        return solution;

    }

    /**
     * 
     * @return true if the puzzle is solved and false if not
     */
    public boolean solved() {
        return isSolved;

    }


    /**
     * 
     * @return the number of attempts made
     */
    public int getAttempts() {
        return attempts;

    }

    /**
     * 
     * @return the max number of attempts for the puzzle
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * 
     * @return the base score for the puzzle
     */
    public int getBaseScore() {
        return baseScore;
    }

    /**
     * sets the base score
     * @param baseScore the base score for the puzzle
     */
    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }

    /**
     * Sets the penalty for using a hint
     * @param per the points being subtracted for each hint
     */
    public void setPerHintPenalty(int per) {
        this.perHintPenalty = Math.max(0, per);
    }

    /**
     * Sets the penalty for making extra attempts
     * @param per the points being subtracted for each extra attempt 
     */
    public void setPerExtraAttempt(int per) {
        this.perExtraAttempt = Math.max(0, per);
    }

    /**
     * Returns the player's calculated score
     * @return the score value
     */
    public int getScoreValue() {
        if(!solved()) return 0;
        int extraAttempts = Math.max(0, getAttempts() - 1); 
        int totalPenalty = (getHintsUsed() * perHintPenalty) + (extraAttempts * perExtraAttempt);
        int scoreValue = Math.max(0, baseScore - totalPenalty);


        return scoreValue;

    }

    /**
     * Grants an item to the player
     * @param item the item being granted
     * @return this puzzle instance
     */
    public Puzzle grantItem(String item) {
        if (item != null && !item.isBlank()) {
            itemsGranted.add(item);
        }
        return this;
    }

    /**
     * Sets the puzzle to require an item
     * @param itemID the ID of the item required for the puzzle
     * @return this puzzle instance
     */
    public Puzzle requireItem(String itemID) {
        if (itemID != null && !itemID.isBlank()) {
            itemsRequired.add(itemID);
        }
        return this;
    }

    /**
     * Returns a list of items require for the puzzle
     * @return list of required items
     */
    public List<String> getRequiredItems() {
        return itemsRequired;
    }

    /**
     * Returns the items granted upon solving the puzzle
     * @return list of granted items
     */
    public List<String> getGrantedItems() {
        return itemsGranted;
    }

    /**
     * Returns the item for the puzzle
     * @return the item string
     */
    public String getItem() {
        return item;
    }

    

    /**
     * Returns puzzle hint, Overidden in subclass
     * @return hint string
     */
    public String getHint() {
        return null; 

    }

    /**
     * Returns the number of hints that were used for the puzzle
     * @return count of hints used
     */
    public int getHintsUsed() {
        return hintUsedCount;
    }

    /**
     * Increment the number of hints used for the puzzle 
     */
    public void increaseHintUsed() {
        hintUsedCount++;
    }

    /**
     * Returns the difficulty of the puzzle
     * @return difficulty level of the puzzle
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level on the puzzle
     * @param difficulty the difficulty to set for the puzzle 
     */
    public void setDifficulty (Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    
    /**
     * Adds a hint to the puzzle 
     * @param hint the hint to add
     */
    public abstract void addHint (String hint); //  abstract hint method to be overriden



}
