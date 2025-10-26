package com.systemfiles;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    public static void saveUsers() {
        UserList userList = UserList.getInstance();
        ArrayList<User> users = userList.getAllUsers();

        JSONArray jsonUsers = new JSONArray();

        for (User user : users) {
            jsonUsers.add(getUserJSON(user));
        }

        try (FileWriter file = new FileWriter(USER_TEMP_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();

        userDetails.put("UUID", user.getId().toString());
        userDetails.put("username", user.getUsername());
        userDetails.put("password", user.getPassword());
        userDetails.put("currentGame", user.getCurrentGame());
        userDetails.put("currentRoom", user.getCurrentRoom());
        userDetails.put("lastPuzzle", user.getLastPuzzle());

        JSONArray itemsArray = new JSONArray();
        for (String item : user.getItems()) {
            itemsArray.add(item);
        }
        userDetails.put("items", itemsArray);

        return userDetails;
    }

    public static void main(String[] args) {
        DataWriter.saveUsers();
    }
}