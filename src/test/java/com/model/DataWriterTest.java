package com.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

    // Class to test the DataWriter system
public class DataWriterTest {
    private Users users = Users.getInstance();
    private ArrayList<User> userList = users.getUsers();

    @BeforeEach
    public void setup() {
        Users.getInstance().getUsers().clear();
        DataWriter.saveUsers();
    }

    @AfterEach
    public void tearDown() {
        Users.getInstance().getUsers().clear();
        DataWriter.saveUsers();
        
    }

    // This tests writing an empty list and ensures data loader loads an empty list
    // Currently: Passes
    @Test
    void testWritingZeroUsers () {
        userList = DataLoader.getUsers();
        assertEquals(0, userList.size());
    }
    
    // This tests writing an empty user to a json file
    // Currently: Passes
    @Test
    void testWritingEmptyUser() {
        userList.add(new User("", ""));
        DataWriter.saveUsers();
        var loadedUsers = DataLoader.getUsers();
        assertEquals("",loadedUsers.get(0).getUsername());
    }

    // This tests writing and saving a user
    // Currently: Passes
    @Test
    void testWritingOneUser() {
        userList.add(new User("user1", "pw1"));
        DataWriter.saveUsers();
        assertEquals("user1", DataLoader.getUsers().get(0).getUsername());
    }

    // This test adds a user with an empty password 
    // Currently: Fails 
    @Test
    void testEmptyPassword() {
        userList.add(new User("user1", ""));
        
        DataWriter.saveUsers();

        assertEquals("0", DataLoader.getUsers().size());

    }

    // This test adds a user with an empty username
    // Currently: Fails
    @Test
    void testEmptyUsername() {
        userList.add(new User("", "pw1"));
        DataWriter.saveUsers();

        assertEquals("0", DataLoader.getUsers().size());

    }

    // This tests if datawriter overwrites old data or adds
    // Currently: Passes
    @Test
    void testOverwriteNotAppend() {

        userList.add(new User("user1", "pw1"));
        userList.add(new User("user2", "pw2"));

        Users.getInstance().getUsers().clear();
        DataWriter.saveUsers();

        assertEquals(0, DataLoader.getUsers().size());
    }

    // This tests saving the same user twice 
    // Currently: Passes
    @Test
    void testDoubleSaves() {
        userList.add(new User("user1","pw1"));
        DataWriter.saveUsers();
        DataWriter.saveUsers();
        var loadedUsers = DataLoader.getUsers();
        assertEquals(1, loadedUsers.size());
        assertEquals("user1", loadedUsers.get(0).getUsername());
    }
    // This tests saving duplicate usernames
    // Currently: Fails
    @Test
    void testDuplicateUsernames() {
        userList.add(new User("user1", "pw1"));
        userList.add(new User("user1", "pw12"));
        DataWriter.saveUsers();
        var loadedUsers = DataLoader.getUsers();

        assertEquals(1, loadedUsers.size());
        assertEquals("pw1", loadedUsers.get(0).getPassword());
        

    }


}
