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

package com.kdyncs.dragonsong.protocol.message.type.channel;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import com.kdyncs.dragonsong.protocol.utils.Concatinator;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

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
