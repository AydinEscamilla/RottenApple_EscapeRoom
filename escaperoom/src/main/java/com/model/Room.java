package com.model;

import java.util.List;

public class Room {
    private int roomID;
    private String roomName;
    private String description;
    private List<Puzzle> puzzles;
    private boolean isCleared; 
    private int roomOrder;
    private List<Room> prerequisiteRooms;

    public Room(int roomID, String name, String description) {
        this.roomID = roomID;
        this.name = name;
        this.description = description;
    }

    public void addPuzzle(Puzzle puzzle) {

    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void clearRoom() {

    }

    public boolean isRoomCleared() {
        return isCleared;
    }

    public List<Puzzle> getUnsolvedPuzzles() {
        return null;
    }

    public boolean hasAccess(List<Room> completedRooms){
        return false;
    }

    public Room getNextRoom() {
        return null;
    }

    public Room moveToRoom(int roomID) {
        return null;
    }
}
