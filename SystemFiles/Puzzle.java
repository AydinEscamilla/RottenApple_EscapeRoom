//package SystemFiles;



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
    protected String solution;
    private boolean isSolved = false;
    private int attempts = 0;
    private int maxAttempts = 3;
    private int scoreValue = 0;
    private int hintUsedCount = 0;
    private Difficulty difficulty = Difficulty.EASY;

    

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

    public int getScoreValue() {
        return scoreValue;

    }

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
