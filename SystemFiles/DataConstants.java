public abstract class DataConstants {
    public static final class User {
        protected static final String USER_FILE_NAME = "json/user.json";
        protected static final String UUID = "UUID";
        protected static final String USERNAME = "username";
        protected static final String PASSWORD = "password";
        protected static final String USER_ID = "userID";
        protected static final String PREFERENCES = "preferences";
        protected static final String PROGRESS = "progress";
    }
    
    public static final class Preferences {
        protected static final String AUDIO_VOLUME = "audioVolume";
        protected static final String MUSIC_ON = "musicOn";
        protected static final String SOUND_EFFECTS_ON = "soundEffectsOn";
        protected static final String TEXT_SIZE = "textSize";
    }

    public static final class Progress {
        protected static final String SCORE = "score";
        protected static final String TIME_TAKEN = "timeTaken";
        protected static final String DATE_ACHEIEVED = "dateAchieved";
        protected static final String RANK = "rank";
        protected static final String ROOMS_CLEARED = "roomsCleared";
        protected static final String PUZZLES_SOLVED = "puzzlesSolved";
        protected static final String CURRENT_GAME = "currentGame";
        protected static final String CURRENT_ROOM = "currentRoom";
        protected static final String LAST_PUZZLE  = "lastPuzzle";
        
    }

    public static final class Room {
        protected static final String ROOM_ID = "roomID";
        protected static final String ROOM_NAME = "roomName";
        protected static final String DESCRIPTION= "description";
        protected static final String PUZZLES = "puzzles";
        protected static final String IS_CLEARED = "isCleared";
        protected static final String ROOM_ORDER = "roomOrder";
        protected static final String PREREQUISITE_ROOMS = "prerequisiteRooms";
    }

    public static final class Puzzles {
        protected static final String PUZZLE_ID = "puzzleID";
        protected static final String PUZZLE_TYPE = "puzzleType";
        protected static final String QUESTION = "question";
        protected static final String SOLUTION = "solution";
        protected static final String HINTS = "hints";
        protected static final String IMAGE_PATH= "imagePath";
        protected static final String IS_SOLVED = "isSolved";
        protected static final String ATTEMPTS = "attemps";
        protected static final String MAX_ATTEMPTS = "maxAttemps";
        protected static final String SCORE_VALUE= "scoreValue";
        protected static final String HINT_USED_COUNT= "hintUsedCount";
        protected static final String DIFFICULTY = "Difficulty";
                

    }
}
