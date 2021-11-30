package com.kdyncs.dragonsong.server.messenger.protocol;

public class Command {
    
    private final String issuer;
    private final byte[] data;
    
    public Command(String issuer, byte[] data) {
        this.issuer = issuer;
        this.data = data;
    }
    
    public String getIssuer() {
        return issuer;
    }
    
    public byte[] getData() {
        return data;
    }
}
