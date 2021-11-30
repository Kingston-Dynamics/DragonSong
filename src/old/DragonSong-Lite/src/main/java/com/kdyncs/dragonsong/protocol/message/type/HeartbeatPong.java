package com.kdyncs.dragonsong.protocol.message.type;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

public class HeartbeatPong extends Message {

    private static final MessageType type = MessageType.HEARTBEAT_PONG;
    
    public HeartbeatPong(String auditId) {
        super(type.getType(), auditId);
    }
    
    public HeartbeatPong() {
        this(Keyinator.generateGUID());
    }
    
    public HeartbeatPong(Readinator reader) {
        super(reader);
    }
}
