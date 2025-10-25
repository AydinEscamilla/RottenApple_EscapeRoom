package systemfiles;

import java.util.Date;
import java.util.List;

public class Progress {
    private String UUID;
    private User user;
    private int score;
    private long timeTaken;
    private Date dateAchieved;
    private int rank;
    private List<Rooms> roomsCleared;
    private List<Puzzle> puzzlesSolved;
    private Game currentGame;
    private Rooms currentRoom;
    private String lastPuzzle;

    public Progress(User user, int score, double timeTaken, Date date) {
        // constructor stub
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
        return 0;
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

    public List<Rooms> getRoomsCleared() {
        return null;
    }

    public void addRoomCleared(Rooms room) {}

    public List<Puzzle> getPuzzlesSolved() {
        return null;
    }

    public void addPuzzleSolved(Puzzle puzzle) {}

    public Game getCurrentGame() {
        return null;
    }

    public void setCurrentGame(Game game) {}

    public Rooms getCurrentRoom() {
        return null;
    }

    public void setCurrentRoom(Rooms room) {}

    public void resetProgress() {}

    @Override
    public String toString() {
        return null;
    }
}
