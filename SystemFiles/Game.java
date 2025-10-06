import java.util.List; 

public class Game {
    private int gameID;
    private List<Rooms> rooms;
    private Rooms currentRoom; 
    private boolean isCompleted;
    private int totalScore;
    private long timeLimit;
    private boolean isPaused;
    private Game game;

    public Game() {

    }

    public void start() {

    }

    public void end() {

    }

    public double getElapsedTime() {
        return 0.0;
    }

    public Rooms getCurrentRoom() {
        return currentRoom;
    }

    public void goToRoom(Rooms room) {

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

    public void addRoom(Rooms room) {

    }

    public Progress saveProgress() {
        return null;
    }

    public void loadProgress(Progress progress) {

    }

    public void pause() {

    }

    public void resume() {

    }
 }
