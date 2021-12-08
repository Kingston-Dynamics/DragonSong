package com.kingstondynamics.dragonsong.protocol.message.type.heartbeat;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;
import com.kingstondynamics.dragonsong.protocol.utils.Readinator;

public class HeartbeatPing extends Message {
    
    private final static MessageType type = MessageType.HEARTBEAT_PING;
    
    public HeartbeatPing(String auditId) {
        super(type.getType(), auditId);
    }
    
    public HeartbeatPing() {
        this(Keyinator.generateGUID());
    }
    
    public HeartbeatPing(Readinator reader) {
        super(reader);
    }
}
