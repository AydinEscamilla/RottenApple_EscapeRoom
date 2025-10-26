
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MathPuzzle extends Puzzle {
private List <String> hints = new ArrayList<>();

    public MathPuzzle(int puzzleID, String question, String solution) {
        super(puzzleID, PuzzleType.MATH, question, solution);
       
    }

    public MathPuzzle requires (int... ids) {
       for (int id : ids) {
            requireItem(id);
       }
       return this;
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

    public static MathPuzzle PemdasLock() {
        var mp1 = new MathPuzzle(
        102,
        "Solve the problems to get in the lock. 18 / 3 - 7 + 2 * 5, 12^2 / 3 - 2 * 7, 4^2 * 3 * (11 - 9) ",
        "93496"
        );
        // .grants(ItemRegistry.Magnifying_Glass.getItemID());
        
        mp1.addHint("Remember PEMDAS");
        mp1.addHint("The P in Pemdas stands for Parentheseas");
        return mp1;

    }

    public static MathPuzzle AmmoBox() {
        var mp2 = new MathPuzzle(
        102,
        "Ammo log: boxes 10, 7, 12; report says 5 were used. Enter (10 + 7 + 12) − 5",
        "6"
        );
        // .requires(ItemRegistry.Magnifying_Glass.getItemID());

        mp2.addHint("Add first, then subtract");
        return mp2;
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

        var mp2 = new MathPuzzle(
        103,
        "Ammo log: boxes 10, 7, 12; report says 5 were used. Enter (10 + 7 + 12) − 5",
        "6"
        );

        mp2.addHint("Add first, then subtract");


    }
}

//  //  Universal Lock that'll show on UI
//     public int LockPuzzzle (String solution) {
//         int a; //  first spot in lock
//         int b; //  second spot
//         int c; //  third spot
//         return 0;
//     }


//     //  Creates a Alphabet Decoder
//     public Map<Character, Integer> DecodePattern() {
//         Map<Character, Integer> alphabetToNumber = new HashMap<>();
//         Random random = new Random();
//         int shift = random.nextInt(26);

//         for (char ch = 'a'; ch <= 'z'; ch++) {
//             int base1to26 = (ch - 'a' + 1);
//             int shifted1to26 = ((base1to26 - 1 + shift) % 26) +1;
//             alphabetToNumber.put(ch, shifted1to26);
//         }
//         return alphabetToNumber;
//     }

//     //  Used for Clock Puzzles
//     public  LocalTime generateTime() {
//         Random random = new Random();

//         //  Generate a random hour 
//         int hour = random.nextInt(24);

//         int minute = random.nextInt(60);

//         int second = random.nextInt(60);

//         return LocalTime.of(hour, minute, second);


    

//     }

