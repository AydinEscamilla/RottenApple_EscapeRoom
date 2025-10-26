package com.model;

import java.util.*;

public class User {
    private UUID uuid;
    private String username;
    private String password;
    private int currentGame;
    private int currentRoom;
    private int lastPuzzle;
    private List<Integer> puzzlesComplete;
    private List<String> items;

    // Creates a user
    public User(String username, String password) {
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.currentGame = 0;
        this.currentRoom = 0;
        this.lastPuzzle = 0;
        this.puzzlesComplete = new ArrayList<>();
        this.items = new ArrayList<>(); 
    }

    // Fetches a user
    public User(UUID uuid, String username, String password, int currentGame, int currentRoom, int lastPuzzle, List<Integer> puzzlesComplete, List<String> items) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.currentGame = currentGame;
        this.currentRoom = currentRoom;
        this.lastPuzzle = lastPuzzle;
        this.puzzlesComplete = puzzlesComplete != null ? puzzlesComplete : new ArrayList<>();
        this.items = items != null ? items : new ArrayList<>();
    }

    public boolean userMatch(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCurrentGame() {
        return currentGame;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }
    
    public int getLastPuzzle() {
        return lastPuzzle;
    }

    public List<Integer> getPuzzlesComplete() {
        return puzzlesComplete;
    }

    public List<String> getItems() {
        return items;
    }

        public void setCurrentGame(int currentGame) {
        this.currentGame = currentGame;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setLastPuzzle(int lastPuzzle) {
        this.lastPuzzle = lastPuzzle;
    }

    public void addItem(String item) {
        items.add(item);
    }
    @Override
    public String toString() {
        return username + " (" + uuid + ")";
    }

}
