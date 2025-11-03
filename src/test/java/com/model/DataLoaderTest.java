package com.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Ignore DataLoaderTest to isolate other tests")

class DataLoaderTest {

    private List<User> users;
    private List<Room> rooms;

    @BeforeEach
    void setup() {
        users = DataLoader.getUsers();
        rooms = DataLoader.getRooms();
    }
    /**
     * Checks that each username is unique
     */
    @Test
    void testDuplicateUsernames() {
        Set<String> seen = new HashSet<>();
        for (User u : users) {
            assertTrue(seen.add(u.getUsername()),
                    "Duplicate username detected: " + u.getUsername());
        }
    }
    /**
     * Checks that the user is in a valid room 
     */
    @Test
    void testInvalidCurrentRoomReferences() {
        for (User u : users) {
            boolean validRoom = rooms.stream()
                    .anyMatch(r -> r.getRoomID() == u.getCurrentRoom());
            assertTrue(validRoom,
                    "User " + u.getUsername() + " references non-existent room ID " + u.getCurrentRoom());
        }
    }
    /**
     * Checks that progress numbers are not negative
     */
    @Test
    void testUsersHaveValidProgression() {
        for (User u : users) {
            assertTrue(u.getCurrentRoom() >= 0, "Room ID should not be negative");
            assertTrue(u.getLastPuzzle() >= 0, "Last puzzle index should not be negative");
        }
    }
    /**
     * Checks negative values in the hints used map
     */
    @Test
    void testHintsUsedMapIntegrity() {
        for (User u : users) {
            Map<Integer, Integer> map = u.getHintsUsedMap();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                assertTrue(entry.getValue() >= 0,
                        "Hint count for puzzle " + entry.getKey() + " should not be negative");
            }
        }
    }
    /**
     * Ensures every item the user has is a string
     */
    @Test
    void testUserInventoryConsistency() {
        for (User u : users) {
            for (String item : u.getItems()) {
                assertFalse(item.isBlank(),
                        "User " + u.getUsername() + " has an invalid (blank) item");
            }
        }
    }

    /**
     * Checks that the room has a ID
     */
    @Test
    void testRoomIDsAreUnique() {
        Set<Integer> ids = new HashSet<>();
        for (Room r : rooms) {
            assertTrue(ids.add(r.getRoomID()), "Duplicate room ID found: " + r.getRoomID());
        }
    }

    /**
     * Checks that each room has a name and description
     */
    @Test
    void testRoomHasDescriptionAndName() {
        for (Room r : rooms) {
            assertFalse(r.getRoomName().isBlank(),
                    "Room ID " + r.getRoomID() + " has a blank name");
            assertNotNull(r.getDescription(r),
                    "Room ID " + r.getRoomID() + " has null description");
        }
    }

    /**
     * Checks that all rooms have puzzles and that the puzzle works
     */
    @Test
    void testPuzzlesExistInRooms() {
        for (Room r : rooms) {
            assertNotNull(r.getPuzzles(),
                    "Room " + r.getRoomID() + " has null puzzle list");
            for (Puzzle p : r.getPuzzles()) {
                assertNotNull(p.getQuestion(),
                        "Puzzle in Room " + r.getRoomID() + " has null question");
                assertNotNull(p.getSolution(),
                        "Puzzle in Room " + r.getRoomID() + " has null solution");
            }
        }
    }
    /**
     * Checks that every room has at least one puzzle
     */
    @Test
    void testEmptyRoomsHaveFallbackBehavior() {
        for (Room r : rooms) {
            if (r.getPuzzles().isEmpty()) {
                fail("Room " + r.getRoomID() + " has no puzzles; DataLoader may not be populating puzzles correctly");
            }
        }
    }
    /**
     * Checks that every puzzle has a difficulty
     * 
     */
    @Test
    void testPuzzleDifficultyIntegrity() {
        for (Room r : rooms) {
            for (Puzzle p : r.getPuzzles()) {
                assertNotNull(p.getDifficulty(),
                        "Puzzle " + p.getPuzzleID() + " has null difficulty");
            }
        }
    }
    /**
     * Ensures that puzzle lists the items required properly
     * Also checks null
     */
    @Test
    void testItemDependenciesMakeSense() {
        for (Room r : rooms) {
            for (Puzzle p : r.getPuzzles()) {
                if (p.getRequiredItems() != null) {
                    for (String required : p.getRequiredItems()) {
                        assertFalse(required.isBlank(),
                                "Puzzle " + p.getPuzzleID() + " requires an invalid (blank) item");
                    }
                }
            }
        }
    }
}
