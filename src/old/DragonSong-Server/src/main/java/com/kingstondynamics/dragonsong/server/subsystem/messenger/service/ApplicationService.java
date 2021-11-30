package com.kingstondynamics.dragonsong.server.subsystem.messenger.service;

import com.kingstondynamics.dragonsong.server.core.pools.ConnectionPool;
import com.kingstondynamics.dragonsong.server.subsystem.deployment.ApplicationPool;
import com.kingstondynamics.dragonsong.server.subsystem.messenger.model.application.Application;
import com.kingstondynamics.dragonsong.server.subsystem.messenger.model.connection.ClientConnection;
import com.kingstondynamics.dragonsong.server.subsystem.messenger.protocol.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class ApplicationService {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final ConnectionPool connections;
    private final ApplicationPool applications;
    
    // Start Time
    private final Instant start = Instant.now();
    
    @Autowired
    public ApplicationService(ConnectionPool connections, ApplicationPool applications) {
        this.connections = connections;
        this.applications = applications;
    }
    
    // Get All Channels
    public void getChannels(Command command) {
        
        log.trace("Getting All Channels");
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // Validate protocol state
        if (connection.getApplicationKey() == null) {
            //TODO: Complain about unauthenticated
            return;
        }
        
        // Lookup Application Name
        Application application = applications.get(connection.getApplicationKey());
        
        // Get a list of all channels
        //noinspection ResultOfMethodCallIgnored
        application.getChannels();
        
        // Return It
        
    }
    
    // Get Application Name
    public void getName(Command command) {
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // Validate protocol state
        if (connection.getApplicationKey() == null) {
            //TODO: Complain about unauthenticated
            return;
        }
        
        // Lookup Application Name
        Application application = applications.get(connection.getApplicationKey());
        //noinspection ResultOfMethodCallIgnored
        application.getApplicationKey();
        
        // Return It
        
        
    }
    
    // Transmit to Every Logged In User
    public void transmit(Command command) {
    
    }
    
    // Get Application Uptime
    public void getUptime(Command command) {
        
        // Get Time between the start and now
        Duration.between(start, Instant.now());
        
    }
}
