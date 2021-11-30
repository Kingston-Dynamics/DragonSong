package com.kdyncs.dragonsong.protocol.message.type;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;

public class ApplicationGetChannels extends Message {

    private static final MessageType type = MessageType.APPLICATION_CHANNEL_LIST;
    
    public ApplicationGetChannels(String auditId) {
        super(type.getType(), auditId);
    }
    
    public ApplicationGetChannels() {
        this(Keyinator.generateGUID());
    }
}
