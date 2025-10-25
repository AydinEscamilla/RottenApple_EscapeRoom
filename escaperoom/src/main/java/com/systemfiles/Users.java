package systemfiles;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static Users instance;
    private ArrayList<User> userList;

    private Users() {
        // instantiate DataLoader (its loadUsers() is non-static)
        DataLoader loader = new DataLoader();
        List<User> loaded = loader.loadUsers();
        userList = new ArrayList<>(loaded);
    }

    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public boolean haveUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return userList;
    }

    // changed to accept a User object (preferred)
    public boolean addUser(User user) {
        if (haveUser(user.getUsername())) {
            return false;
        }
        userList.add(user);
        return true;
    }

    // helper that matches previous usage if you still call addUser(username,password)
    public boolean addUser(String username, String password) {
        return addUser(new User(username, password));
    }

    public void saveUsers() {
        DataWriter.saveUsers();
    }
}
