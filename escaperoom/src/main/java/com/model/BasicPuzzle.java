package com.model;

import java.util.List;

public class BasicPuzzle extends Puzzle {
    public BasicPuzzle(int puzzleID,
                       String puzzleTypeStr,
                       String question,
                       String solution,
                       List<String> hints,
                       String imagePath,
                       boolean isSolved,
                       int attempts,
                       int maxAttempts,
                       int scoreValue,
                       int hintUsedCount,
                       Difficulty difficulty) {
        super(puzzleID,
              Puzzle.PuzzleType.valueOf(puzzleTypeStr.toUpperCase()),
              question,
              solution,
              hints,
              imagePath,
              isSolved,
              attempts,
              maxAttempts,
              scoreValue,
              hintUsedCount,
              difficulty);
    }

    @Override
    public void addHint(String hint) {
        // Puzzle.hints is private — if Puzzle doesn't provide an accessor,
        // either make hints protected or provide a protected/addHint method in Puzzle.
        // If Puzzle has no way to add hints from subclass, change Puzzle.hints to protected
        // or add a protected helper in Puzzle.
    }
}
