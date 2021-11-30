package com.kdyncs.dragonsong.protocol.message.data;

public enum MessageType {
    
    // Default Type
    UNKNOWN(0x0000),
    
    // Heartbeat Commands
    HEARTBEAT_PING(0x1000),
    HEARTBEAT_PONG(0x1001),
    
    // Notification Commands
    NOTIFICATION(0x2000),
    
    // Authentication Commands
    AUTHENTICATION_LOGIN(0x3000),
    AUTHENTICATION_LOGOUT(0x3001),
    AUTHENTICATION_RECONNECT(0x3002),
    AUTHENTICATION_DISCONNECT(0x3003),
    
    // Self Commands
    SELF_WHOAMI(0x4000),
    SELF_WHATAMI(0x4001),
    SELF_UPTIME(0x4002),
    SELF_TRANSMIT(0x4003),
    
    // User Commands
    USER_WHOIS(0x5000),
    USER_WHATIS(0x5001),
    USER_UPTIME(0x5002),
    USER_TRANSMIT(0x5003),
    USER_BLOCK(0x5004),
    USER_UNBLOCK(0x5005),
    
    // Server Commands
    SERVER_HOSTNAME(0x6000),
    SERVER_ADDRESS(0x6001),
    SERVER_PING_TIME(0x6002),
    SERVER_UPTIME(0x6003),
    SERVER_TRANSMIT(0x6007),
    
    // Application Commands
    APPLICATION_TRANSMIT(0x7002),
    APPLICATION_CHANNEL_LIST(0x7003),
    
    // Channel Commands
    CHANNEL_NAME(0x8000),
    CHANNEL_CONNECT(0x8001),
    CHANNEL_DISCONNECT(0x8002),
    CHANNEL_TRANSMIT(0x8003),
    CHANNEL_USER_LIST(0x8004),
    CHANNEL_USER_COUNT(0x8005);
    
    private final int type;
    
    MessageType(int type) {
        this.type = type;
    }
    
    public static MessageType fromValue(int type) {
        
        MessageType[] types = MessageType.values();
        
        for (MessageType t : types) {
            
            if (t.type == type) {
                return t;
            }
        }
        return UNKNOWN;
    }
    
    public int getType() {
        return type;
    }
}
