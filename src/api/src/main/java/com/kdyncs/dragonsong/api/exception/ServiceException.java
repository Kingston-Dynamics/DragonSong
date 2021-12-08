package com.kdyncs.dragonsong.api.exception;

import org.springframework.http.ResponseEntity;

public class ServiceException extends RuntimeException {
    
    private static final long serialVersionUID = 8468468516846100994L;
    
    private final ResponseEntity<?> error;
    
    public ServiceException(ResponseEntity<?> error) {
        this.error = error;
    }
    
    public ResponseEntity<?> getError() {
        return this.error;
    }
}
