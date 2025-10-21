

import java.util.ArrayList;
import java.util.List;

public class MathPuzzle extends Puzzle {
private List <String> hints = new ArrayList<>();

    public MathPuzzle(int puzzleID, String question, String solution) {
        super(puzzleID, PuzzleType.MATH, question, solution);
       
    }

    protected boolean isCorrect (String fixedAnswer) {
        
        return accepted.contains(fixedAnswer);
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

class MathPuzzleTest {
    public static void main(String[] args) {
        var mp1 = new MathPuzzle(
        102,
        "Solve the problems to get in the lock. 18 / 3 - 7 + 2 * 5, 12^2 / 3 - 2 * 7, 4^2 * 3 * (11 - 9) ",
        "93496"
        );
        
        mp1.addHint("Remember PEMDAS");
        mp1.addHint("The P in Pemdas stands for Parentheseas");
        System.out.println(mp1.attempt("42"));
        System.out.println(mp1.nextHint());
        System.out.println(mp1.attempt("536"));
        System.out.println(mp1.nextHint());
        System.out.println(mp1.attempt("93496"));


    }
}
