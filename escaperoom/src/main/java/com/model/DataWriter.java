package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class DataWriter extends DataConstants {

    public static void saveUsers() {
        Users users = Users.getInstance();
        ArrayList<User> userList = users.getUsers();

        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(UserFields.USER_TEMP_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
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

        return userDetails;
    }
}
