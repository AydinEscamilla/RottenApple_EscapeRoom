
public class MathPuzzle extends Puzzle {


    public MathPuzzle(int puzzleID, String question, String solution) {
        super(puzzleID, PuzzleType.MATH, question, solution);
       
    }

    @Override
    protected boolean isCorrect (String fixedAnswer) {
        return solution.contains(fixedAnswer);
    }



    public static MathPuzzle PemdasLock() {
        var mp1 = new MathPuzzle(
        201,
        "I'm going to need a tool to track this guy, let me open the lock on the toolbox...'Solve the problems to get in the lock. 10+10+5' ",
        "25"
        );
        mp1.grantItem(ItemRegistry.Magnifying_Glass.getItemID());
        
        mp1.addHint("10+10 is 20");
        mp1.addHint("10+5 is 15");
        return mp1;

    }

    public static MathPuzzle AmmoBox() {
        var mp2 = new MathPuzzle(
        202,
        "Let me inspect these tags carefully looks like someone tampered with them...'Ammo log: Mags with ammo of 10, 7, 12 respectivley were checked out ; report says only 5 shots were used in the firing range.  (10 + 7 + 12) − 5' How many unused bullets are left?' ",
        "24"
        );
        mp2.requireItem(ItemRegistry.Magnifying_Glass.getItemID());

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

