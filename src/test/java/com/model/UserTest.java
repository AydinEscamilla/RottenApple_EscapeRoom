package com.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

// Class to test the User class
public class UserTest {

   // This test is to make sure the testing system works properly
   // Currently: Passes
    @Test
    public void testFrameworkSanity() {
        assertTrue(true);
    }

    // This test is to confirm that user can store username and password
    // Currently: Passes
    @Test
    public void testConstructorStoresFields() {
        User u = new User("Alice", "Wonderland");
        assertEquals("Alice", u.getUsername());
        assertEquals("Wonderland", u.getPassword());
    }

    // This test confirms that the user system can match usernames and passwords
    // Currently: Passes
    @Test
    public void testUserMatchBehavior() {
        User u = new User("TestUser", "secret");
        assertTrue("Correct credentials should pass userMatch()",
                   u.userMatch("TestUser", "secret"));
        assertFalse("Wrong username should fail userMatch()",
                    u.userMatch("WrongUser", "secret"));
        assertFalse("Wrong password should fail userMatch()",
                    u.userMatch("TestUser", "wrong"));
    }

    // This test confirms that matching a users username and password is case sensitive
    // Currently: Passes
    @Test
    public void testUserMatchCaseSensitivity() {
        User u = new User("CaseUser", "Password123");
        assertFalse("Username comparison should be case-sensitive",
                    u.userMatch("caseuser", "Password123"));
        assertFalse("Password comparison should be case-sensitive",
                    u.userMatch("CaseUser", "password123"));
    }

    // This test confirms that each user gets a unique ID
    // Currently: Passes
    @Test
    public void testUniqueUUIDs() {
        User u1 = new User("UserA", "pw");
        User u2 = new User("UserB", "pw");
        assertNotEquals("Each user should have a unique UUID",
                        u1.getUUID(), u2.getUUID());
    }

    // This test ensures that users start with an empty inventory and can add items
    // Currently: Passes
    @Test
    public void testAddItem() {
        User u = new User("ItemUser", "pw");
        assertTrue("New user should start with empty inventory", u.getItems().isEmpty());
        u.addItem("key");
        assertTrue("Item list should contain 'key'", u.getItems().contains("key"));
    }

    // This test ensures that null parameters return false when matching user info
    // Currently: Passes
    @Test
    public void testUserMatchNullSafety() {
        User u = new User("SafeUser", "pw");
        assertFalse("Null username should return false", u.userMatch(null, "pw"));
        assertFalse("Null password should return false", u.userMatch("SafeUser", null));
    }

    // This test checks that hints used are tracked properly
    // Currently: Passes
    @Test
    public void testHintUsageMap() {
        User u = new User("HintTester", "pw");
        assertEquals(0, u.getHintsUsedForPuzzle(1));
        u.incrementHintUsed(1);
        assertEquals(1, u.getHintsUsedForPuzzle(1));
        u.incrementHintUsed(1);
        assertEquals(2, u.getHintsUsedForPuzzle(1));
    }

    // This test confirms that new users start with empty saves 
    // Currently: Passes
    @Test
    public void testInitialCompletedPuzzlesEmpty() {
        User u = new User("PuzzleUser", "pw");
        assertTrue(u.getPuzzlesComplete().isEmpty());
    }


    // This test adds a null item to the players inventory
    // Currently: Fails
    @Test
    public void testAddNullItemHandledGracefully() {
        User u = new User("NullItemUser", "pw");
        u.addItem(null);
        assertFalse("Null item should not be added to inventory", u.getItems().contains(null));
    }

    // This test checks if a UUID that is null can be handled by the program
    // Currently: Fails
    @Test
    public void testNullUUIDAccessSafety() throws Exception {
        User u = new User("uuidUser", "pw");
        java.lang.reflect.Field f = User.class.getDeclaredField("uuid");
        f.setAccessible(true);
        f.set(u, null);
        assertNotNull("getUUID should never return null", u.getUUID());
    }

    // This test checks how the program handles a negative hint count in the puzzle
    // Currently: Fails
    @Test
    public void testIncrementHintUsedNegativeId() {
        User u = new User("HintNeg", "pw");
        u.incrementHintUsed(-1);
        assertFalse("Hints map should not accept negative IDs",
                    u.getHintsUsedMap().containsKey(-1));
    }

    // This tests that the program can handle larger puzzle ID
    // Currently: Passes
    @Test
    public void testGetHintsUsedExtremePuzzleID() {
        User u = new User("BigHintUser", "pw");
        int bigID = Integer.MAX_VALUE;
        u.incrementHintUsed(bigID);
        assertEquals("Should record hint for large puzzleID safely",
                     1, u.getHintsUsedForPuzzle(bigID));
    }

    // This test checks how the program will handle duplicate items in the users inventory
    // Currently: Fails
    @Test
    public void testDuplicateItemsNotAllowed() {
        User u = new User("DupUser", "pw");
        u.addItem("key");
        u.addItem("key");
        long uniqueCount = u.getItems().stream().distinct().count();
        assertEquals("Duplicate items should not exist in inventory",
                     uniqueCount, u.getItems().size());
    }
}