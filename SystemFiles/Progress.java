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
        this.username = username; //  prev private User User
        this.currentGame = currentGame;
        //this.timeTaken = (long) timeTaken;
        this.dateAchieved = new Date();
        this.score = 0;
        this.rank = 0;

        
    }

    /*
     * Calculates percentage completion based on solved puzzles across rooms
     * @returns the percentage of completion for user
     */
    public double getPercentageCompletion() {
        int total = 0;
        int solved = 0;

        for (Room room : currentGame.getRooms()) {
            total += room.getPuzzles().size();
            for (Puzzle puzzle : room.getPuzzles()) {
                if (puzzle.solved()) {
                    solved++;
                }
            }
        }
        return total == 0 ? 0.0 : (solved * 100.0) / total;
    }

    /*
     * Calculates the total score across all solved puzzles
     * @returns the score from all solved puzzles
     */
    public int getScore() {
        int total = 0;
        for (Room room : currentGame.getRooms()) {
            for (Puzzle puzzle : room.getPuzzles()) {
                if (puzzle.solved()) {
                    total += puzzle.getScoreValue();
                }
            }
        }
        this.score = total;
        return score;

    }

    /*
     * Gathers all Puzzles solved into a list
     * @returns a List of solvedPuzzles
     */
    public List<String> getPuzzlesSolved() {
        List<String> solvedPuzzles = new ArrayList<>();

        for (Room room : currentGame.getRooms()) {
            for (Puzzle puzzle : room.getPuzzles()) {
                if (puzzle.solved()) {
                    solvedPuzzles.add (
                        String.format("Puzzle %d: %s | Hints used: %d",
                        puzzle.getPuzzleID(),
                        puzzle.getQuestion(),
                        puzzle.getHintsUsed()) //  incorrect

                    );
                }
            }
        }
        return solvedPuzzles;
    }

    // public List<String> getHintsUsed() {
    //     List<String> hints = new ArrayList<>();

    //     return hints;
    // }
   
    //  Username
    public String getUsername() {
        return username;
    }

    // Date
     public Date getDateAchieved() {
        return dateAchieved;
    }

    //  Rank
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<Room> getRoomsCleared() {
        if (currentRoom.isRoomCleared()) {
            roomsCleared.add(currentRoom);
            return roomsCleared;
        }
        return null;
    }

    public User getUser() {
        return null;
    }

    public void setUser(User user) {}

    

    public void setScore(int score) {}

    public long getTimeTaken() {
        return currentGame.getElapsedTime();
    }

    public void setTimeTaken(long time) {}

   

    public void setDateAchieved(Date date) {

    }

    

    public void addRoomCleared(Room room) {
        List <Room> cleared = getRoomsCleared();

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
        /*
         * %s for strings
         * %d integer
         * %.2f for floating point with 2 decimal spaces
         */
        return String.format (
            "User: %s\nScore: %d\nProgress: %.2f%\nSolved: %d puzzles\nDate: %s",
            username,
            getScore(),
            getPercentageCompletion(),
            getPuzzlesSolved().size(),
            dateAchieved.toString()

        );
    }
}

