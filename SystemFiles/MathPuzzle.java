

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MathPuzzle extends Puzzle {
private List <String> hints = new ArrayList<>();

    public MathPuzzle(int puzzleID, String question, String solution) {
        super(puzzleID, PuzzleType.MATH, question, solution);
       
    }

    protected boolean isCorrect (String soluton) {
        int answer = Integer.parseInt(soluton); //  turning solution to integer

        return solution.contains(answer);
    }

    //  Universal Lock that'll show on UI
    public int LockPuzzzle (String solution) {
        int a; //  first spot in lock
        int b; //  second spot
        int c; //  third spot
        return 0;
    }


    //  Creates a Alphabet Decoder
    public Map DecodePattern() {
        Map<Character, Integer> alphabetToNumber = new HashMap<>();
        Random random = new Random();
        int x = random.nextInt(26);

        for (char ch = 'a'; ch <= 'z'; ch++) {
            alphabetToNumber.put(ch, ch - 'a' + x);
        }
    }

    //  Used for Clock Puzzles
    public void generateTime() {
        Random random = new Random();

        //  Generate a random hour 
        int hour = random.nextInt(24);

        int minute = random.nextInt(60);

        int second = random.nextInt(60);

        LocalTime randomTime = LocalTime.of(hour, minute, second);


    

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
