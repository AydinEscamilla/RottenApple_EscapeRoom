package SystemFiles;

import java.io.*;
import java.util.*;
import org.json.simple.*;

public class DataWriter extends DataConstants {

    public static void saveUsers() {
        Users users = Users.getInstance();
        ArrayList<User> userList = users.getUsers();

        JSONArray jsonUsers = new JSONArray();

        for(int i=0; i<userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getUserJSON(User user) {
        userDetails.put(USER_ID, user.getID().toString());
        userDetails.put(USERNAME, user.getUsername().toString());
        userDetails.put(PASSWORD, user.getPassword().toString());

        return userDetails;
    }

}

