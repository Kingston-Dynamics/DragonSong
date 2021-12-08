package com.kingstondynamics.dragonsong.protocol.message.type.channel;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Byteinator;
import com.kingstondynamics.dragonsong.protocol.utils.Concatinator;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;
import com.kingstondynamics.dragonsong.protocol.utils.Readinator;

public class ChannelConnect extends Message {
    
    private final static MessageType type = MessageType.CHANNEL_CONNECT;
    
    private final String channelId;
    
    public ChannelConnect(String id, String auditId) {
        super(type.getType(), auditId);
        
        this.channelId = id;
    }
    
    public ChannelConnect(String id) {
        this(id, Keyinator.generateGUID());
    }
    
    public ChannelConnect(Readinator reader) {
        super(reader);
        
        this.channelId = reader.readIntPrefixedString();
    }
    
    public ChannelConnect(byte[] data) {
        this(new Readinator(data));
    }
    
    public static MessageType getType() {
        return type;
    }
    
    @Override
    public byte[] build() {
        // Build Channel Connect
        byte[] channelData = Byteinator.stringToBytesPrefixed(channelId);
        return Concatinator.ConcatinateByteArrays(super.build(), channelData);
    }
    
    public String getChannelId() {
        return channelId;
    }
}
