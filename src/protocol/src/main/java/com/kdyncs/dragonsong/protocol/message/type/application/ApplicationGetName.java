package com.kdyncs.dragonsong.protocol.message.type.application;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;

public class ApplicationGetName extends Message {
    
    private final static MessageType type = MessageType.APPLICATION_NAME;
    
    public ApplicationGetName(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationGetName() {
        this(Keyinator.generateGUID());
    }
}
