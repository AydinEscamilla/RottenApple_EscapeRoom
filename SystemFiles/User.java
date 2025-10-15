import org.json.simple.*;

public class User {
    private String uuid;
    private String username;
    private String password;
    private int userID;
    private Settings preferences;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.uuid = java.util.UUID.randomUUID().toString();
        this.userID = 0; 
        this.preferences = new Settings();
    }

public String getUsername(){
        return null;
    }

public void setUsername(String username){

    }

public String getPassword(){
        return null;
    }

public void setPassword(String password){

    }

public static int getUserID(){
        return 0;
    }

public void setUserID(int id){

    }

public Settings getPreferences(){
        return null;
    }

public void setPreferences(Settings settings){

    }
}