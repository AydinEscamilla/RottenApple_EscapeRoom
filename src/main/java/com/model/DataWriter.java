/**
 * @Author: Rotten Apple
 * CSCE247
 */

package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Handles saving game data (which is mostly user information since user.json constructs a save)
 * as defined by DataConstants
 * 
 * Serializes User objects to JSON
 * Writes user data to JSON
 * 
 * {@link DataLoader} handles reading from those files
 */
@SuppressWarnings("unchecked")
public class DataWriter extends DataConstants {

    /**
     * Writes the current list of users to the user.json
     * 
     * Retrieves all users from the Users singleton, converts them to JSON objects,
     * and writes the resulting JSONArray to JSON.
     * 
     * Existing data gets overwritten.
     */
    public static void saveUsers() {
        Users users = Users.getInstance();
        ArrayList<User> userList = users.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for (User user : userList) {
            jsonUsers.add(getUserJSON(user));
        }

        // Write directly to user.json, not the temp file
        try (FileWriter file = new FileWriter(UserFields.USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a User into a JSON form for file persistence. That object includes:
     *  UUID
     *  username/password
     *  currentGame, currentRoom, lastPuzzle
     *  complete puzzles and items held as arrays
     *  hints used as object mapping
     * 
     * @param user the User to serialize
     * @return a JSONObject to show the saved state
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put("UUID", user.getUUID().toString());
        userDetails.put("username", user.getUsername());
        userDetails.put("password", user.getPassword());
        userDetails.put("currentGame", user.getCurrentGame());
        userDetails.put("currentRoom", user.getCurrentRoom());
        userDetails.put("lastPuzzle", user.getLastPuzzle());
        
        // Serialize complete puzzles
        JSONArray puzzlesArray = new JSONArray();
        for (Integer puzzle : user.getPuzzlesComplete()) {
            puzzlesArray.add(puzzle);
        }
        userDetails.put("puzzlesComplete", puzzlesArray);

        // Serialize collected items
        JSONArray itemsArray = new JSONArray();
        for (String item : user.getItems()) {
            itemsArray.add(item);
        }
        userDetails.put("items", itemsArray);

        // Serialize hints used
        JSONObject hintsObj = new JSONObject();
        Map<Integer, Integer> hintsMap = user.getHintsUsedMap();
        if (hintsMap != null) {
            for (Map.Entry<Integer,Integer> e : hintsMap.entrySet()) {
                hintsObj.put(String.valueOf(e.getKey()), e.getValue());
            }
        }
        userDetails.put("hintsUsed", hintsObj);

        return userDetails;
    }
}