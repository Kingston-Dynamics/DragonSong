package com.kdyncs.dragonsong.protocol.message.type.user;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import com.kdyncs.dragonsong.protocol.utils.Concatinator;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

public class UserTransmit extends Message {
    
    private final static MessageType type = MessageType.USER_TRANSMIT;
    
    private final String identifier;
    private final String message;
    
    public UserTransmit(String id, String message, String auditID) {
        super(type.getType(), auditID);
        
        this.identifier = id;
        this.message = message;
    }
    
    public UserTransmit(String id, String message) {
        this(id, message, Keyinator.generateGUID());
    }
    
    public UserTransmit(Readinator reader) {
        super(reader);
        
        this.identifier = reader.readIntPrefixedString();
        this.message = reader.readIntPrefixedString();
    }
    
    public UserTransmit(byte[] data) {
        this(new Readinator(data));
    }
    
    public static MessageType getType() {
        return type;
    }
    
    @Override
    public byte[] build() {
        byte[] identifierData = Byteinator.stringToBytesPrefixed(identifier);
        byte[] messageData = Byteinator.stringToBytesPrefixed(message);
        return Concatinator.ConcatinateByteArrays(super.build(), identifierData, messageData);
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public String getMessage() {
        return message;
    }
}
