package com.model;

import java.util.List;
import java.util.Scanner;

//import SystemFiles.Puzzle;

public class MathPuzzle extends Puzzle {
    private List <String> hints;

    public MathPuzzle(int puzzleID, String question, String solution) {
        super(puzzleID, question, solution);
       
    }

    public List<String> getHints () {
        return hints;
        
    }

    public void addHint (String hint) {
        System.out.println("Add a hint for Math Puzzle");
        Scanner scanner = new Scanner(System.in);
        hint = scanner.nextLine();
        System.out.println(hint);
        scanner.close();
        
    }
}
