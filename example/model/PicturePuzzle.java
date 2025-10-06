package example.model;

import java.util.List;

public class PicturePuzzle extends Puzzle {
    private List <String> hints;
    private String imagePath;

    public PicturePuzzle(int puzzleID, String question, String solution, String imagePath) {
        super(puzzleID, question, solution);
       
    }

    public List<String> getHints () {
        return hints;
        
    }

    public void addHint (String hint) {
        
    }

    public <BufferdReader> String getImage() {
        return imagePath;
    }
    
}
