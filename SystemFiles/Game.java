
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
    private long accumlatedTime = 0; //  total time spent paused
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
      
        if (status == GameStatus.PAUSED && pausedAt != null) {
            //  Calculate paused duration and add to accumlatedTime
            accumlatedTime += Duration.between(pausedAt, Instant.now()).toMillis(); 
            pausedAt = null;
            status = GameStatus.RUNNING;
        }
            
        
 
    }

    public void end() {
        status = GameStatus.COMPLETED;

    }

    
    public GameStatus getStatus() {
        return status;
    }

    public long getElapsedTime() {
        //  If game not started, elapsed time is 0
        if (status == GameStatus.NOT_STARTED) {
            return 0L;
        }

        Instant now = Instant.now();

        //  total time since starting
        long sinceStart = Duration.between(startTime, now).toMillis(); 

        //  the open pause, time between paused at to now
        long currentPause = (status ==  GameStatus.PAUSED && pausedAt != null)
                                ? Duration.between(pausedAt, now).toMillis()
                                : 0L;
        //  Elapsed time is total time minus the pauses in accumlated and current
        long elapsed = sinceStart - accumlatedTime - currentPause;
        //  Ensure non-negative
        return Math.max(0L, elapsed);

    }

    /*
     * @return the current room the player is in
     */
    public Room getCurrentRoom() {
        if (rooms.isEmpty()) {
            return null;
        }
        return rooms.get(currentRoomIndex);
    }

    /*
     * @return true if successfully moved to room with given ID, false otherwise
     */
    public boolean goToRoom(int roomID) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomID() == roomID) {
                currentRoomIndex = i;
                return true;
            }
        }
        return false;

    }

    /*
     * User attempts to answer the puzzle in the current room at given index
     * @return true if the attempt was correct, false otherwise
     */
    public boolean attemptCurrentRoomPuzzle (int index, String solution) {
        Room currentRoom = getCurrentRoom(); 
        if (currentRoom == null) return false; 
        List<Puzzle> Puzzles = currentRoom.getPuzzles(); 
        if (index < 0 || index >= Puzzles.size()) return false; 

        boolean result = Puzzles.get(index).attempt(solution); 
        currentRoom.updatedClearedStatus();
        if (currentRoom.isRoomCleared()) {
            advanceIfCleared();
        }
        return result;

    }

    /*
     * Advances to the next room if the current room is cleared, end game if none is left
     */
    private void advanceIfCleared() {
        Room currentRoom = getCurrentRoom();
        if (currentRoom == null) return;
        currentRoom.updatedClearedStatus();
        if (!currentRoom.isRoomCleared()) return;

        if (currentRoomIndex + 1 < rooms.size()) {
            currentRoomIndex++;
        } else {
            end();
        }
    }

    /*
     * @return list of unsolved puzzles in the current room
     */
    public List<Puzzle> getUnsolvedPuzzles() {
        Room r = getCurrentRoom();
        return (r == null) ? List.of() : r.getUnsolvedPuzzles(); 
    }

    public boolean isFinished() {
        return status == GameStatus.COMPLETED; 
    }

    public int calculateScore() {
        int totalScore = 0;
        for (Room room : rooms) {
            for (Puzzle puzzle : room.getPuzzles()) {
                if (puzzle.solved()) {
                    totalScore += puzzle.getScoreValue();
                }
            }
        }
        return totalScore;
    }


    public void addPuzzle(Puzzle puzzle) {

    }

    public void addRoom(Room room) {
        if (room != null) {
            rooms.add(room);
        }

    }

    public int getGameID() {
        return gameID;
    }
    /*
    * @return list of rooms in the game
    */
    public List<Room> getRooms() {
        return List.copyOf(rooms);
    }

    public Progress saveProgress() {
        return null;
    }

    public void loadProgress(Progress progress) {

    }

   
 }

 
