import org.json.simple.*;

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
}
