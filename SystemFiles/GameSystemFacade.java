import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameSystemFacade {

    public signup(String username, String password) {

    }
    
    public User loadUser(String username) {return null;}

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public void logout() {
        currentUser = null;
    }

    public List<String> getAllUsernames() {
        return out;
    }

    /* ----- small helpers that call DataWriter where appropriate ----- */

    public boolean updateSettings(User user, Settings settings) {
        return ok;
    }

    public boolean saveProgress(User user, Object roomID, Object gameID, Object puzzleID) {
        return ok;
    }

    /*
    public Settings changeSetting(User user) { return null; }
    public Game startNewGame(User user) { return null; }
    public Game resumeGame(User user) { return null; }
    public Game pauseGame(User user) { return null; }
    public void saveGame(User user) {}
    public void quitGame() {}
    public Game getCurrentGame() { return null; }
    public Rooms getCurrentRoom() { return null; }
    public Rooms moveToRoom(int roomID) { return null; }
    public boolean attemptPuzzle(int puzzleID, String solution) { return false; }
    public String getHint(int puzzleID) { return null; }
    public List<Progress> getProgress() { return null; }
    public long getTimeTaken() { return 0L; }
    public Leaderboard getLeaderboard() { return null; }
    public Settings getSettings(User user) { return null; }
    public void updateSettings(User user, Settings settings) { }
    */
}
