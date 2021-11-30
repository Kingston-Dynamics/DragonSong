package com.kingstondynamics.dragonsong.protocol.utils;

import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;

public class Determinator {
    
    /**
     * Private Constructor for Utlity Class
     */
    private Determinator() {
        throw new AssertionError();
    }
    
    public static MessageType determinate(byte[] data) {
        
        // Load Data to be Read
        Readinator reader = new Readinator(data);
        
        // Check first four bytes
        int opcode = reader.readInt();
        
        // Return Type
        return MessageType.fromValue(opcode);
    }
}
