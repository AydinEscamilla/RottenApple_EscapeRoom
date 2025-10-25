import java.util.*;
import java.util.stream.*;

public class GameSystemFacade {
    private DataLoader dataLoader;
    private DataWriter dataWriter;
    private List<User> users;
    private User currentUser;

    public GameSystemFacade() {
        this(new DataLoader(), new DataWriter());
    }

    public GameSystemFacade(DataLoader loader, DataWriter writer) {
        this.dataLoader = loader == null ? new DataLoader() : loader;
        this.dataWriter = writer == null ? new DataWriter() : writer;
        List<User> loaded = this.dataLoader.loadUsers();
        this.users = loaded == null ? new ArrayList<>() : loaded;
        this.currentUser = null;
    }

    public User login(String username, String password) {
        if (username == null || password == null) return null;
        for (User u : users) {
            if (u.getUsername() != null
                    && u.getUsername().equalsIgnoreCase(username)
                    && u.getPassword() != null
                    && u.getPassword().equals(password)) {
                currentUser = u;
                return currentUser;
            }
        }
        return null;
    }

    public User signUp(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        for (User u : users) {
            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(username)) {
                return null;
            }
        }

        Settings defaultSettings = new Settings(50, true, true, 14);

        User newUser;
        try {
            newUser = new User(username, password, defaultSettings);
        } catch (Throwable t) {
            newUser = new User(username, password);
            
            try { newUser.setPreferences(defaultSettings); } 
            catch (Throwable ignored) {}
        }

        users.add(newUser);
        boolean saved = false;
        try {
            saved = dataWriter.saveUser(newUser);
        } catch (Throwable t) {
            saved = false;
        }

        currentUser = newUser;
        return newUser;
    }

    public User loadUser(String username) {
        if (username == null) return null;
        return users.stream()
                    .filter(u -> username.equals(u.getUsername()))
                    .findFirst()
                    .orElse(null);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        for (User u : users) {
            usernames.add(u.getUsername());
        }
        return usernames;
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

    public Room getCurrentRoom() {
            return null;
        }

    public Room moveToRoom(int roomID) {
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