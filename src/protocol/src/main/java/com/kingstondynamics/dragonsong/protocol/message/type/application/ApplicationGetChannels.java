package com.kingstondynamics.dragonsong.protocol.message.type.application;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;

public class ApplicationGetChannels extends Message {
    
    private final static MessageType type = MessageType.APPLICATION_CHANNEL_LIST;
    
    public ApplicationGetChannels(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationGetChannels() {
        this(Keyinator.generateGUID());
    }
}
