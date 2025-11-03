package com.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


// Class to test the Puzzle system

public class PuzzleTest {

    /**
     * Puzzle used for testing the Puzzle class
     */
    static class TestPuzzle extends Puzzle {
        public TestPuzzle(int id, PuzzleType type, String question, String solution) {
            super(id, type, question, solution);
        }

        @Override
        public void addHint(String hint) {
            if (hint == null || hint.isBlank()) return;
            try {
                List<String> hints = this.getHints();
                if (hints == null) {
                    java.lang.reflect.Field f = Puzzle.class.getDeclaredField("hints");
                    f.setAccessible(true);
                    f.set(this, new ArrayList<String>());
                    hints = this.getHints();
                }
                hints.add(hint);
            } catch (Exception e) {
                fail("Reflection error initializing hints: " + e.getMessage());
            }
        }

        @Override
        public Puzzle grantItem(String item) {
            if (item == null || item.isBlank()) return this;
            try {
                List<String> granted = this.getGrantedItems();
                if (granted == null) {
                    java.lang.reflect.Field f = Puzzle.class.getDeclaredField("grantedItems");
                    f.setAccessible(true);
                    f.set(this, new ArrayList<String>());
                }
                if (!this.getGrantedItems().contains(item)) {
                    this.getGrantedItems().add(item);
                }
            } catch (Exception e) {
                fail("Reflection error initializing grantedItems: " + e.getMessage());
            }
            return this;
        }
    }

    private TestPuzzle puzzle;

    @BeforeEach
    public void setUp() {
        puzzle = new TestPuzzle(
                1,
                Puzzle.PuzzleType.LOGIC,
                "What has keys but can't open locks?",
                "piano"
        );
    }

    // Test to confirm that correct answers mark the puzzle as solved
    @Test
    public void testAttemptCorrectAnswer() {
        assertTrue(puzzle.attempt("Piano"), "Answer 'Piano' should solve the puzzle (case insensitive).");
        assertTrue(puzzle.solved(), "Puzzle should be marked as solved after correct answer.");
    }

    // Test to confirm that wrong answers do not mark the puzzle as solved 
    // Currently: Passes
    @Test
    public void testAttemptWrongAnswer() {
        assertFalse(puzzle.attempt("keyboard"), "Wrong answer should not solve the puzzle.");
        assertFalse(puzzle.solved(), "Puzzle should remain unsolved after a wrong attempt.");
    }

    // Test to confirm that the puzzle transitions between stages properly
    // Currently: Passes
    @Test
    public void testGetStatusTransitions() {
        assertEquals(Puzzle.PuzzleStatus.NOT_STARTED, puzzle.getStatus(), "Should start NOT_STARTED.");
        puzzle.attempt("wrong");
        assertEquals(Puzzle.PuzzleStatus.IN_PROGRESS, puzzle.getStatus(), "After one wrong attempt should be IN_PROGRESS.");
        puzzle.giveUp();
        assertEquals(Puzzle.PuzzleStatus.FAILED, puzzle.getStatus(), "After giveUp() should be FAILED.");
    }

    // Test to confirm that the basic point value is assigned 
    // Currently: Passes
    @Test
    public void testScoreValueBasic() {
        puzzle.attempt("piano");
        assertEquals(10, puzzle.getScoreValue(), "Default score for one correct attempt should be 10.");
    }

    // Test to confirm if code accepts a null answer as an attempt
    // Currently: Fails
    @Test
    public void testAttemptWithNullAnswer() {
        puzzle.attempt(null);
        assertEquals(0, puzzle.getAttempts(), "Attempt count should not increase for null input.");
    }

    // Test to confirm that an empty answer will not count as an attempt
    // Currently: Fails
    @Test
    public void testAttemptWithEmptyAnswer() {
        puzzle.attempt("");
        assertEquals(0, puzzle.getAttempts(), "Blank input should not increase attempts.");
    }

    // Test to confirm that the required item request cannot be duplicated
    // Currently: Passes
    @Test
    public void testGrantAndRequireItemsNotDuplicated() {
        puzzle.requireItem("key");
        puzzle.requireItem("key");
        assertEquals(1, puzzle.getRequiredItems().stream().distinct().count(),
                "Duplicate required item entries found.");
    }

    // Test to confirm that correct answers are not case sensitive
    // Currently: Passes
    @Test
    public void testCaseInsensitiveAnswerCheck() {
        assertTrue(puzzle.attempt("PIANO"), "Puzzle should treat uppercase 'PIANO' as correct.");
    }

    // Test to confirm that correct answers with white space still solve the puzzle
    // Currently: Passes
    @Test
    public void testAnswerWithWhitespace() {
        assertTrue(puzzle.attempt("  piano  "), "Puzzle should trim whitespace before comparing answers.");
    }

    // Test to confirm that puzzle does not accept attempts above the max
    // Currently: Passes
    @Test
    public void testNoFurtherAttemptsAfterSolved() {
        puzzle.attempt("piano");
        int attemptsBefore = puzzle.getAttempts();
        puzzle.attempt("wrong");
        assertEquals(attemptsBefore, puzzle.getAttempts(), "Attempt count increased even after puzzle solved!");
    }

    // Test to confirm if puzzles can be solved without required items
    // Currently: Fails
    @Test
    public void testCannotSolveWithoutRequiredItems() {
        puzzle.requireItem("gold key");
        boolean solved = puzzle.attempt("piano");
        assertFalse(solved, "Puzzle was solved without required items present — logic bug!");
    }

    // Test to confirm if duplicate rewards can be granted and held
    // Currently: Fails
    @Test
    public void testRewardsNotDuplicated() {
        puzzle.grantItem("coin");
        int before = puzzle.getGrantedItems().size();
        puzzle.grantItem("coin");
        int after = puzzle.getGrantedItems().size();
        assertEquals(before, after, "Rewards were duplicated after granting the same item twice!");
    }
}
