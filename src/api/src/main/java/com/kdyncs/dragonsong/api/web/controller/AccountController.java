package com.kdyncs.dragonsong.api.web.controller;

import com.kdyncs.dragonsong.api.util.response.ResponseFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/account")
public class AccountController {

    // Spring Components
    private final ResponseFactory response;

    // Logging
    private final Logger log = LogManager.getLogger();

    @Autowired
    public AccountController(ResponseFactory response) {
        this.response = response;
    }

    @PostConstruct
    public void init() {
        log.info("Loading {}", this.getClass().getSimpleName());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register() {
        log.trace("Registering Account.");



        return response.buildResponse(HttpStatus.OK, "Account Registered");
    }
}
