package com.model;

import java.util.List;
import java.util.Scanner;

//import SystemFiles.Puzzle;

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
        System.out.println("Hint for Picture Puzzle");
        Scanner scanner = new Scanner(System.in);
        hint = scanner.nextLine();
        System.out.println(hint);
        scanner.close();
        
    }

    
}
