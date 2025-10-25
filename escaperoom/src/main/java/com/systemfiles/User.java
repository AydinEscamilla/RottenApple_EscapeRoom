package systemfiles;

import java.util.*;

public class User {
    private UUID uuid;
    private String username;
    private String password;

    public User(String username, String password) {
        this.uuid = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public User(UUID uuid, String username, String password) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
    }

    public UUID getID() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
