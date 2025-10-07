import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

    public boolean saveUsers(List<Users> users) {
        users.add(User.getUserID()); //  adding User to array list of Users with id
        String filename = "save.txt"; //  the file to write to

        try (FileWriter writer = new FileWriter("filename")) {
            writer.write("New save file");
            System.out.println("Overwritten: " + filename);
            
        } catch (Exception e) {
            // TODO: handle exception
        }



        return false;
    }

    public boolean saveProgress(User user, Room roomID, Game gameID, Puzzle puzzleID) {
        return false;
    }

    public boolean updateSettings(User user, Settings settings) {
        return false;
    }

    public boolean addEntry(User user, Score score) {
        return false;
    }
}
