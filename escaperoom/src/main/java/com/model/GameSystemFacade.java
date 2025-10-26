package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameSystemFacade {

    private Users users = Users.getInstance();
    private User currentUser;

    public static void main(String[] args) { 
        GameSystemFacade facade = new GameSystemFacade(); 
    }

    public User signup(String username, String password) {
        User newUser = new User(username, password);
        boolean added = Users.getInstance().addUser(newUser);

        if (added == true) {
            this.currentUser = newUser;
            return newUser;
        } else {
            return null;
        }
    }

    public Room getRoom() {
    }

    public User startNewGame(Room room) {
        if (this.currentUser != null) {
            System.out.println(this.currentUser.getUsername());
            return this.currentUser;
        } else {
            System.out.println("No user loaded.");
            return null;
        }
    }

    public Room getRoomByName(String name) {
        return DataLoader.getRoomByName(name);
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
