package SystemFiles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter {
    private String userFile; // The user data is stored in userFile

    public DataWriter(String userFile) {
        this.userFile = userFile;
    }

   // saveUsers saves users to JSON
    public boolean saveUsers(List<User> users) {

        // Creates the array to hold user objects
        JSONArray jsonUsers = new JSONArray();

        // Convert user to JSON object (portia said something like this in her vid)
        for (User user : users) {
            JSONObject jsonUser = new JSONObject();
            jsonUser.put("username", user.getUsername());
            jsonUser.put("password", user.getPassword());

            // Converts settings to JSON
            JSONObject jsonSettings = new JSONObject();
            Settings s = user.getPreferences();
            jsonSettings.put("audioVolume", s.getAudioVolume());
            jsonSettings.put("musicOn", s.isMusicOn());
            jsonSettings.put("soundEffectsOn", s.isSoundEffectsOn());
            jsonSettings.put("textSize", s.getTextSize());

            // Save users settings to the rest of their JSON data
            jsonUser.put("preferences", jsonSettings);
            jsonUsers.add(jsonUser); // Add the user to the array
        }

        // write the array to the userFile
        try (FileWriter file = new FileWriter("SystemFiles/" + userFile)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // need to finish
    public boolean saveProgress(User user, Object roomID, Object gameID, Object puzzleID) {
        return false;
    }

    // need to finish
    public boolean updateSettings(User user, Settings settings) {
        return false;
    }

    // need to finish
    public boolean addEntry(User user, Object score) {
        return false;
    }
}
