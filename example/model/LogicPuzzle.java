package example.model;

import java.util.List;

public class LogicPuzzle extends Puzzle {
    

    private List <String> hints;

    public LogicPuzzle(int puzzleID, String question, String solution) {
        super(puzzleID, question, solution);
       
    }

    public List<String> getHints () {
        return hints;
        
    }

    public void addHint (String hint) {
        
    }
}
