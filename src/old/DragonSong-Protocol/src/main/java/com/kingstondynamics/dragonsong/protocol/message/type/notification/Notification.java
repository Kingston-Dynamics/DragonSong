package com.kingstondynamics.dragonsong.protocol.message.type.notification;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.message.data.NotificationType;
import com.kingstondynamics.dragonsong.protocol.utils.Byteinator;
import com.kingstondynamics.dragonsong.protocol.utils.Concatinator;
import com.kingstondynamics.dragonsong.protocol.utils.Keyinator;
import com.kingstondynamics.dragonsong.protocol.utils.Readinator;

public class Notification extends Message {
    
    private final static MessageType TYPE = MessageType.NOTIFICATION;
    
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
        
        return Concatinator.ConcatinateByteArrays(super.build(), codeData, messageData);
    }
}
