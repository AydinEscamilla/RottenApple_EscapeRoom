/**
 * @Author: Rotten Apple
 * CSCE247
 */

package com.model;

import java.util.UUID;

/**
 * UuidGenerator is a utility class demonstarting and storing the method to generate
 * UUIDs for Java
 * 
 * The class does not print any output by default, and in that way is somewhat for demonstration,
 * but it is still a class in the same directory and can be called by the User constructor (which it is)
 */
public class UuidGenerator {
    /**
    * Generates a random UUID and converts it to its string representation.
    * 
    * @param args unused command-line arguments
    */
    public static void main(String[] args) {
        // Generates a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert the UUID to its standard string representation
        String uuidString = uuid.toString();
    }
}