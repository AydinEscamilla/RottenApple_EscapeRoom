

import java.util.List;


//import SystemFiles.Puzzle;

public class PicturePuzzle extends Puzzle {
    private List <String> hints;
    private String imagePath;

    public PicturePuzzle(int puzzleID, String question, String solution, String imagePath) {
        super(puzzleID, PuzzleType.PICTURE, question, solution);
       
    }

    @Override
    protected boolean isCorrect (String fixedAnswer) {
        return solution.contains(fixedAnswer);
    }

    
    public void addHint (String hint) {
         if (hint != null && !hint.isBlank()) 
            hints.add(hint);
    }

    public List<String> getHints () {
        return hints;
        
    }

    public String nextHint() {
        if (hints.isEmpty()) return null;
        int used = getHintsUsed();
        if (used >= hints.size()) return null; //  checks to see if user didn't reach max hints

        String h = hints.get(used); //  returns the hint at that value
        super.increaseHintUsed(); //  increments the amount of hints used when user ask for hint
        return h; //  returns hint
    }

    
}
