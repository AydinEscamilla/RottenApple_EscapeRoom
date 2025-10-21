package SystemFiles;


public class User {
    private String UUID;          
    private String username;      
    private String password;      
    private int userID;           
    private Settings preferences; 
    private User user;            

    // Constructor used when creating a new user (sign-up)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.UUID = java.util.UUID.randomUUID().toString();
        this.userID = 0;
        this.preferences = new Settings();
        this.user = this;
    }

    
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int id) {
        this.userID = id;
    }

    public Settings getPreferences() {
        return this.preferences;
    }

    public void setPreferences(Settings settings) {
        this.preferences = settings;
    }
}
