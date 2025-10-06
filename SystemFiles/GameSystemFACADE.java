import java.util.List;

public class GameSystemFacade {
    private User user;


public User login(String username, String password) {
        return null;
    }

public User signUp(String username, String password) {
        return null;
    }

public Settings changeSetting(User user) {
        return null;
    }

public Game startNewGame(User user) {
        return null;
    }

public Game resumeGame(User user) {
        return null;
    }

public Game pauseGame(User user) {
        return null;
    }

public void saveGame(User user) {

    }

public void quitGame() {

    }

public Game getCurrentGame() {
        return null;
    }

public Rooms getCurrentRoom() {
        return null;
    }

public Rooms moveToRoom(int roomID) {
        return null;
    }

public boolean attemptPuzzle(int puzzleID, String solution) {
        return false;
    }

public String getHint(int puzzleID) {
        return null; 
    }

public List<Progress> getProgress() {
        return null; 
    }

public long getTimeTaken() {
        return 0L;
    }

public Leaderboard getLeaderboard() {
        return null;
    }

public Settings getSettings(User user) {
        return null;
    }

public void updateSettings(User user, Settings settings) {

    }

}