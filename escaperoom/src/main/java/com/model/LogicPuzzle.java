package com.model;

import java.util.List;
import java.util.Set; 
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.stream.Collectors;

import Puzzle;
import Puzzle.PuzzleType;

import java.util.Iterator;

public  class LogicPuzzle extends Puzzle {
    

    private List <String> hints = new ArrayList<>();
    private Set <String> accepted;
    
    /*
     * Calls Puzzle Parent, gives it one solution string, but keeps a Collection of answers to check if a answer is correct
     * 
     */
    public LogicPuzzle(int puzzleID, String question, Collection <String> acceptedAnswers) {
        super(puzzleID, PuzzleType.LOGIC, question, 
        // condition ? valueIfTrue : valueIfFalse
        //  If we have a null or empty string, we pass a empty one for a placeholder
        //  Else, we take the first element and pass it to the parent, LP will check internally
        acceptedAnswers == null || acceptedAnswers.isEmpty() ? "" : acceptedAnswers.iterator().next()); 
                                                                            

        if (acceptedAnswers == null) acceptedAnswers = List.of(); //  if a null is passed, given a default list, so the rest of code can assume not null
        this.accepted = acceptedAnswers.stream() //  Turns user input into a Stream of Strings
            .map(a -> a == null ? "" : a.trim().toLowerCase()) 
            .collect(Collectors.toCollection(HashSet::new));  //  removes duplicates, and check for correctness

       
    }

    /*
     * Calls parent attempt method and 
     * @return true if player answer exist in accepted set
     */
    @Override
    protected boolean isCorrect (String fixedAnswer) {
        return accepted.contains(fixedAnswer);
    }

    /*
     * Checks if hint value is null, then adds it to the list
     */
    public void addHint (String hint) {
        if (hint != null && !hint.isBlank()) 
            hints.add(hint);
    }

   /*
    * @return the hints for puzzle
    */
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

    public static LogicPuzzle DuckPuzzle() {
        var lp1 = new LogicPuzzle(
            101,
            "There are two ducks in front of a duck, two ducks behind a duck and a duck in the middle. How many ducks are there?",
            List.of("Three", "3")
        );
        
        lp1.addHint("Consider the amount of ducks that ACTUALLY exist.");
        return lp1;
    }

    public static LogicPuzzle DayPuzzle() {
         var lp2 = new LogicPuzzle (
            102,
            "The day before two days after the day before tomorrow is Saturday. What day is it today?",
            List.of("Friday", "friday")
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
            List.of("Three", "3") 
        );
        
        lp1.addHint("Consider the amount of ducks that ACTUALLY exist.");
        System.out.println(lp1.attempt("Two"));
        System.out.println(lp1.nextHint());
        System.out.println(lp1.attempt("Three"));

        var lp2 = new LogicPuzzle (
            102,
            "The day before two days after the day before tomorrow is Saturday. What day is it today?",
            List.of("Friday", "friday")
        );

        lp2.addHint(null);






    }
}
