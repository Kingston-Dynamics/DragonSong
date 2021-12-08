package com.kingstondynamics.dragonsong.protocol.message.type.application;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;

public class ApplicationGetName extends Message {
    
    private final static MessageType type = MessageType.APPLICATION_NAME;
    
    public ApplicationGetName(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationGetName() {
        this(Keyinator.generateGUID());
    }
}
