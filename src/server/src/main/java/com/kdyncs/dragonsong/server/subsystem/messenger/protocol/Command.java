package com.kdyncs.dragonsong.server.subsystem.messenger.protocol;

public class Command {
    
    private final String issuer;
    private final byte[] command;
    
    public Command(String issuer, byte[] command) {
        this.issuer = issuer;
        this.command = command;
    }
    
    public String getIssuer() {
        return issuer;
    }
    
    public byte[] getCommand() {
        return command;
    }
}
