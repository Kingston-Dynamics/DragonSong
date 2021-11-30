package com.kdyncs.dragonsong.protocol.message.type;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;

public class AuthenticationLogout extends Message {

    private static final MessageType type = MessageType.AUTHENTICATION_LOGOUT;
    
    public AuthenticationLogout(String auditId) {
        super(type.getType(), auditId);
    }
    
    public AuthenticationLogout() {
        this(Keyinator.generateGUID());
    }
}
