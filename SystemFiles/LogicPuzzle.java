

import java.util.List;
import java.util.Scanner;

import SystemFiles.Puzzle;

public class LogicPuzzle extends Puzzle {
    

    private List <String> hints;

    public LogicPuzzle(int puzzleID, String question, String solution) {
        super(puzzleID, question, solution);
       
    }

    public List<String> getHints () {
        return hints;
        
    }

    public void addHint (String hint) {
        Scanner scanner = new Scanner(System.in);
        hint = scanner.nextLine();
        System.out.println(hint);
        scanner.close();
        
    }
}
