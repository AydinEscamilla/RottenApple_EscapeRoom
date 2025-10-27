/**
 * @Author: Rotten Apple
 * CSCE247
 */
package com.model;
import java.util.*;

/**
 * This class represents a player of the escape room.
 * Stores the player's account information
 *
 */
public class User {
    private UUID uuid;
    private String username;
    private String password;
    private int currentGame;
    private int currentRoom;
    private int lastPuzzle;
    private List<Integer> puzzlesComplete;
    private List<String> items;

    private Map<Integer, Integer> hintsUsedMap = new HashMap<>();

    /**
     * 
     * @param username the players username
     * @param password the players password
     */
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

    /**
     * 
     * @param uuid the player's uuid
     * @param username the player's username
     * @param password the player's password
     * @param currentGame the current game the player is on 
     * @param currentRoom the current room the player is in
     * @param lastPuzzle the last puzzle the player saw 
     * @param puzzlesComplete a list of puzzles the player completed
     * @param items the player's items 
     */
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

    /**
     * 
     * @param username the username being matched
     * @param password the password being matched
     * @return {@code true} if username and password match and {@code false} if not.
     */
    public boolean userMatch(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * 
     * @return the player's uuid
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * 
     * @return the player's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @return the player's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @return the current game the player is on
     */
    public int getCurrentGame() {
        return currentGame;
    }

    /**
     * 
     * @return the current room the player is in
     */
    public int getCurrentRoom() {
        return currentRoom;
    }

    /**
     * 
     * @return the last puzzle the player saw
     */
    public int getLastPuzzle() {
        return lastPuzzle;
    }

    /**
     * 
     * @return list of puzzles completed
     */
    public List<Integer> getPuzzlesComplete() {
        return puzzlesComplete;
    }

    /**
     * 
     * @return list of the player's items
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * 
     * @param currentGame the current game the player is on
     */
    public void setCurrentGame(int currentGame) {
        this.currentGame = currentGame;
    }

    /**
     * 
     * @param currentRoom the current room the player is in
     */
    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * 
     * @param lastPuzzle the last puzzle the user saw
     */
    public void setLastPuzzle(int lastPuzzle) {
        this.lastPuzzle = lastPuzzle;
    }

    /**
     * Adds an item to the player's inventory
     * @param item the item being added
     */
    public void addItem(String item) {
        items.add(item);
    }

    /**
     * 
     * @return the map of hints the player used 
     */
    public Map<Integer, Integer> getHintsUsedMap() {
        return hintsUsedMap;
    }

    /**
     * Sets a hints used map
     * @param map Map of hints used 
     */
    public void setHintsUsedMap(Map<Integer, Integer> map) {
        this.hintsUsedMap = map != null ? map : new HashMap<>();
    }

    /**
     * Adds one to the number of hints used on the puzzle
     * @param puzzleID The puzzle the player is using a hint on
     */
    public void incrementHintUsed(int puzzleID) {
        if (hintsUsedMap == null) hintsUsedMap = new HashMap<>();
        hintsUsedMap.put(puzzleID, hintsUsedMap.getOrDefault(puzzleID, 0) + 1);
    }

    /**
     * 
     * @param puzzleID the puzzle the hints were used on
     * @return the number of hints used for the puzzle
     */
    public int getHintsUsedForPuzzle(int puzzleID) {
        if (hintsUsedMap == null) return 0;
        return hintsUsedMap.getOrDefault(puzzleID, 0);
    }
}