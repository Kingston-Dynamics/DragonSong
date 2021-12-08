package com.kingstondynamics.dragonsong.protocol.message.type.authentication;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;
import com.kingstondynamics.dragonsong.protocol.utils.Readinator;

public class AuthenticationDisconnect extends Message {
    
    private final static MessageType type = MessageType.AUTHENTICATION_DISCONNECT;
    
    public AuthenticationDisconnect(String auditId) {
        super(type.getType(), auditId);
    }
    
    public AuthenticationDisconnect() {
        this(Keyinator.generateGUID());
    }
    
    public AuthenticationDisconnect(Readinator reader) {
        super(reader);
    }
}
