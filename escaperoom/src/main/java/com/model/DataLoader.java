package com.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataLoader extends DataConstants {

    public static ArrayList<User> getUsers() {

        ArrayList<User> userList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(UserFields.USER_FILE_NAME);
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);


            for (int i=0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID uuid = UUID.fromString((String)personJSON.get(UserFields.UUID));
				String username = (String)personJSON.get(UserFields.USERNAME);
				String password = (String)personJSON.get(UserFields.PASSWORD);
				int currentGame = ((Long)personJSON.get(UserFields.CURRENT_GAME)).intValue();
                int currentRoom = ((Long)personJSON.get(UserFields.CURRENT_ROOM)).intValue();
                int lastPuzzle = ((Long)personJSON.get(UserFields.LAST_PUZZLE)).intValue();

                JSONArray itemsJSON = (JSONArray) personJSON.get(UserFields.ITEMS);
                ArrayList<String> items = new ArrayList<>();

                if (itemsJSON != null) {
                    for (Object itemObj : itemsJSON) {
                        items.add((String) itemObj);
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

    public static ArrayList<Room> getRooms() {
        ArrayList<Room> roomList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(RoomFields.ROOM_FILE);
            JSONArray roomJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < roomJSON.size(); i++) {
                JSONObject roomsJSON = (JSONObject) roomJSON.get(i);

                int roomID = ((Long) roomsJSON.get(RoomFields.ROOM_ID)).intValue();
                String roomName = (String) roomsJSON.get(RoomFields.ROOM_NAME);
                String description = (String) roomsJSON.get(RoomFields.DESCRIPTION);

                // Parse puzzles array
                ArrayList<Puzzle> puzzles = new ArrayList<>();
                JSONArray puzzlesJSON = (JSONArray) roomsJSON.get(RoomFields.PUZZLES);
                if (puzzlesJSON != null) {
                    for (Object pObj : puzzlesJSON) {
                        JSONObject pJSON = (JSONObject) pObj;

                        int puzzleID = ((Long) pJSON.get("puzzleID")).intValue();
                        String puzzleType = (String) pJSON.get("puzzleType");
                        String question = (String) pJSON.get("question");
                        String solution = (String) pJSON.get("solution");

                        // Parse hints array
                        ArrayList<String> hints = new ArrayList<>();
                        JSONArray hintsJSON = (JSONArray) pJSON.get("hints");
                        if (hintsJSON != null) {
                            for (Object h : hintsJSON) {
                                hints.add((String) h);
                            }
                        }

                        String imagePath = (String) pJSON.get("imagePath");
                        boolean isSolved = pJSON.get("isSolved") != null && (Boolean) pJSON.get("isSolved");
                        int attempts = pJSON.get("attempts") != null ? ((Long) pJSON.get("attempts")).intValue() : 0;
                        int maxAttempts = pJSON.get("maxAttempts") != null ? ((Long) pJSON.get("maxAttempts")).intValue() : 0;
                        int scoreValue = pJSON.get("scoreValue") != null ? ((Long) pJSON.get("scoreValue")).intValue() : 0;
                        int hintUsedCount = pJSON.get("hintUsedCount") != null ? ((Long) pJSON.get("hintUsedCount")).intValue() : 0;

                        // Convert difficulty string to enum
                        Difficulty difficulty = Difficulty.valueOf(((String) pJSON.get("difficulty")).toUpperCase());

                        puzzles.add(new BasicPuzzle(
                            puzzleID,
                            puzzleType,
                            question,
                            solution,
                            hints,
                            imagePath,
                            isSolved,
                            attempts,
                            maxAttempts,
                            scoreValue,
                            hintUsedCount,
                            difficulty
                        ));
                    }
                }

                roomList.add(new Room(roomID, roomName, description, puzzles));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomList;

    }

}
