package com.kdyncs.dragonsong.protocol.message.type.authentication;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import com.kdyncs.dragonsong.protocol.utils.Concatinator;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

public class AuthenticationLogin extends Message {
    
    private final static MessageType type = MessageType.AUTHENTICATION_LOGIN;
    
    private final String ApplicationKey;
    private final String UniqueIdentifier;
    private final String displayName;
    
    public AuthenticationLogin(String ApplicationKey, String UniqueIdentifier, String displayName, String auditId) {
        super(type.getType(), auditId);
        
        this.ApplicationKey = ApplicationKey;
        this.UniqueIdentifier = UniqueIdentifier;
        this.displayName = displayName;
    }
    
    public AuthenticationLogin(String ApplicationKey, String UniqueIdentifier, String displayName) {
        this(ApplicationKey, UniqueIdentifier, displayName, Keyinator.generateGUID());
    }
    
    public AuthenticationLogin(Readinator reader) {
        super(reader);
        
        this.ApplicationKey = reader.readIntPrefixedString();
        this.UniqueIdentifier = reader.readIntPrefixedString();
        this.displayName = reader.readIntPrefixedString();
    }
    
    public AuthenticationLogin(byte[] data) {
        this(new Readinator(data));
    }
    
    public static MessageType getType() {
        return type;
    }
    
    @Override
    public byte[] build() {
        
        // Get All Bytes
        byte[] ApplicationKeyData = Byteinator.stringToBytesPrefixed(ApplicationKey);
        byte[] UniqueIdentifierData = Byteinator.stringToBytesPrefixed(UniqueIdentifier);
        byte[] displayNameData = Byteinator.stringToBytesPrefixed(displayName);
        
        // Construct
        return Concatinator.ConcatinateByteArrays(super.build(), ApplicationKeyData, UniqueIdentifierData, displayNameData);
    }
    
    public String getApplicationKey() {
        return ApplicationKey;
    }

    public String getUniqueIdentifier() {
        return UniqueIdentifier;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
