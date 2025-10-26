package com.model;

import java.util.*;

public class ItemRegistry {
    private static Map<Integer, Item> items = new HashMap<>();

    public static final Item Camera_Passcode = register(new Item(1, "Camera Passcode", true));
    public static final Item Magnifying_Glass = register (new Item(2, "Magnifying Glass", false));

    /*
     * Registers an item in the registry
     */
    private static Item register(Item item) {
        items.put(item.getItemID(), item);
        return item;
    }

    /*
     * Gets an item by its ID
     */
    public static Item getItemByID(int id) {
        return items.get(id);
    }
    
}