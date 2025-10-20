package SystemFiles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
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
            jsonUsers.add(jsonUser);
        }

        // write the array to the user file
        return writeJSONArray(jsonUsers, "SystemFiles/" + userFile);
    }

    // saveProgress saves the users puzzle progress
    public boolean saveProgress(User user, Object roomID, Object gameID, Object puzzleID) {

        JSONArray usersArray = readJSONArray("SystemFiles/" + userFile); // Reads users from userFile

        // Find the specific user
        for (Object obj : usersArray) {
            JSONObject jsonUser = (JSONObject) obj;

            if (jsonUser.get("username").equals(user.getUsername())) {
                // Create the user's progress object
                JSONObject progressData = new JSONObject();
                progressData.put("roomID", roomID.toString());
                progressData.put("gameID", gameID.toString());
                progressData.put("puzzleID", puzzleID.toString());

                // add the users progress 
                jsonUser.put("progress", progressData);
            }
        }

        // Write updated progress to the JSON file
        return writeJSONArray(usersArray, "SystemFiles/" + userFile);
    }

    // updateSettings updates the users settings
    public boolean updateSettings(User user, Settings settings) {
        
        JSONArray usersArray = readJSONArray("SystemFiles/" + userFile); // Read from the userFile

        // Find the specific user
        for (Object obj : usersArray) {
            JSONObject jsonUser = (JSONObject) obj;
            
            
            if (jsonUser.get("username").equals(user.getUsername())) {
                // Create a settings object
                JSONObject newSettings = new JSONObject();
                newSettings.put("audioVolume", settings.getAudioVolume());
                newSettings.put("musicOn", settings.isMusicOn());
                newSettings.put("soundEffectsOn", settings.isSoundEffectsOn());
                newSettings.put("textSize", settings.getTextSize());

                // Replace the old settings with the new ones
                jsonUser.put("preferences", newSettings);
            }
        }

        // Write everything back to userFile
        return writeJSONArray(usersArray, "SystemFiles/" + userFile);
    }

    // addEntry adds an entry to the leaderboard
    public boolean addEntry(User user, Object score) {
        // Read all users from the userFile
        JSONArray usersArray = readJSONArray("SystemFiles/" + userFile);

        // Find the user 
        for (Object obj : usersArray) {
            JSONObject jsonUser = (JSONObject) obj;

            if (jsonUser.get("username").equals(user.getUsername())) {
                // Add score for the user
                jsonUser.put("score", score.toString());
            }
        }

        // Update the userFile
        return writeJSONArray(usersArray, "SystemFiles/" + userFile);
    }

    // read JSON Array is a helper method that reads a JSON file and returns an array
    private JSONArray readJSONArray(String path) {
        JSONParser parser = new JSONParser();
        File file = new File(path);

        if (!file.exists()) {
            return new JSONArray(); // Return empty list if file not found
        }

        try (FileReader reader = new FileReader(file)) {
            Object obj = parser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    // writeJSONArray is a helper method to write a JSON array to a JSON file
    private boolean writeJSONArray(JSONArray arr, String path) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(arr.toJSONString());
            file.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
