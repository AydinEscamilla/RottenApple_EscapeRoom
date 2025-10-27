/**
 * @Author: Rotten Apple
 * CSCE247
 */
package com.model;

import java.util.ArrayList;
import java.util.List;

/*
 * Repository of all users in the Escape Room System
 * loaded via {@link DataLoader} and persitted via {@link DataWriter}
 * Stores multiple players information in a List.
 * Useful data that fulfills Leaderboard
 */

public class Users {
    private static Users instance;
    private ArrayList<User> userList;

    /*
     * Consructs the repository and loads user from JSON storage
     */
    private Users() {
        // instantiate DataLoader (its loadUsers() is non-static)
        DataLoader loader = new DataLoader();
        List<User> loaded = loader.getUsers();
        userList = new ArrayList<>(loaded);
    }

    /*
     * @return an instance of a User
     */

    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    /*
     * Verifies User exist by username
     * 
     * @param username to be searched for 
     * @returns {@code true} if the User in list does exist and {@code false} otherwise.
     */
    public boolean haveUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Finds user by username
     * @oaram username to be returned
     * @returns the matching {@link User} if they do exist, null if user not found.
     */

    public User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /*
     * Returns a view of all Users
     * 
     * @returns the list of Users
     */
    public ArrayList<User> getUsers() {
        return userList;
    }

    /*
     * Adds User to the list if they are not already a existing User
     * @param user to be added into list
     * 
     * @return {@code true} if added, {@code false} if a user with the same username exist
     */
    // changed to accept a User object (preferred)
    public boolean addUser(User user) {
        if (haveUser(user.getUsername())) {
            return false;
        }
        userList.add(user);
        return true;
    }

    /*
    * Useful overload to create and add user
     * @param username to be added
     * @param password to be associated with user
     * 
     * @return {@code true} if added, {@code false} if a user with the same username exist
     */
    // helper that matches previous usage if you still call addUser(username,password)
    public boolean addUser(String username, String password) {
        return addUser(new User(username, password));
    }

    /*
     * Saves Users to storage
     */
    public void saveUsers() {
        DataWriter.saveUsers();
    }
}
