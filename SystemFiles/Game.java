import java.net.http.WebSocket.Listener;
import java.util.List; 

public class Game {
    public enum GameStatus { NOT_STARTED, RUNNING, PAUSED, COMPLETED}

    private GameStatus status = GameStatus.NOT_STARTED;
    private int gameID;
    private List<Room> rooms;
    private Room currentRoom; 
    private boolean isCompleted;
    private int totalScore;
    private long timeLimit;
    private boolean isPaused;
    private Game game;

    public Game (int gameID, List<Room> rooms, Room currentRoom, boolean isCompleted, int totalScore, long timeLimit, boolean isPaused, Game game) {
        this.gameID = gameID;
        this.rooms = rooms;
        this.currentRoom = currentRoom;
        this.isCompleted = isCompleted;
        this.totalScore = totalScore;
        this.timeLimit = timeLimit;
        this.isPaused = isPaused;
        this.game = game;
    }


    public void start() {
        while 

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
