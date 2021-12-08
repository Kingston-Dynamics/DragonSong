package com.kdyncs.dragonsong.api.service.clamav.constant;

import com.kdyncs.dragonsong.api.util.misc.Byteinator;

import java.nio.charset.StandardCharsets;

public enum AVCommand {
    
    PING("zPING\0"),
    SCAN_START("zINSTREAM\0"),
    SCAN_TERMINATE(new String(new byte[]{0, 0, 0, 0})); //Why?
    
    byte[] value;
    
    AVCommand(String data) {
        value = Byteinator.stringToBytes(data, StandardCharsets.US_ASCII);
    }
    
    public byte[] getValue() {
        return this.value;
    }
}
