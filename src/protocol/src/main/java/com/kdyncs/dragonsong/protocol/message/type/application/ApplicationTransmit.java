package com.kdyncs.dragonsong.protocol.message.type.application;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;

public class ApplicationTransmit extends Message {
    
    private final static MessageType type = MessageType.APPLICATION_TRANSMIT;
    
    public ApplicationTransmit(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationTransmit() {
        this(Keyinator.generateGUID());
    }
}
