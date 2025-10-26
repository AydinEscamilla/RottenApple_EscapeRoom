
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataLoader extends DataConstants {
public static ArrayList<User> getUsers() {
    ArrayList<User> userList = new ArrayList<>();

    try (FileReader reader = new FileReader("json/" + DataConstants.UserFields.USER_FILE_NAME)) {
        JSONParser parser = new JSONParser();
        // your file is a top-level JSON array of users
        JSONArray usersJSON = (JSONArray) parser.parse(reader);

        for (Object obj : usersJSON) {
            JSONObject userJSON = (JSONObject) obj;

            UUID uuid = UUID.fromString((String) userJSON.get("UUID"));
            String username = (String) userJSON.get("username");
            String password = (String) userJSON.get("password");

            // JSON.simple returns numbers as Long; handle possible nulls
            int currentGame = 0;
            if (userJSON.get("currentGame") != null) {
                currentGame = ((Long) userJSON.get("currentGame")).intValue();
            }
            int currentRoom = 0;
            if (userJSON.get("currentRoom") != null) {
                currentRoom = ((Long) userJSON.get("currentRoom")).intValue();
            }
            int lastPuzzle = 0;
            if (userJSON.get("lastPuzzle") != null) {
                lastPuzzle = ((Long) userJSON.get("lastPuzzle")).intValue();
            }

            List<String> items = new ArrayList<>();
            JSONArray itemsArray = (JSONArray) userJSON.get("items");
            if (itemsArray != null) {
                for (Object it : itemsArray) {
                    items.add((String) it);
                }
            }

            // Requires a User constructor that accepts all these fields
            userList.add(new User(uuid, username, password, currentGame, currentRoom, lastPuzzle, items));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return userList;
}
}