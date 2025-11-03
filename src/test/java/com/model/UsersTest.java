package com.model;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Ignore;

/**
 * Class to test Users system
 */

public class UsersTest {
    // To test that the testing is working 
    @Test
    public void TestTesting() {
        assertTrue(true);
    }
    
    /**
     * Checks that the scenario user is saved (Should Return True)
     * Currently: Passes
     */
    @Test
    public void testHaveUserValid() {
        Users users = Users.getInstance();
        boolean exists = users.haveUser("LRivers");
        assertTrue("Expected LRivers to exist in the system", exists);
    }
    
    /**
     * Checks that a user is not saved (Should Return False)
     * Currently: Passes
     */
    @Test
    public void testHaveUserInvalid() {
        Users users = Users.getInstance();
        boolean exists = users.haveUser("DefinitelyNotAUser");
        assertFalse("Expected non-existent user to return false", exists);
    }

    /**
     * Test to add a new valid User (Should Return True)
     * Currently: Passes
     */
    @Test
    public void testAddValidUser() {
        Users users = Users.getInstance();
        boolean added = users.addUser("TempTestUser123", "password123");
        assertTrue("Expected TempTestUser123 to be added successfully", added);
    }

    /**
     * Test to Add a Duplicate username user (Should return False)
     * Currently: Passes
     */
    @Test
    public void testAddDuplicateUser() {
        Users users = Users.getInstance();
        users.addUser("DupTestUser", "abc123");
        boolean addedAgain = users.addUser("DupTestUser", "xyz789");
        assertFalse("Expected duplicate username addition to fail", addedAgain);
    }

    /**
     * Test to get user when user does not exist (Should return null)
     * Currently: Passes
     */
    @Test
    public void testGetUser_WhenUserDoesNotExist_ReturnsNull() {
        Users users = Users.getInstance();
        User u = users.getUser("FakePersonXYZ");
        assertNull("Expected null when fetching nonexistent user", u);
    }

    /**
     * Test to add a user when the username is blank (Should return False)
     * Currently: Fails
     */
    @Test
    public void testAddBlankUsername() {
        Users users = Users.getInstance();
        boolean added = users.addUser("", "pass");
        assertFalse("Expected blank username addition to fail", added);
    }

    /**
     * Test to add user when the password is blank (Should return False)
     * Currently: Fails
     */
    @Test
    public void testAddBlankPassword() {
        Users users = Users.getInstance();
        boolean added = users.addUser("BlankPassUser", "");
        assertFalse("Expected user creation to fail with blank password", added);
    }

    /**
     * Test to make sure Users are still saved between runs
     * Currently: Passes
     */
    @Test
    public void testPersistence() {
        Users users = Users.getInstance();
        users.addUser("PersistentUser", "p123");
        users.saveUsers();

        Users reloaded = Users.getInstance();
        boolean exists = reloaded.haveUser("PersistentUser");
        assertTrue("Expected PersistentUser to exist after reloading", exists);
    }
}
