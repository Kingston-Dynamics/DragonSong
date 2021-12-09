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

package com.kdyncs.dragonsong.protocol.message.type.notification;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.message.data.NotificationType;
import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import com.kdyncs.dragonsong.protocol.utils.Concatinator;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

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
