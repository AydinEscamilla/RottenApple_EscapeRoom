package com.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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

    @Test
    void testWritingZeroUsers () {
        userList = DataLoader.getUsers();
        assertEquals(0, userList.size());
    }

    @Test
    void testWritingEmptyUser() {
        userList.add(new User("", ""));
        DataWriter.saveUsers();
        var loadedUsers = DataLoader.getUsers();
        assertEquals("",loadedUsers.get(0).getUsername());
    }

    @Test
    void testWritingOneUser() {
        userList.add(new User("user1", "pw1"));
        DataWriter.saveUsers();
        assertEquals("user1", DataLoader.getUsers().get(0).getUsername());
    }

    @Test
    void testEmptyPassword() {
        userList.add(new User("user1", ""));
        
        DataWriter.saveUsers();

        assertEquals("0", DataLoader.getUsers().size());

    }

    @Test
    void testEmptyUsername() {
        userList.add(new User("", "pw1"));
        DataWriter.saveUsers();

        assertEquals("0", DataLoader.getUsers().size());

    }

    @Test
    void testOverwriteNotAppend() {
        //  write 2 users
        userList.add(new User("user1", "pw1"));
        userList.add(new User("user2", "pw2"));

        // clear in-memory list and save again (should write an empty file)
        Users.getInstance().getUsers().clear();
        DataWriter.saveUsers();

        // load should be empty if writer overwrites
        assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    void testDoubleSaves() {
        userList.add(new User("user1","pw1"));
        DataWriter.saveUsers();
        DataWriter.saveUsers(); //  same state, should not duplicate
        var loadedUsers = DataLoader.getUsers();
        assertEquals(1, loadedUsers.size());
        assertEquals("user1", loadedUsers.get(0).getUsername());
    }

    @Test
    void testDuplicateUsernames() {
        userList.add(new User("user1", "pw1"));
        userList.add(new User("user1", "pw12"));
        DataWriter.saveUsers();
        var loadedUsers = DataLoader.getUsers();

        //  want to get just one user
        assertEquals(1, loadedUsers.size());
        assertEquals("pw1", loadedUsers.get(0).getPassword());
        

    }


}
