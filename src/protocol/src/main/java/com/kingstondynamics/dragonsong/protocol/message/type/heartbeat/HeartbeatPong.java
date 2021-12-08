package com.kingstondynamics.dragonsong.protocol.message.type.heartbeat;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;
import com.kingstondynamics.dragonsong.protocol.utils.Readinator;

public class HeartbeatPong extends Message {
    
    private final static MessageType type = MessageType.HEARTBEAT_PONG;
    
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
