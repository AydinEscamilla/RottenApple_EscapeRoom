import java.util.List;
import org.json.simple.*

public class DataLoader extends DataConstants {

    public List<Users> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            FileReader reader = new FileReader();
            JSONParser parser = newJSONParser();
            JSONArray userJSON = (JSONArray)new JSONParser().parse(reader);

            for (int i=0; i<userJSON.size(); i++) {
                JSONObject userJSON;
                UUID userID;
                String username;
                String password;
                
            }
        }
            catch {
                // Exception
        }
    }

    public List<Game> getGame() {
        return null;
    }

    public List<Rooms> getRoom() {
        return null;
    }

    public List<Puzzle> getPuzzle() {
        return null;
    }

    public Leaderboard getLeaderboard() {
        return null;
    }

    public Settings getSettings() {
        return null;
    }
}
