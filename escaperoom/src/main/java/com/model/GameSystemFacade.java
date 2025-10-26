package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameSystemFacade {

    private GameSystemFacade facade;

    public static void main(String[] args) { 
        GameSystemFacade facade = new GameSystemFacade(); 
    }

    public User signup(String username, String password) {
        User newUser = new User(username, password);
        boolean added = true;
        added = Users.getInstance().addUser(newUser);

        if (added == true) {
            return newUser;
        } else {
            return null;
        }
    }

    public User startNewGame(User user) {
        System.out.print(user.getUsername());
        return null;
    }

    public User loadUser(String username) {return null;}
    
    public User getCurrentUser() {
        return null;
    }

    public void logout() {
        users.saveUsers();
    }

    public List<String> getAllUsernames() {
        return null;
    }
    /*
    public Settings changeSetting(User user) { return null; }
    public Game startNewGame(User user) { return null; }
    public Game resumeGame(User user) { return null; }
    public Game pauseGame(User user) { return null; }
    public void saveGame(User user) {}
    public void quitGame() {}
    public Game getCurrentGame() { return null; }
    public Rooms getCurrentRoom() { return null; }
    public Rooms moveToRoom(int roomID) { return null; }
    public boolean attemptPuzzle(int puzzleID, String solution) { return false; }
    public String getHint(int puzzleID) { return null; }
    public List<Progress> getProgress() { return null; }
    public long getTimeTaken() { return 0L; }
    public Leaderboard getLeaderboard() { return null; }
    public Settings getSettings(User user) { return null; }
    public void updateSettings(User user, Settings settings) { }
    */
}
