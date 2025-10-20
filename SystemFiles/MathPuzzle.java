

import java.util.List;
import java.util.Scanner;

//import SystemFiles.Puzzle;

public class MathPuzzle extends Puzzle {
    private List <String> hints;

    public MathPuzzle(int puzzleID, String question, String solution) {
        super(puzzleID, question, solution);
       
    }

    public void addHint (String hint) {
         if (hint != null && !hint.isBlank()) 
            hints.add(hint);
    }

    public List<String> getHints () {
        return hints;
        
    }

    /*
     * 
     * @return the next hint based on how many user already used or null if there are no more
     */
    public String nextHint() {
        if (hints.isEmpty()) return null;
        int used = getHintsUsed();
        if (used >= hints.size()) return null; //  checks to see if user didn't reach max hints

        String h = hints.get(used); //  returns the hint at that value
        super.increaseHintUsed(); //  increments the amount of hints used when user ask for hint
        return h; //  returns hint
    }

  
}
