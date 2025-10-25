import java.net.http.WebSocket.Listener;
import java.time.Instant; //  better for tracking time
import java.util.List;
import java.time.Duration; 

public class Game {
    public enum GameStatus { NOT_STARTED, RUNNING, PAUSED, COMPLETED}

    private GameStatus status = GameStatus.NOT_STARTED;
    private int gameID;
    private List<Room> rooms;
    private int currentRoomIndex = 0;

    //  Timing
    private Instant startTime;
    private Instant pausedAt;
    private long accumlatedTime = 0;
    private int totalScore = 0;

    public Game (int gameID, List<Room> initialRooms) {
        this.gameID = gameID;
        if(initialRooms != null) {
            rooms.addAll(initialRooms);
        }
    }


    public void start() {
        if (rooms.isEmpty()) {
            throw new IllegalStateException("Cannot start a game with no rooms.");
        }
        status = GameStatus.RUNNING;
       currentRoomIndex = 0;
        startTime = Instant.now();
        pausedAt = null;
        accumlatedTime = 0;

    }

     public void pause() {
      
        if (status == GameStatus.RUNNING) {
            status = GameStatus.PAUSED;
            pausedAt = Instant.now();
        }

    }

    /*
     * Resumes the game if it is paused
     */
    public void resume() {
        if (status == GameStatus.PAUSED) {
            if (pausedAt != null) {
                accumlatedTime += Duration.between(pausedAt, Instant.now()).toMillis();
                pausedAt = null;
            }
            status = GameStatus.RUNNING;
        }
 
    }

    public void end() {
        status = GameStatus.COMPLETED;

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

   
 }
