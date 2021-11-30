package com.kingstondynamics.dragonsong.api.service.clamav.constant;

public enum AVResponse {
    PONG("PONG"),
    CLEAN("stream: OK ");
    
    String value;
    
    AVResponse(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
