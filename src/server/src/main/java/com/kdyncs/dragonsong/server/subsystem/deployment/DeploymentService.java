package com.kdyncs.dragonsong.server.subsystem.deployment;

import com.kdyncs.dragonsong.server.subsystem.messenger.model.application.Application;
import com.kdyncs.dragonsong.server.subsystem.messenger.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeploymentService {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final ApplicationPool applications;
    private final AuthenticationService authentication;
    
    @Autowired
    public DeploymentService(ApplicationPool applications, AuthenticationService authentication) {
        this.applications = applications;
        this.authentication = authentication;
    }
    
    public void deploy() {
    
    }
    
    public void undeploy(String key) {
        
        log.info("Undeploying application {}", key);
        
        Application application = applications.get(key);

        // Sanity Checking
        if (application == null) {
            throw new DeploymentException("Application ");
        }

        // Shutdown Application
        if (application != null) {
            
            Map<String, String> users = application.getUsers().getAll();
            
            for (String user : users.values()) {
                authentication.disconnect(user);
            }
        }
        
        log.info("undeployed application {}", key);
    }
}
