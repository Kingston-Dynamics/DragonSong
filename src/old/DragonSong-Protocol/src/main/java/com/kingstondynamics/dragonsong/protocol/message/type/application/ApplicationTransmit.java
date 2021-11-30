package com.kingstondynamics.dragonsong.protocol.message.type.application;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;

public class ApplicationTransmit extends Message {
    
    private final static MessageType type = MessageType.APPLICATION_TRANSMIT;
    
    public ApplicationTransmit(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationTransmit() {
        this(Keyinator.generateGUID());
    }
}
