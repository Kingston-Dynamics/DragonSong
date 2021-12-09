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
