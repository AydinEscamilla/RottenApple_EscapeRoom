package SystemFiles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private static final String USER_FILE = "SystemFiles/user.json";

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        // Read the JSON array from the user file
        JSONArray usersArray = readJSONArray(USER_FILE);

        for (Object obj : usersArray) {
            JSONObject jsonUser = (JSONObject) obj;

            // Read username and password
            String username = (String) jsonUser.get("username");
            String password = (String) jsonUser.get("password");
            String uuid = (String) jsonUser.get("uuid");

            // Load user settings
            JSONObject settingsObj = (JSONObject) jsonUser.get("preferences");
            Settings settings = new Settings();
            if (settingsObj != null) {
                settings.setAudioVolume(((Long) settingsObj.getOrDefault("audioVolume", 50L)).intValue());
                settings.setMusicOn((Boolean) settingsObj.getOrDefault("musicOn", true));
                settings.setSoundEffectsOn((Boolean) settingsObj.getOrDefault("soundEffectsOn", true));
                settings.setTextSize(((Long) settingsObj.getOrDefault("textSize", 12L)).intValue());
            }

            // Create the User object
            User user = new User(username, password, settings);

            // Assign UUID to match DataWriter
            try {
                java.lang.reflect.Field uuidField = User.class.getDeclaredField("uuid");
                uuidField.setAccessible(true);
                uuidField.set(user, uuid);
            } catch (Exception e) {
                e.printStackTrace();
            }

            users.add(user);
        }

        return users;
    }
    
    // getGame stub
    public static List<Game> getGame() {
        return new ArrayList<>();
    }

    // getRoom stub
    public static List<Room> getRoom() {
        return new ArrayList<>();
    }

    // getPuzzle stub
    public static List<Puzzle> getPuzzle() {
        return new ArrayList<>();
    }

    // getLeaderboard stub
    public static Leaderboard getLeaderboard() {
        return new Leaderboard();
    }

    // getSettings function loads users settings
    public static Settings getSettings() {
        Settings defaultSettings = new Settings();
        defaultSettings.setAudioVolume(50);
        defaultSettings.setMusicOn(true);
        defaultSettings.setSoundEffectsOn(true);
        defaultSettings.setTextSize(12);
        return defaultSettings;
    }

    // readJSONARRAY is a helper method to read a array
    private static JSONArray readJSONArray(String path) {
        JSONParser parser = new JSONParser();
        File file = new File(path);

        if (!file.exists()) {
            return new JSONArray(); // returns empty list if file not found
        }

        try (FileReader reader = new FileReader(file)) {
            Object obj = parser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
}
