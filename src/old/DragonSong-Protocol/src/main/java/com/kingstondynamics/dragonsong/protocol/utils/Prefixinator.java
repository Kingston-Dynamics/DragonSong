package com.kingstondynamics.dragonsong.protocol.utils;

public class Prefixinator {
    
    /**
     * Private Constructor for Utility Class
     */
    private Prefixinator() {
        throw new AssertionError();
    }
    
    public static byte[] prefix(byte[] data) {
        return Concatinator.ConcatinateByteArrays(Byteinator.intToBytes(data.length), data);
    }
}
