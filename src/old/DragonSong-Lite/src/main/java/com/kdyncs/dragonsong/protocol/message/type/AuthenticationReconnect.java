package com.kdyncs.dragonsong.protocol.message.type;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;

public class AuthenticationReconnect extends Message {

    private static final MessageType type = MessageType.AUTHENTICATION_RECONNECT;
    
    public AuthenticationReconnect(String auditId) {
        super(type.getType(), auditId);
    }
    
    public AuthenticationReconnect() {
        this(Keyinator.generateGUID());
    }
}
