package example.model;

public class Puzzle {
    private int puzzleID;
    private String puzzleType;
    private String question;
    private String solution;
    private boolean isSolved;
    private int attempts;
    private int maxAttempts;
    private int scoreValue;
    private int hintUsedCount;
    private Difficulty Difficulty;

    public Puzzle (int puzzleID, String question, String solution) {
        this.puzzleID = puzzleID;
        this.question = question;
        this.solution = solution;
         

    }


    //  Check
    public boolean attempt (String answer) {
        return false ;

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

    public int getScoreValue() {
        return scoreValue;

    }

    public String getHint() {
        return; 

    }

    public void giveUp() {

    }

}
