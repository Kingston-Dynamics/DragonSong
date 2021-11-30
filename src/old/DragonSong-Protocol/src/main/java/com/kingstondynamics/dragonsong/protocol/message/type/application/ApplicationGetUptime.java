package com.kingstondynamics.dragonsong.protocol.message.type.application;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;

public class ApplicationGetUptime extends Message {
    
    private final static MessageType type = MessageType.APPLICATION_UPTIME;
    
    public ApplicationGetUptime(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationGetUptime() {
        this(Keyinator.generateGUID());
    }
}
