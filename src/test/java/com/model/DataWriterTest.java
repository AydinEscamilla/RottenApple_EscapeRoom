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
    void testOverwriteNotAppend() {
        //  write 2 users
        userList.add(new User("tester1", "tester1Password"));
        userList.add(new User("tester2", "tester2Password"));

        // clear in-memory list and save again (should write an empty file)
        Users.getInstance().getUsers().clear();
        DataWriter.saveUsers();

        // load should be empty if writer overwrites
        assertEquals(0, DataLoader.getUsers().size());
    }


}