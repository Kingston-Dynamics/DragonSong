package com.kingstondynamics.dragonsong.protocol.message.type.authentication;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;

public class AuthenticationReconnect extends Message {
    
    private final static MessageType type = MessageType.AUTHENTICATION_RECONNECT;
    
    public AuthenticationReconnect(String auditId) {
        super(type.getType(), auditId);
    }
    
    public AuthenticationReconnect() {
        this(Keyinator.generateGUID());
    }
}
