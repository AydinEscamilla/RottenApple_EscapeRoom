//package SystemFiles;



public abstract class Puzzle {

    public enum PuzzleState {
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


    //  Check
    public boolean attempt (String answer) {

        if (answer.equals(getSolution(answer))) {
            return true;
        }

        return false ;


    }

    public String getQuestion(String question) {

        if (question != null) {
            return question;
        }

        return "Question not found";
        

    }

    public String getSolution(String solution) {
        if (solution != null) {
            return solution;
        } 

        return "Solution to" + getQuestion(question) + " not found.";

    }

    public boolean solved() {
        return isSolved;

    }

    public int getAttempts() {
        return attempts;

    }

    public int getScoreValue() {
        return scoreValue;

    }

    public String getHint() {
        return null; 

    }

    public void giveUp() {

    }

    public abstract void addHint (String hint); //  abstract hint method to be overriden

}
