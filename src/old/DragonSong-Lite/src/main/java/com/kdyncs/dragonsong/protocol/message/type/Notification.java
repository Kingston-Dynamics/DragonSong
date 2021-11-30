package com.kdyncs.dragonsong.protocol.message.type;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.message.data.NotificationType;
import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import com.kdyncs.dragonsong.protocol.utils.Concatinator;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

public class Notification extends Message {

    private static final MessageType TYPE = MessageType.NOTIFICATION;
    
    private final String code;
    private final String message;
    
    public Notification(NotificationType notification, String auditId) {
        super(TYPE.getType(), auditId);
        
        this.code = notification.getCode();
        this.message = notification.toString();
    }
    
    public Notification(NotificationType notification) {
        this(notification, Keyinator.generateGUID());
    }
    
    public Notification(Readinator reader) {
        super(reader);
        
        this.code = reader.readIntPrefixedString();
        this.message = reader.readIntPrefixedString();
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    @Override
    public byte[] build() {
        
        byte[] codeData = Byteinator.stringToBytesPrefixed(code);
        byte[] messageData = Byteinator.stringToBytesPrefixed(message);
        
        return Concatinator.concatinateByteArrays(super.build(), codeData, messageData);
    }
}
