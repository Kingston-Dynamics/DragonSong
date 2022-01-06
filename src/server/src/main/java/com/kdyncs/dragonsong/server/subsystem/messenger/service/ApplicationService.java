package com.kdyncs.dragonsong.server.subsystem.messenger.service;

import com.kdyncs.dragonsong.server.subsystem.messenger.model.application.Partition;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.connection.ClientConnection;
import com.kdyncs.dragonsong.server.subsystem.messenger.protocol.Command;
import com.kdyncs.dragonsong.server.core.pools.ConnectionPool;
import com.kdyncs.dragonsong.server.subsystem.deployment.PartitionPool;
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
    private final PartitionPool applications;
    
    // Start Time
    private final Instant start = Instant.now();
    
    @Autowired
    public ApplicationService(ConnectionPool connections, PartitionPool applications) {
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
        Partition partition = applications.get(connection.getApplicationKey());
        
        // Get a list of all channels
        //noinspection ResultOfMethodCallIgnored
        partition.getChannels();
        
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
        Partition partition = applications.get(connection.getApplicationKey());
        //noinspection ResultOfMethodCallIgnored
        partition.getApiKey();
        
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
