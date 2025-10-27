/**
 * @Author: Rotten Apple
 * CSCE247
 */

package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A basic {@link Puzzle} implementation, that stores Puzzle details such as
 * question, solution, hints, images, attempts, scoring and difficultu
 * 
 */

public class BasicPuzzle extends Puzzle {

     /**
     * Constructs a {@code BasicPuzzle}.
     *
     * @param puzzleID      unique identifier for the puzzle
     * @param puzzleTypeStr string name of the {@link PuzzleType}; case-insensitive
     * @param question      the question prompt shown to the player
     * @param solution      the expected solution value
     * @param hints         optional list of hints (may be {@code null})
     * @param imagePaths    optional list of image asset paths (may be {@code null})
     * @param isSolved      initial solved state
     * @param attempts      initial attempt count
     * @param maxAttempts   maximum allowed attempts; negative or zero may indicate “unlimited”
     * @param scoreValue    score awarded upon solving
     * @param hintUsedCount initial count of hints already used
     * @param difficulty    difficulty rating
     * @param item          optional item granted/referenced by this puzzle (may be {@code null})
     * @throws NullPointerException if {@code puzzleTypeStr}, {@code question}, {@code solution}, or {@code difficulty} is {@code null}
     * @throws IllegalArgumentException if {@code puzzleTypeStr} is not a valid {@link PuzzleType}
     */

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

    /*
     * Checks if hint is null, trims leading/trailing space
     * checks if hint is not blank, adds to list.
     */
    @Override
    public void addHint(String hint) {
        if (hint == null || hint.trim().isEmpty()) return;
        List<String> hints = getHints(); // assumes Puzzle has a getHints() method returning List<String>
        if (hints != null) {
            hints.add(hint);
        }
    }
}
