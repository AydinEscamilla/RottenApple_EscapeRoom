package com.systemfiles;

import java.util.*;

public class User {
    private UUID uuid;
    private String username;
    private String password;
    private int currentGame;
    private int currentRoom;
    private int lastPuzzle;
    private List<String> items;

    // Creates a user
    public User(String username, String password) {
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.currentGame = 0;
        this.currentRoom = 0;
        this.lastPuzzle = 0;
        this.items = new ArrayList<>(); 
    }

    // Fetches a user
    public User(UUID uuid, String username, String password, int currentGame, int currentRoom, int lastPuzzle, List<String> items) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.currentGame = currentGame;
        this.currentRoom = currentRoom;
        this.lastPuzzle = lastPuzzle;
        this.items = items != null ? items : new ArrayList<>();
    }

    public boolean userMatch(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public UUID getID() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getGame() {
        return currentGame;
    }

    public int getRoom() {
        return currentRoom;
    }
    
    public int getPuzzle() {
        return lastPuzzle;
    }

    public List<String> getItems() {
        return items;
    }
}
