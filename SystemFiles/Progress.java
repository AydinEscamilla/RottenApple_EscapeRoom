import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Progress {
    private String UUID;
    private User user;
    private int score;
    private long timeTaken;
    private Date dateAchieved;
    private int rank;
    private List<Room> roomsCleared = new ArrayList<>();
    private List<Puzzle> puzzlesSolved = new ArrayList<>();
    private Game currentGame;
    private Room currentRoom;
    private String lastPuzzle;

    Game game = new Game();

    // constructor stub
    public Progress(User user, int score, double timeTaken, Date date) {
        this.user = user;
        this.score = score;
        this.timeTaken = (long) timeTaken;
        this.dateAchieved = date;

        
    }

    public User getUser() {
        return null;
    }

    public void setUser(User user) {}

    public int getScore() {
        return 0;
    }

    public void setScore(int score) {}

    public long getTimeTaken() {
        return currentGame.getElapsedTime();
    }

    public void setTimeTaken(long time) {}

    public Date getDateAchieved() {
        return null;
    }

    public void setDateAchieved(Date date) {}

    public int getRank() {
        return 0;
    }

    public void setRank(int rank) {}

    public List<Room> getRoomsCleared() {
        if (currentRoom.isRoomCleared()) {
            roomsCleared.add(currentRoom);
            return roomsCleared;
        }
        return null;
    }

    public void addRoomCleared(Room room) {
        List <Room> cleared = getRoomsCleared();

    }

    public List<Puzzle> getPuzzlesSolved() {
        Puzzle p = roomsCleared.get(i.get)

        return null;
    }

    public void addPuzzleSolved(Puzzle puzzle) {}

    public Game getCurrentGame() {
        
        return game.getCurrentGame();
        
    }

    public void setCurrentGame(Game game) {}

    public Room getCurrentRoom() {
        return game.getCurrentRoom();
    }

    public void setCurrentRoom(Room room) {}

    public void resetProgress() {}

    @Override
    public String toString() {
        return null;
    }
}
