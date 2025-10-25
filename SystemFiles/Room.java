import java.util.ArrayList;
import java.util.List;


public class Room {
    private int roomID;
    private String roomName;
    private String description;
    private List<Puzzle> puzzles = new ArrayList<>();
    private boolean isCleared = false; 
    private int roomOrder = 0;
    private List<Room> prerequisiteRooms = new ArrayList();

    public Room(int id, String name, String description) {
        this.roomID = id;
        this.roomName = name;
        this.description = description;

    }

    public void addPuzzle(Puzzle puzzle) {
        Puzzle PP1 = PicturePuzzle.FingerPrintMatch();
        Puzzle PP2 = PicturePuzzle.CameraPuzzle();
        Puzzle MP1 = MathPuzzle.PemdasLock();
        Puzzle MP2 = MathPuzzle.AmmoBox();
        Puzzle LP1 = LogicPuzzle.DuckPuzzle();
        
        puzzles.add(PP1);
        puzzles.add(PP2);
        puzzles.add(MP1);
        puzzles.add(MP2);
        puzzles.add(LP1);

    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void clearRoom() {

    }

    public boolean isRoomCleared() {
        return isCleared;
    }

    public List<Puzzle> getUnsolvedPuzzles() {
        return null;
    }

    public boolean hasAccess(List<Room> completedRooms){
        return false;
    }

    public Room getNextRoom() {
        return null;
    }

    public Room moveToRoom(int roomID) {
        return null;
    }
}
