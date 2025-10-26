import java.util.*;

public class Inventory {
    private Set <Integer> bag = new HashSet<>();

    /*
     * Adds an item to the inventory
     */
    public void addItem (int itemID) {
        bag.add(itemID);
    }

    /*
     * Checks if the inventory has an item
     */
    public boolean hasItem (int itemID) {
        return bag.contains(itemID);
    }

    /*
     * Uses an item from the inventory, removes it if usable
     */
    public boolean useItem (int itemID, boolean usable) {
        if (!bag.contains(itemID)) return false;
        if (usable) {
            bag.remove(itemID);
        }
        return true;
    }

    /*
     * @return an unmodifiable set of saved items when saves
     */
    public Set <Integer> savedItems () {
        return Collections.unmodifiableSet(bag);
    }

    
}
