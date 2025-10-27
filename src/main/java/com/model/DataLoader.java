/**
 * @Author: Rotten Apple
 * CSCE247
 */

package com.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * {@code DataLoader} reads persistent game data (users and rooms) from JSON files
 * as defined by {@link DataConstants} 
 * 
 * Loads users from {@link UserFields#USER_FILE_NAME} and all of their info found within
 * Loads rooms from {@link RoomFields#ROOM_FILE}, constructing Room and BasicPuzzle with info within
 * 
 * Purely data access utility, not modifying or persisting any data returned
 */

public class DataLoader extends DataConstants {

    /**
     * Loads and parses the user JSON, returning a list of User objects
     * 
     * Method reads each JSON and populates the user fields listed below
     * 
     * The loader is designed to catch incorrectly instantiated fields where appropriate, but for now
     * this is merely a formality
     * 
     * @return an ArrayList of loaded User objects (which can be empty if none found)
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();

        try (FileReader reader = new FileReader(UserFields.USER_FILE_NAME)) {
            JSONArray peopleJSON = (JSONArray) new JSONParser().parse(reader);

            for (Object o : peopleJSON) {
                if (!(o instanceof JSONObject)) continue;
                JSONObject personJSON = (JSONObject) o;

                UUID uuid = null;
                try {
                    String uuidStr = (String) personJSON.get(UserFields.UUID);
                    uuid = uuidStr != null ? UUID.fromString(uuidStr) : UUID.randomUUID();
                } catch (Exception ex) {
                    uuid = UUID.randomUUID();
                }

                String username = (String) personJSON.getOrDefault(UserFields.USERNAME, "");
                String password = (String) personJSON.getOrDefault(UserFields.PASSWORD, "");
                int currentGame = asInt(personJSON.get(UserFields.CURRENT_GAME), 0);
                int currentRoom = asInt(personJSON.get(UserFields.CURRENT_ROOM), 0);
                int lastPuzzle = asInt(personJSON.get(UserFields.LAST_PUZZLE), 0);

                ArrayList<Integer> puzzlesComplete = new ArrayList<>();
                JSONArray puzzlesJSON = (JSONArray) personJSON.get(UserFields.COMPLETED);
                if (puzzlesJSON != null) {
                    for (Object puzzleObj : puzzlesJSON) {
                        if (puzzleObj instanceof Number) {
                            puzzlesComplete.add(((Number) puzzleObj).intValue());
                        } else {
                            try {
                                puzzlesComplete.add(Integer.parseInt(puzzleObj.toString()));
                            } catch (Exception ignored) {}
                        }
                    }
                }

                ArrayList<String> items = new ArrayList<>();
                JSONArray itemsJSON = (JSONArray) personJSON.get(UserFields.ITEMS);
                if (itemsJSON != null) {
                    for (Object itemObj : itemsJSON) {
                        if (itemObj != null) items.add(itemObj.toString());
                    }
                }

                User u = new User(uuid, username, password, currentGame, currentRoom, lastPuzzle, puzzlesComplete, items);

                JSONObject hintsJson = (JSONObject) personJSON.get("hintsUsed");
                if (hintsJson != null) {
                    Map<Integer, Integer> hintsMap = new HashMap<>();
                    for (Object keyObj : hintsJson.keySet()) {
                        try {
                            String keyStr = (String) keyObj;
                            Number val = (Number) hintsJson.get(keyStr);
                            int pid = Integer.parseInt(keyStr);
                            int cnt = val == null ? 0 : val.intValue();
                            hintsMap.put(pid, cnt);
                        } catch (Exception ex) {
                            // skip invalid entries
                        }
                    }
                    u.setHintsUsedMap(hintsMap);
                }

                userList.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    /**
     * Loads and parses the room JSON file, returning {@link Room} objects in  a list
     * 
     * The loader will read for all expected fields as depicted in rooms.json
     * 
     * Difficulty specifically is passed to the enum for Difficulty (tentative, fallback to EASY)
     * 
     * @return an ArrayList of loaded Room objects (can similarly be empty)
     */
    public static ArrayList<Room> getRooms() {
        ArrayList<Room> roomList = new ArrayList<>();

        try (FileReader reader = new FileReader(RoomFields.ROOM_FILE)) {
            JSONArray roomJSON = (JSONArray) new JSONParser().parse(reader);

            for (Object o : roomJSON) {
                if (!(o instanceof JSONObject)) continue;
                JSONObject roomsJSON = (JSONObject) o;

                int roomID = asInt(roomsJSON.get(RoomFields.ROOM_ID), 0);
                String roomName = (String) roomsJSON.getOrDefault(RoomFields.ROOM_NAME, "");
                String description = (String) roomsJSON.getOrDefault(RoomFields.DESCRIPTION, "");

                // Parse puzzles array
                ArrayList<Puzzle> puzzles = new ArrayList<>();
                JSONArray puzzlesJSON = (JSONArray) roomsJSON.get(RoomFields.PUZZLES);
                if (puzzlesJSON != null) {
                    for (Object pObj : puzzlesJSON) {
                        if (!(pObj instanceof JSONObject)) continue;
                        JSONObject pJSON = (JSONObject) pObj;

                        int puzzleID = asInt(pJSON.get("puzzleID"), 0);
                        String puzzleType = (String) pJSON.getOrDefault("puzzleType", "LOGIC");
                        String question = (String) pJSON.getOrDefault("question", "");
                        String solution = (String) pJSON.getOrDefault("solution", "");

                        // Parse hints array
                        ArrayList<String> hints = new ArrayList<>();
                        JSONArray hintsJSON = (JSONArray) pJSON.get("hints");
                        if (hintsJSON != null) {
                            for (Object h : hintsJSON) {
                                if (h != null) hints.add(h.toString());
                            }
                        }

                        // Parse imagePath array (not necessary yet)
                        ArrayList<String> imagePaths = new ArrayList<>();
                        JSONArray imagePathJSON = (JSONArray) pJSON.get("imagePath");
                        if (imagePathJSON != null) {
                            for (Object ip : imagePathJSON) {
                                if (ip != null) imagePaths.add(ip.toString());
                            }
                        }

                        boolean isSolved = pJSON.get("isSolved") != null && Boolean.TRUE.equals(pJSON.get("isSolved"));
                        int attempts = asInt(pJSON.get("attempts"), 0);
                        int maxAttempts = asInt(pJSON.get("maxAttempts"), 3);
                        int scoreValue = asInt(pJSON.get("scoreValue"), 0);
                        int hintUsedCount = asInt(pJSON.get("hintUsedCount"), 0);
                        String item = (String) pJSON.getOrDefault("item", "");
                        String itemNeeded = (String) pJSON.getOrDefault("itemNeeded", "");

                        // Convert difficulty string to enum with fallback
                        Difficulty difficulty = Difficulty.EASY;
                        try {
                            Object diffObj = pJSON.get("difficulty");
                            if (diffObj != null) difficulty = Difficulty.valueOf(diffObj.toString().toUpperCase());
                        } catch (Exception ex) {
                            difficulty = Difficulty.EASY;
                        }

                        // Construct BasicPuzzle
                        BasicPuzzle bp = new BasicPuzzle(
                                puzzleID,
                                puzzleType,
                                question,
                                solution,
                                hints,
                                imagePaths,
                                isSolved,
                                attempts,
                                maxAttempts,
                                scoreValue,
                                hintUsedCount,
                                difficulty,
                                item
                        );

                        // itemNeeded becomes requireItem
                        if (itemNeeded != null && !itemNeeded.isBlank()) {
                            bp.requireItem(itemNeeded);
                        }

                        puzzles.add(bp);
                    }
                }

                roomList.add(new Room(roomID, roomName, description, puzzles));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomList;
    }

    // helper to convert Number/Long/etc to int with default
    private static int asInt(Object o, int defaultVal) {
        if (o == null) return defaultVal;
        if (o instanceof Number) return ((Number) o).intValue();
        try {
            return Integer.parseInt(o.toString());
        } catch (Exception e) {
            return defaultVal;
        }
    }
}