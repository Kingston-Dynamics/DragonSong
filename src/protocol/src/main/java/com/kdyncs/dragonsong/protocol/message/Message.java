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

package com.kdyncs.dragonsong.protocol.message;

import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import com.kdyncs.dragonsong.protocol.utils.Concatinator;
import com.kdyncs.dragonsong.protocol.utils.Readinator;

public abstract class Message {
    
    private final int opcode;
    private final String auditId;
    
    public Message(int opcode, String auditId) {
        this.opcode = opcode;
        this.auditId = auditId;
    }
    
    public Message(Readinator reader) {
        this.opcode = reader.readInt();
        this.auditId = reader.readAuditId();
    }
    
    public byte[] build() {
        
        byte[] opcodeData = Byteinator.intToBytes(opcode);
        byte[] auditIdData = Byteinator.stringToBytes(auditId);
        
        return Concatinator.ConcatinateByteArrays(opcodeData, auditIdData);
    }
    
    public int getOpcode() {
        return opcode;
    }
    
    public String getAuditId() {
        return auditId;
    }
}
