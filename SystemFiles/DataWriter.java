import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class DataWriter {
    private String usersFile;

    public DataWriter() {
        this("user.json");
    }

    public DataWriter(String usersFile) {
        this.usersFile = usersFile;
    }

    public boolean saveUsers(List<User> users) {
        try (FileWriter writer = new FileWriter(this.usersFile, false)) {
            JSONArray arr = new JSONArray();
            for (User u : users) {
                JSONObject o = new JSONObject();
                o.put("username", u.getUsername());
                o.put("password", u.getPassword());
                JSONObject pref = new JSONObject();
                pref.put("audioVolume", u.getPreferences() == null ? 50 : u.getPreferences().getAudioVolume());
                o.put("preferences", pref);
                arr.add(o);
            }
            writer.write(arr.toJSONString());
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveUser(User user) {
        if (user == null) return false;
        DataLoader loader = new DataLoader(this.usersFile);
        List<User> users = loader.loadUsers();
        if (users == null) users = new ArrayList<>();
        users.add(user);
        return saveUsers(users);
    }

    public boolean saveProgress(User user, Room roomID, Game gameID, Puzzle puzzleID) {
        return false;
    }

    public boolean updateSettings(User user, Settings settings) {
        return false;
    }

    public boolean addEntry(User user, Score score) {
        return false;
    }
}

