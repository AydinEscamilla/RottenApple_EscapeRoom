

import java.util.ArrayList;
import java.util.List;

public class PicturePuzzle extends Puzzle {
    private List <String> imagePaths = new ArrayList<>();

    public PicturePuzzle(int puzzleID, String question, String solution, List<String> images) {
        super(puzzleID, PuzzleType.PICTURE, question, solution);
        if (images != null) imagePaths.addAll(images);
       
    }

    @Override
    protected boolean isCorrect (String input) {
        String playerAnswer = FixedString(input);
        String correctAnswer = FixedString(getSolution());
        return playerAnswer.equals(correctAnswer); //  compare the answers
    }

    public List<String> getImagePaths() {
        return List.copyOf(imagePaths);
    }

    public static PicturePuzzle FingerPrintMatch() {
    var p1 = new PicturePuzzle( 
        301,
        "Based on the sample print, which fingerprint mathces?",
        "B",
        List.of("images/Question-Print.png", "images/Incorrect-Print-1", "images/Correct-Print", "images/Incorrect-Print-2")
    );
    
    p1.addHint("Look at the ridge patterns.");
    p1.addHint("Focus on the center area of the prints.");
    return p1;
  
}

public static PicturePuzzle CameraPuzzle() {
    var p2 = new PicturePuzzle(
        302,
        "Which color car has the plate 395833?",
        "White",
        List.of("images/Correct-Car.png", "Incorrect-Car-1.png", "Incorrect-Car-2.png")

    );

    p2.addHint("Look closely at the license plates.");
    p2.addHint("Focus on the numbers");
    return p2;
}

public static PicturePuzzle MissingBadge() {
    var p3 = new PicturePuzzle(
        303,
        "Who's missing their badge?",
        "Left",
        List.of("images/cop-badge.png")
    );

    p3.addHint("Look towards the right side of the chest.");

    return p3;


}

    
}




