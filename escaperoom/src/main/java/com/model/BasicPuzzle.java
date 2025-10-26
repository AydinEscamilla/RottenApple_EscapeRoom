package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BasicPuzzle extends Puzzle {

    public BasicPuzzle(int puzzleID,
                       String puzzleTypeStr,
                       String question,
                       String solution,
                       List<String> hints,
                       List<String> imagePaths,
                       boolean isSolved,
                       int attempts,
                       int maxAttempts,
                       int scoreValue,
                       int hintUsedCount,
                       Difficulty difficulty,
                       String item) {

        super(
            puzzleID,
            Puzzle.PuzzleType.valueOf(Objects.requireNonNull(puzzleTypeStr).toUpperCase()),
            question,
            solution,
            hints != null ? hints : new ArrayList<>(),
            imagePaths != null ? imagePaths : new ArrayList<>(),
            isSolved,
            attempts,
            maxAttempts,
            scoreValue,
            hintUsedCount,
            difficulty,
            item
        );
    }

    @Override
    public void addHint(String hint) {
        if (hint == null || hint.trim().isEmpty()) return;
        List<String> hints = getHints(); // assumes Puzzle has a getHints() method returning List<String>
        if (hints != null) {
            hints.add(hint);
        }
    }
}
