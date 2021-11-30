package com.kdyncs.dragonsong.protocol.message.type;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import com.kdyncs.dragonsong.protocol.utils.Concatinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

public class AuthenticationLogin extends Message {

    private static final MessageType type = MessageType.AUTHENTICATION_LOGIN;

    private final String playerId;
    private final String characterId;
    
    public AuthenticationLogin(String playerId, String characterId, String auditId) {
        super(type.getType(), auditId);
        this.playerId = playerId;
        this.characterId = characterId;
    }

    public AuthenticationLogin(Readinator reader) {
        super(reader);
        this.playerId = reader.readIntPrefixedString();
        this.characterId = reader.readIntPrefixedString();
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
        byte[] playerIdData = Byteinator.stringToBytesPrefixed(playerId);
        byte[] characterIdData = Byteinator.stringToBytesPrefixed(characterId);
        
        // Construct
        return Concatinator.concatinateByteArrays(super.build(), playerIdData, characterIdData);
    }

    public String getPlayerId() {
        return playerId;
    }
    
    public String getCharacterId() {
        return characterId;
    }
}
