package com.kdyncs.dragonsong.protocol.message.type;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import com.kdyncs.dragonsong.protocol.utils.Concatinator;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

public class ChannelConnect extends Message {

    private static final MessageType type = MessageType.CHANNEL_CONNECT;
    
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
        return Concatinator.concatinateByteArrays(super.build(), channelData);
    }
    
    public String getChannelId() {
        return channelId;
    }
}
