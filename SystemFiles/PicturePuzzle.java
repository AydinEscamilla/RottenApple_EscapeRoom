
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


//import SystemFiles.Puzzle;

public class PicturePuzzle extends Puzzle {
    private List <String> hints;
    // private List <ImageIcon> images;
    private String imagePath;

    public PicturePuzzle(int puzzleID, String question, String solution, String imagePath) {
        super(puzzleID, PuzzleType.PICTURE, question, solution);
       
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

    public String nextHint() {
        if (hints.isEmpty()) return null;
        int used = getHintsUsed();
        if (used >= hints.size()) return null; //  checks to see if user didn't reach max hints

        String h = hints.get(used); //  returns the hint at that value
        super.increaseHintUsed(); //  increments the amount of hints used when user ask for hint
        return h; //  returns hint
    }

          

      //  FingerPrint Game
    public List<ImageIcon> fingerPrint () {
        List <ImageIcon> fingerPrint = new ArrayList<>();
        ImageIcon questionPrint = new ImageIcon("images/Question-Print.png");
        ImageIcon correctPrint = new ImageIcon("images/Correct-Print.png");
        ImageIcon incorrectPrint1 = new ImageIcon("images/Incorrect-Print1.png");
        ImageIcon incorrectPrint2 = new ImageIcon("images/Incorrect-Print2.png");

        fingerPrint.add(0, questionPrint); //  wrpng
        fingerPrint.add(1, incorrectPrint1);
        fingerPrint.add(2, incorrectPrint2);
        fingerPrint.add(3, correctPrint);

        return fingerPrint;


        
    }

    //  Security Camera Game
    public List<ImageIcon> CameraFootage () {

        question = "Officer Crook was driving a white car what is the license plate?";
        solution = "395833";

        List <ImageIcon> CameraFootage = new ArrayList<>();
        ImageIcon correctCar = new ImageIcon("images/Correct-Car.png");
        ImageIcon incorrectCar1 = new ImageIcon("images/Incorrect-Car1.png");
        ImageIcon incorrectCar2 = new ImageIcon("images/Incorrect-Car2.png");

        CameraFootage.add(0, correctCar); 
        CameraFootage.add(1, incorrectCar1);
        CameraFootage.add(2, incorrectCar2);

        return CameraFootage;


        
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame("Image Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

   


    
}


