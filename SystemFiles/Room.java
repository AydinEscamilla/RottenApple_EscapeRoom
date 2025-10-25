import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    /*
     * Sets the order of the room in the game
     * @return the room with set order
     */
    public Room setOrder (int order) {
        this.roomOrder = order;
        return this;
    }

    /*
     * Adds a prerequisite room to the room
     * @return the room with added prerequisite
     */
    public Room addPrerequisitie (Room room) {
        if (room != null)
        prerequisiteRooms.add(room);
        return this;
    }

    /*
     * Adds a puzzle to the room
     * @return the room with added puzzle
     */
    public Room addPuzzle(Puzzle puzzle) {
        if (puzzle != null)
        puzzles.add(puzzle);
        return this;

    }

    /*
     * Adds multiple puzzles to the room at once
     * @return the room with added puzzles
     */
    public Room addPuzzles (Puzzle...list) {
        for (Puzzle p : list) {
            addPuzzle(p);
        }
        return this;
    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void clearRoom() {

    }

    public boolean isRoomCleared() {
        return isCleared;
    }

    /*
     * 
     * @return list of unsolved puzzles in the room
     */

    public List<Puzzle> getUnsolvedPuzzles() {
        List<Puzzle> unsolvedP = new ArrayList<>();
        for (Puzzle p : puzzles) {
            if (!p.solved()) {
                unsolvedP.add(p);
            }
        }
        return unsolvedP;
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
