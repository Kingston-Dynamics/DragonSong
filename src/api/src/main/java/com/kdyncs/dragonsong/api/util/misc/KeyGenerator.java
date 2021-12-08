package com.kdyncs.dragonsong.api.util.misc;

import java.util.UUID;

public class KeyGenerator {
    
    // Blocked Private Constructor
    // The Exception prevents initialization via reflection
    private KeyGenerator() {
        throw new UnsupportedOperationException();
    }
    
    //Generates a GUID
    public static String generateKey() {
        return UUID.randomUUID().toString();
    }
}
