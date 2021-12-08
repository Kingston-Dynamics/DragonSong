package com.kingstondynamics.dragonsong.protocol.message;

import com.kingstondynamics.dragonsong.protocol.utils.Byteinator;
import com.kingstondynamics.dragonsong.protocol.utils.Concatinator;
import com.kingstondynamics.dragonsong.protocol.utils.Readinator;

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
