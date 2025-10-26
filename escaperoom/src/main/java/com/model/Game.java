package com.model;

import java.net.http.WebSocket.Listener;
import java.util.List; 

public class Game {
    private int gameID;
    private List<Room> rooms;
    private Room currentRoom; 
    private boolean isCompleted;
    private int totalScore;
    private long timeLimit;
    private boolean isPaused;
    private Game game;

    public Game (int gameID) {
        return Game;
    }

    public Game() {
        gameID = 0;
        //rooms =
        currentRoom = 101; //  per JSON this is the first room
        isCompleted = false;
        totalScore = 0;
        //timeLimit = 
        isPaused = false;
        game = 0; //  don't know what to store the Game itself as


    }

    public void start() {

    }

    public void end() {

    }

    public double getElapsedTime() {
        return 0.0;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void goToRoom(Room room) {

    }

    public List<Puzzle> getUnsolvedPuzzles() {
        return null; 
    }

    public boolean isFinished() {
        return false; 
    }

    public int calculateScore() {
        return 0;
    }

    public void addPuzzle(Puzzle puzzle) {

    }

    public void addRoom(Room room) {

    }

    public Progress saveProgress() {
        return null;
    }

    public void loadProgress(Progress progress) {

    }

    public void pause() {
      
        while (isPaused = false) {

        }


    }

    public void resume() {
        
        while (isPaused != false) {

        }

        
    }
 }
