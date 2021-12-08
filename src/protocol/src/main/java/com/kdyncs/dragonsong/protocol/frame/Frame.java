package com.kdyncs.dragonsong.protocol.frame;

/**
 * @author peter
 */
public class Frame {
    
    // Frame Contents
    private final int size;
    private final byte[] data;
    
    public Frame(byte[] data) {
        this.data = data;
        size = data.length;
    }
    
    public int getSize() {
        return size;
    }
    
    public byte[] getData() {
        return data.clone();
    }
}
