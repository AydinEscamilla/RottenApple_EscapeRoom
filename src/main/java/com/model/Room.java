/**
 * @Author: Rotten Apple
 * CSCE247
 */
package com.model;

import java.util.List;

/**
 * This class represents a room in the game 
 * Contains the room's information
 */
public class Room {
    private int roomID;
    private String roomName;
    private String description;
    private List<Puzzle> puzzles;
    private boolean isCleared; 
    private int roomOrder;
    private List<Room> prerequisiteRooms;

    /**
     * 
     * @param roomID The ID for this specific room.
     * @param roomName The room's name
     * @param description A description of the room
     * @param puzzles The list of puzzles for this room
     */
    public Room(int roomID, String roomName, String description, List<Puzzle> puzzles) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.description = description;
        this.puzzles = puzzles;
    }

    /**
     * 
     * @return the room's ID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * 
     * @return the room's name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * 
     * @param room The room the description is describing
     * @return the room's description
     */
    public String getDescription(Room room) {
        return description;
    }

    /**
     * Add a puzzle to the room
     * @param puzzle The puzzle being added 
     */
    public void addPuzzle(Puzzle puzzle) {

    }

    /**
     * Lists puzzles in the room
     * @return list of puzzles 
     */
    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    /**
     * Marks the room clear
     */
    public void clearRoom() {

    }

    /**
     * 
     * @return true if the room is cleared and false if not
     */
    public boolean isRoomCleared() {
        return isCleared;
    }

    /**
     * Lists unsolved puzzles in the room
     * @return list of unsolved puzzles
     */
    public List<Puzzle> getUnsolvedPuzzles() {
        return null;
    }

    /**
     * Checks if the player can access the room
     * @param completedRooms list of completed rooms
     * @return true if player can access the next room and false if not
     */
    public boolean hasAccess(List<Room> completedRooms){
        return false;
    }

    /**
     * Returns the next room
     * @return the next room
     */
    public Room getNextRoom() {
        return null;
    }

    /**
     * Moves to the room with the specific ID
     * @param roomID The room being moved to 
     * @return the room with this ID
     */
    public Room moveToRoom(int roomID) {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Room[id=%d, name=\"%s\"]",
                            roomID, roomName);
    }
}