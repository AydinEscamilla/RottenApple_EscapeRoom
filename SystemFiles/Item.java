public class Item {
    private final int itemID;
    private final String name;
    private final boolean usable;
    

    public Item (int itemID, String name, boolean usable) {
        this.itemID = itemID;
        this.name = name;
        this.usable = usable;
    }

    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public boolean isUsable() {
        return usable;
    }
}
