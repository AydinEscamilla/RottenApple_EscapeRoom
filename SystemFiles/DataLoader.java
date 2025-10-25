import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class DataLoader {
    private String filename;

    public static void main(Stringp[] args) {
        ArrayList<User> users = DataLoader.getUsers();

        for (User user : users) {
            System.out.println(user);
        }
    }

    public DataLoader() {
        this("user.json");
    }

    public DataLoader(String filename) {
        this.filename = filename;
    }

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File f = new File(this.filename);
        if (!f.exists()) return users;
        try (FileReader fr = new FileReader(f)) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fr);
            if (obj instanceof JSONArray) {
                JSONArray arr = (JSONArray) obj;
                for (Object o : arr) {
                    if (o instanceof JSONObject) {
                        JSONObject jo = (JSONObject) o;
                        String username = (String) jo.get("username");
                        String password = (String) jo.get("password");
                        User u = new User(username, password);
                        users.add(u);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Game> getGame() {
        return new ArrayList<>();
    }

    public List<Rooms> getRoom() {
        return new ArrayList<>();
    }

    public List<Puzzle> getPuzzle() {
        return new ArrayList<>();
    }

    public Leaderboard getLeaderboard() {
        return new Leaderboard();
    }

    public Settings getSettings() {
        return new Settings();
    }
}
