import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Progress {
    private String UUID;
    private String username; // prev private User user;
    private int score;
    private Date dateAchieved;
    private int rank;
    
    private Game currentGame;

    //Goals
    /*
     * Percent through game
     * Questions they answered
     * What hints they used on which questions
     */
    
   

    //Deleting For the scenario, a lot could be cut off Progress, since she wants to see Percentage, Questions Answered, and Hints used
    private List<Room> roomsCleared = new ArrayList<>();
    private List<Puzzle> puzzlesSolved = new ArrayList<>();
    private String lastPuzzle;
    private Room currentRoom;
    private long timeTaken;
    Game game = new Game();
    //

    // constructor stub
    public Progress(String username, Game currentGame) {
        this.username = username;
        this.currentGame = currentGame;
        //this.timeTaken = (long) timeTaken;
        this.dateAchieved = new Date();
        this.score = 0;
        this.rank = 0;

        
    }

    public User getUser() {
        return null;
    }

    public void setUser(User user) {}

    public int getScore() {
        Puzzle p;
        for (p : puzzlesSolved) {
            score += p.getScoreValue();
            return score;

        }
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

    //  incorrect
    public List<Puzzle> getPuzzlesSolved() {
        int index = roomsCleared.size() - 1;
        Puzzle p = roomsCleared.get(index).getPuzzles().get(index);
        if (p.getStatus() == p.getStatus().SOLVED) {
            puzzlesSolved.add(p);
            return puzzlesSolved;
        }

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
