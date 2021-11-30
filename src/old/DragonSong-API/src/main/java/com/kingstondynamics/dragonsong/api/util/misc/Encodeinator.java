package com.kingstondynamics.dragonsong.api.util.misc;

import java.util.Base64;

public class Encodeinator {
    
    // Blocked Private Constructor
    // The Exception prevents initialization via reflection
    private Encodeinator() {
        throw new UnsupportedOperationException();
    }
    
    public static byte[] encodeinate(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }
    
    public static byte[] decodeinate(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }
}
