package com.kingstondynamics.dragonsong.api.web.controller;

import com.kingstondynamics.dragonsong.api.util.response.ResponseFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@RestController
public class HealthController {
    
    // Spring Components
    private final ResponseFactory response;
    
    // Logging
    private Logger log = LogManager.getLogger();
    
    @Autowired
    public HealthController(ResponseFactory response) {
        this.response = response;
    }
    
    @PostConstruct
    public void init() {
        log.info("Loading {}", this.getClass().getSimpleName());
    }
    
    public ResponseEntity<?> health() {
        log.trace("Checking Health.");
        return response.buildResponse(HttpStatus.OK, "Application Running");
    }
}
