public class Rooms {
    private int roomID;
    private String roomName;
    private String description;
    private List<Puzzle> puzzles;
    private boolean isCleared; 
    private int roomOrder;
    private List<Rooms> prerequisiteRooms;

    public Rooms(int id, String name, String description) {

    }

    public void addPuzzle(Puzzle puzzle) {

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

    public boolean hasAccess(List<Rooms> completedRooms){
        return false;
    }

    public Rooms getNextRoom() {
        return null;
    }

    public Rooms moveToRoom(int roomID) {
        return null;
    }
}
