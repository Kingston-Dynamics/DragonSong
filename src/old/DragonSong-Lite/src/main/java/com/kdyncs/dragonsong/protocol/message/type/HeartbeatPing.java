package com.kdyncs.dragonsong.protocol.message.type;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

public class HeartbeatPing extends Message {

    private static final MessageType type = MessageType.HEARTBEAT_PING;
    
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
