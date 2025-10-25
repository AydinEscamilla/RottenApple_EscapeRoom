package systemfiles;

import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import SystemFiles.DataConstants;   // import the constants class (it's in folder SystemFiles)

public class DataLoader {
    private String filename;

    // no-arg constructor uses constant from DataConstants (adjust name if your constant differs)
    public DataLoader() {
        this(DataConstants.USER_FILE_NAME != null ? DataConstants.USER_FILE_NAME : "user.json");
    }

    public DataLoader(String filename) {
        this.filename = filename;
    }

    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        List<User> users = loader.loadUsers();

        for (User user : users) {
            // print a meaningful representation
            System.out.println("User: " + user.getUsername() + " (UUID=" + user.getID() + ")");
        }
    }

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File f = new File(this.filename);
        if (!f.exists()) return users;

        try (FileReader fr = new FileReader(f)) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fr);

            if (obj instanceof JSONArray) {
                JSONArray arr = (JSONArray) obj;
                for (Object o : arr) {
                    if (o instanceof JSONObject) {
                        JSONObject jo = (JSONObject) o;

                        // read fields - try to be permissive about presence of UUID
                        String username = (String) jo.get("username");
                        String password = (String) jo.get("password");

                        // If JSON has UUID string, parse it; otherwise use User(String,String) constructor
                        Object uuidObj = jo.get("UUID");
                        if (uuidObj != null) {
                            try {
                                java.util.UUID uUUID = java.util.UUID.fromString((String) uuidObj);
                                users.add(new User(uUUID, username, password));
                            } catch (IllegalArgumentException iae) {
                                // bad UUID format -> fall back to random UUID
                                users.add(new User(username, password));
                            }
                        } else {
                            users.add(new User(username, password));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // left for compatibility (if other code calls DataLoader.getUsers())
    public List<User> getUsers() {
        return loadUsers();
    }

    public List<Rooms> getRoom() {
        return new ArrayList<>();
    }
}
