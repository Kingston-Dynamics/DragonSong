package com.kingstondynamics.dragonsong.api.util.response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;

@Component
@Scope("prototype")
public class ResponseBuilder {
    
    // Spring Components
    private final HttpServletRequest http;
    
    // Builder Bits
    private HttpHeaders headers;
    
    // Logging
    private Logger log = LogManager.getLogger();
    
    @Autowired
    public ResponseBuilder(HttpServletRequest http) {
        this.http = http;
    }
    
}
