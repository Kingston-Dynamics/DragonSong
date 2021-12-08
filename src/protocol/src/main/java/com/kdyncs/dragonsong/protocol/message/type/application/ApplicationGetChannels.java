package com.kdyncs.dragonsong.protocol.message.type.application;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;

public class ApplicationGetChannels extends Message {
    
    private final static MessageType type = MessageType.APPLICATION_CHANNEL_LIST;
    
    public ApplicationGetChannels(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationGetChannels() {
        this(Keyinator.generateGUID());
    }
}
