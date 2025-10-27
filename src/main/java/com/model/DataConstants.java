/**
 * @Author: Rotten Apple
 * CSCE247
 */

package com.model;

/**
 * DataConstants centralizes string constants for JSON file names and JSON accessors for
 * DataLoader and DataWriter
 * 
 * Keeping them in a single place is just what we've learned to be best practice this semseter
 * in order to reduce dupes and typos (i.e. DataLoader/DataWriter accessing JSON)
 * 
 * Abstract to prevent instantiating, all classes and members are static final constants
 * 
 * UserFields -- keys and file names for user persistence
 * RoomFields -- keys and file name for room data/puzzle data
 * Puzzles -- keys used for individual puzzle attributes
 */
public abstract class DataConstants {
    /**
     * UserFields contains constants used when serializing and deserializing User objects
     * to-from JSON
     * 
     * Includes user JSON file names and JSON keys for user attributes such as username and uuid
     */
    public static final class UserFields {
        protected static final String USER_FILE_NAME = "json/user.json";
        protected static final String USER_TEMP_FILE_NAME = "json/user_temp.json";
        protected static final String UUID = "UUID";
        protected static final String USERNAME = "username";
        protected static final String PASSWORD = "password";
        protected static final String CURRENT_GAME = "currentGame";
        protected static final String CURRENT_ROOM = "currentRoom";
        protected static final String LAST_PUZZLE = "lastPuzzle";
        protected static final String COMPLETED = "puzzlesComplete";
        protected static final String ITEMS = "items";
    }

    /**
     * RoomFields groups constants used for reading/writing room level JSON data with rooms.json
     * 
     * Keys are for Room attributes such as roomID and description
     */
    public static final class RoomFields {
        protected static final String ROOM_FILE = "json/rooms.json";
        protected static final String ROOM_ID = "roomID";
        protected static final String ROOM_NAME = "roomName";
        protected static final String DESCRIPTION= "description";
        protected static final String PUZZLES = "puzzles";
        protected static final String IS_CLEARED = "isCleared";
        protected static final String ROOM_ORDER = "roomOrder";
        protected static final String PREREQUISITE_ROOMS = "prerequisiteRooms";
    }

    /**
     * Puzzles defines keys used to represent individual puzzle attributes within a room's 
     * puzzles array
     * 
     * Keys are good for puzzle ID, type, question, solution, and more
     */
    public static final class Puzzles {
        protected static final String PUZZLE_ID = "puzzleID";
        protected static final String PUZZLE_TYPE = "puzzleType";
        protected static final String QUESTION = "question";
        protected static final String SOLUTION = "solution";
        protected static final String HINTS = "hints";
        protected static final String IMAGE_PATH= "imagePath";
        protected static final String IS_SOLVED = "isSolved";
        protected static final String ATTEMPS = "attemps";
        protected static final String MAX_ATTEMPS = "maxAttemps";
        protected static final String SCORE_VALUE= "scoreValue";
        protected static final String HINT_USED_COUNT= "hintUsedCount";
        protected static final String DIFFICULTY = "Difficulty";
                

    }
}