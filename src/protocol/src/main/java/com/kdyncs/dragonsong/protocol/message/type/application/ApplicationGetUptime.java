package com.kdyncs.dragonsong.protocol.message.type.application;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;

public class ApplicationGetUptime extends Message {
    
    private final static MessageType type = MessageType.APPLICATION_UPTIME;
    
    public ApplicationGetUptime(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationGetUptime() {
        this(Keyinator.generateGUID());
    }
}
