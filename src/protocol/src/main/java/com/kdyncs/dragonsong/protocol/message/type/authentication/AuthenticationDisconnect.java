package com.kdyncs.dragonsong.protocol.message.type.authentication;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

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
