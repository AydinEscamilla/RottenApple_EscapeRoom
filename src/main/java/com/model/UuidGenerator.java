package com.model;

import java.util.UUID;

public class UuidGenerator {
    public static void main(String[] args) {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert the UUID to its standard string representation
        String uuidString = uuid.toString();
    }
}