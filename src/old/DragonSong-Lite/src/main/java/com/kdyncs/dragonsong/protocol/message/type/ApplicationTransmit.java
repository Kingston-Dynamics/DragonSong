package com.kdyncs.dragonsong.protocol.message.type;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;

public class ApplicationTransmit extends Message {
    
    private static final MessageType type = MessageType.APPLICATION_TRANSMIT;
    
    public ApplicationTransmit(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationTransmit() {
        this(Keyinator.generateGUID());
    }
}
