/*
 * Copyright (C) 2021 Kingston Dynamics Inc.
 *
 * This file is part of DragonSong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
