import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static final String USERS_FILE = "user.json";

    public static synchronized List<User> readUsers() {
        File f = new File(USERS_FILE);
        List<User> out = new ArrayList<>();
        if (!f.exists()) return out;

        JSONParser parser = new JSONParser();
        try (FileReader fr = new FileReader(f)) {
            Object parsed = parser.parse(fr);
            if (!(parsed instanceof JSONArray)) return out;
            JSONArray arr = (JSONArray) parsed;
            for (Object item : arr) {
                if (item instanceof JSONObject) {
                    User u = User.fromJSONObject((JSONObject) item);
                    if (u != null) out.add(u);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            // return whatever we parsed so far (or empty)
        }
        return out;
    }

    @SuppressWarnings("unchecked")
    public static synchronized boolean writeUsers(List<User> users) {
        JSONArray arr = new JSONArray();
        for (User u : users) {
            arr.add(u.toJSONObject());
        }

        try (FileWriter fw = new FileWriter(USERS_FILE, false)) {
            fw.write(arr.toJSONString());
            fw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean createUser(String username, String password) {
        if (username == null || username.isBlank() || password == null) return false;
        username = username.trim();

        List<User> users = readUsers();
        for (User u : users) {
            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(username)) {
                return false; // already exists
            }
        }

        User newUser = new User(username, password);
        users.add(newUser);
        return writeUsers(users);
    }

    /**
     * Authenticate: returns User if credentials match, null otherwise.
     */
    public static synchronized User authenticate(String username, String password) {
        if (username == null || password == null) return null;
        List<User> users = readUsers();
        for (User u : users) {
            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(username)
                    && u.getPassword() != null && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public static synchronized User getUserByUsername(String username) {
        if (username == null) return null;
        List<User> users = readUsers();
        for (User u : users) {
            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(username)) return u;
        }
        return null;
    }

    public static synchronized List<User> getAllUsers() {
        return new ArrayList<>(readUsers());
    }
}
