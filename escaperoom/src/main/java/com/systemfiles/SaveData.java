package systemfiles;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SaveData {
    private String saveFilePath;
    private Date lastSaved;
    private Map<User, List<Progress>> userProgress;
    private Map<Game, Progress> gameProgress;
    private static SaveData saveData;

    public static SaveData getInstance() {
        return null;
    }

    public SaveData(String path) {
        // constructor stub
    }

    public void saveUser(User user) {}

    public User loadUser(String username) {
        return null;
    }

    public void saveSettings(Settings settings) {}

    public Settings loadSettings(User user) {
        return null;
    }

    public void saveLeaderboard(Leaderboard leaderboard) {}

    public Leaderboard loadLeaderboard() {
        return null;
    }

    public void saveGameProgress(User user, Game game, Progress progress) {}

    public List<Progress> loadGameProgress(User user) {
        return null;
    }

    public void deleteSave(User user) {}

    public boolean hasSave(User user) {
        return false;
    }

    public void writeToFile() {}

    public void readFromFile() {}
}
