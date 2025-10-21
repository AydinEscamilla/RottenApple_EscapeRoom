import org.json.simple.*;
import org.json.simple.parser.*;

public class User {
    private String uuid;
    private String username;
    private String password;
    private int userID;
    private Settings preferences;

    public User(String username, String password) {
        this(username, password, new Settings());
    }

    public User(String username, String password, Settings preferences) {
        this.username = username;
        this.password = password;
        this.uuid = java.util.UUID.randomUUID().toString();
        this.userID = 0;
        this.preferences = preferences == null ? new Settings() : preferences;
    }

    public User(String uuid, String username, String password, int userID, Settings preferences) {
        this.uuid = (uuid == null || uuid.isBlank()) ? java.util.UUID.randomUUID().toString() : uuid;
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.preferences = preferences == null ? new Settings() : preferences;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int getUserID(){
        return this.userID;
    }

    public void setUserID(int id){
        this.userID = id;
    }

    public Settings getPreferences(){
        return this.preferences;
    }

    public void setPreferences(Settings settings){
        this.preferences = settings;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", uuid='" + uuid + '\'' + '}';
    }

    /**
     * Convert this user to a JSONObject matching your file structure.
     * preferences will be written as a single-element JSONArray (matching your sample).
     */
    @SuppressWarnings("unchecked")
    public JSONObject toJSONObject() {
        JSONObject o = new JSONObject();
        o.put("UUID", this.uuid);
        o.put("username", this.username);
        o.put("password", this.password);

        // preferences as an array with one object
        JSONArray prefArr = new JSONArray();
        JSONObject prefObj = new JSONObject();

        try {
            if (this.preferences != null) {
                // try common getter names - if they don't exist, reflection calls below will silently skip
                try {
                    Object audioVal = this.preferences.getClass().getMethod("getAudioVolume").invoke(this.preferences);
                    prefObj.put("audioVolume", audioVal);
                } catch (NoSuchMethodException ignored) {}
                try {
                    Object musicOn = this.preferences.getClass().getMethod("isMusicOn").invoke(this.preferences);
                    prefObj.put("musicOn", musicOn);
                } catch (NoSuchMethodException ignored) {}
                try {
                    Object musicOn2 = this.preferences.getClass().getMethod("getMusicOn").invoke(this.preferences);
                    prefObj.put("musicOn", musicOn2);
                } catch (NoSuchMethodException ignored) {}
                try {
                    Object sfxOn = this.preferences.getClass().getMethod("isSoundEffectsOn").invoke(this.preferences);
                    prefObj.put("soundEffectsOn", sfxOn);
                } catch (NoSuchMethodException ignored) {}
                try {
                    Object sfxOn2 = this.preferences.getClass().getMethod("getSoundEffectsOn").invoke(this.preferences);
                    prefObj.put("soundEffectsOn", sfxOn2);
                } catch (NoSuchMethodException ignored) {}
                try {
                    Object textSize = this.preferences.getClass().getMethod("getTextSize").invoke(this.preferences);
                    prefObj.put("textSize", textSize);
                } catch (NoSuchMethodException ignored) {}
            }
        } catch (Exception e) {
            // if reflection failed for any reason, leave prefObj possibly empty — DataWriter/DataLoader are defensive.
        }

        // If nothing was set, put some sensible defaults to keep file consistent with your sample
        if (prefObj.isEmpty()) {
            prefObj.put("audioVolume", 50);
            prefObj.put("musicOn", true);
            prefObj.put("soundEffectsOn", true);
            prefObj.put("textSize", 14);
        }

        prefArr.add(prefObj);
        o.put("preferences", prefArr);

        // leave "progress" out here (DataWriter manages progress serialization separately in your codebase)
        return o;
    }

    /**
     * Build a User from a JSONObject (from your user.json).
     * This attempts to populate preferences via reflection if Settings setters exist.
     */
    public static User fromJSONObject(org.json.simple.JSONObject o) {
        if (o == null) return null;
        String uuid = (String) o.get("UUID");
        String username = (String) o.get("username");
        String password = (String) o.get("password");
        int userID = 0;
        // try reading userID if present (might be absent)
        Object idObj = o.get("userID");
        if (idObj instanceof Number) {
            userID = ((Number) idObj).intValue();
        }

        // create default Settings and try to populate from JSON if present
        Settings settings = new Settings();
        try {
            Object prefsObj = o.get("preferences");
            if (prefsObj instanceof org.json.simple.JSONArray) {
                org.json.simple.JSONArray prefArr = (org.json.simple.JSONArray) prefsObj;
                if (!prefArr.isEmpty() && prefArr.get(0) instanceof org.json.simple.JSONObject) {
                    org.json.simple.JSONObject pref = (org.json.simple.JSONObject) prefArr.get(0);

                    // Try to set properties via reflection (safe — missing methods are ignored)
                    try {
                        java.lang.reflect.Method m;
                        if (pref.get("audioVolume") != null) {
                            m = settings.getClass().getMethod("setAudioVolume", int.class);
                            m.invoke(settings, ((Number) pref.get("audioVolume")).intValue());
                        }
                    } catch (Exception ignored) {}

                    try {
                        if (pref.get("musicOn") != null) {
                            java.lang.reflect.Method m = settings.getClass().getMethod("setMusicOn", boolean.class);
                            m.invoke(settings, Boolean.parseBoolean(pref.get("musicOn").toString()));
                        }
                    } catch (Exception ignored) {}

                    try {
                        if (pref.get("soundEffectsOn") != null) {
                            java.lang.reflect.Method m = settings.getClass().getMethod("setSoundEffectsOn", boolean.class);
                            m.invoke(settings, Boolean.parseBoolean(pref.get("soundEffectsOn").toString()));
                        }
                    } catch (Exception ignored) {}

                    try {
                        if (pref.get("textSize") != null) {
                            java.lang.reflect.Method m = settings.getClass().getMethod("setTextSize", int.class);
                            m.invoke(settings, ((Number) pref.get("textSize")).intValue());
                        }
                    } catch (Exception ignored) {}
                }
            }
        } catch (Exception ignored) {}

        return new User(uuid, username, password, userID, settings);
    }
}
