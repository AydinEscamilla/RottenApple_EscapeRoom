import java.util.ArrayList;

public class Users {
    private static Users users;
    private ArrayList<User> userList;

    private UserList() {
        userList = DataLoader.getUsers();
    }

    public static UserList getInstance() {
        if(users == null) {
            users = new Users();
        }
    }

    public boolean haveUser (String username) {
        for(User user : userList) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    public User getUser(String username) {
        for(User user : userList) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        
        return null;
    }

    public ArrayList<User> getUsers() {
        return userList;
    }

    public boolean addUser(String username, String password) {
        if(haveUser(username)) {
            return false;
        }

        userList.add(new User(username, password));
        return true;
    }

    public void saveUsers() {
        DataWriter.saveUsers();
    }
}
