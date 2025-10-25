import java.util.*;
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
     * Updates the cleared status of the room based on its puzzles
     */
    public void updatedClearedStatus() {
        this.isCleared = puzzles.stream().allMatch(Puzzle::solved);
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

    /*
     * Checks if the room can be accessed based on completed rooms
     * @return true if all prerequisite rooms are cleared, false otherwise
     */
    public boolean hasAccess(List<Room> completedRooms){
        if(prerequisiteRooms.isEmpty()) return true;
        return prerequisiteRooms.stream().allMatch(Room::isRoomCleared);
    }

    public int getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getDescription() {
        return description;
    }

    public int getRoomOrder() {
        return roomOrder;
    }

    public List<Room> getPrerequisiteRooms() {
        return prerequisiteRooms;
    }

    public Room getNextRoom() {
        return null;
    }

    public Room moveToRoom(int roomID) {
        return null;
    }
}

 class RoomTest {
    public static void main (String[] args) {
        Room r1 = new Room(1, "Entrance Hall", "The starting point of your escape room adventure.");
        Room r2 = new Room(2, "Library", "A room filled with ancient books and puzzles.");
        Room r3 = new Room(3, "Laboratory", "A high-tech room with scientific equipment.");

        r2.addPrerequisitie(r1);
        r3.addPrerequisitie(r2);

        Puzzle p1 = new LogicPuzzle(101, "What has keys but can't open locks?", List.of("Piano", "A piano"));
        Puzzle p2 = new LogicPuzzle(102, "What comes once in a minute, twice in a moment, but never in a thousand years?", List.of("The letter M", "M"));

        r1.addPuzzles(p1);
        r2.addPuzzles(p2);

        System.out.println("Room 1 ID: " + r1.getRoomID());
        System.out.println("Room 2 Name: " + r2.getRoomName());
        System.out.println("Room 3 Description: " + r3.getDescription());

        System.out.println("Room 2 Prerequisites: " + r2.getPrerequisiteRooms().stream().map(Room::getRoomName).collect(Collectors.joining(", ")));
    }
}
