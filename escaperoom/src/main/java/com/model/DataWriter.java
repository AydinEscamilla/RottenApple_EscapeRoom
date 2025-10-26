package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class DataWriter extends DataConstants {

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
            System.out.println("DEBUG: Users written to " + UserFields.USER_FILE_NAME +
                            " (total: " + userList.size() + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put("UUID", user.getUUID().toString());
        userDetails.put("username", user.getUsername());
        userDetails.put("password", user.getPassword());
        userDetails.put("currentGame", user.getCurrentGame());
        userDetails.put("currentRoom", user.getCurrentRoom());
        userDetails.put("lastPuzzle", user.getLastPuzzle());
        
        JSONArray puzzlesArray = new JSONArray();
        for (Integer puzzle : user.getPuzzlesComplete()) {
            puzzlesArray.add(puzzle);
        }
        userDetails.put("puzzlesComplete", puzzlesArray);

        JSONArray itemsArray = new JSONArray();
        for (String item : user.getItems()) {
            itemsArray.add(item);
        }
        userDetails.put("items", itemsArray);

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
