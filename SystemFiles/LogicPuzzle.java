
public  class LogicPuzzle extends Puzzle {
    
    /*
     * Calls Puzzle Parent, gives it one solution string
     * 
     */
    public LogicPuzzle(int puzzleID, String question, String solution) {
        super(puzzleID, PuzzleType.LOGIC, question, solution);
       
    }

    /*
     * Calls parent attempt method and 
     * @return true if player answer exist in accepted set
     */
    @Override
    protected boolean isCorrect (String input) {
        String playerAnswer = FixedString(input);
        String correctAnswer = FixedString(getSolution());
        return playerAnswer.equals(correctAnswer); //  compare the answers
    }

    
    public static LogicPuzzle DuckPuzzle() {
        var lp1 = new LogicPuzzle(
            101,
            "There are two ducks in front of a duck, two ducks behind a duck and a duck in the middle. How many ducks are there?",
            "Three"
        );
        lp1.grantItem(ItemRegistry.Camera_Passcode.getItemID());
        
        lp1.addHint("Consider the amount of ducks that ACTUALLY exist.");
        return lp1;
    }

    public static LogicPuzzle DayPuzzle() {
         var lp2 = new LogicPuzzle (
            102,
            "The day before two days after the day before tomorrow is Saturday. What day is it today?",
           "Friday"
        );

        lp2.addHint("Day before tomorrow is today.");
        lp2.addHint("'The day before two days after' is really one day after");
        return lp2;


    }

   
}


class LogicPuzzleTest {
    public static void main(String[] args) {
        var lp1 = new LogicPuzzle(
            101,
            "There are two ducks in front of a duck, two ducks behind a duck and a duck in the middle. How many ducks are there?",
        "Three"
        );
        
        lp1.addHint("Consider the amount of ducks that ACTUALLY exist.");
        System.out.println(lp1.attempt("Two"));
        System.out.println(lp1.nextHint());
        System.out.println(lp1.attempt("Three"));

        var lp2 = new LogicPuzzle (
            102,
            "The day before two days after the day before tomorrow is Saturday. What day is it today?",
           "Friday"
        );

        lp2.addHint(null);






    }
}
