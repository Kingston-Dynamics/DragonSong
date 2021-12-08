package com.kingstondynamics.dragonsong.protocol.message.type.channel;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Byteinator;
import com.kingstondynamics.dragonsong.protocol.utils.Concatinator;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;
import com.kingstondynamics.dragonsong.protocol.utils.Readinator;

public class ChannelTransmit extends Message {
    
    private final static MessageType type = MessageType.CHANNEL_TRANSMIT;
    
    private final String channelId;
    private final String transmission;
    
    public ChannelTransmit(String id, String message, String auditId) {
        super(type.getType(), auditId);
        
        this.channelId = id;
        this.transmission = message;
    }
    
    public ChannelTransmit(String id, String message) {
        this(id, message, Keyinator.generateGUID());
    }
    
    public ChannelTransmit(Readinator reader) {
        super(reader);
        
        this.channelId = reader.readIntPrefixedString();
        this.transmission = reader.readIntPrefixedString();
    }
    
    public ChannelTransmit(byte[] data) {
        this(new Readinator(data));
    }
    
    public static MessageType getType() {
        return type;
    }
    
    @Override
    public byte[] build() {
        byte[] channelData = Byteinator.stringToBytesPrefixed(channelId);
        byte[] messageData = Byteinator.stringToBytesPrefixed(transmission);
        return Concatinator.ConcatinateByteArrays(super.build(), channelData, messageData);
    }
    
    public String getChannelId() {
        return channelId;
    }
    
    public String getTransmission() {
        return transmission;
    }
}
