package com.kdyncs.dragonsong.server.subsystem.messenger.service;

import com.kdyncs.dragonsong.protocol.message.type.user.UserTransmit;
import com.kdyncs.dragonsong.protocol.utils.Readinator;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.application.Application;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.application.UserPool;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.connection.ClientConnection;
import com.kdyncs.dragonsong.server.core.pools.ConnectionPool;
import com.kdyncs.dragonsong.server.subsystem.deployment.ApplicationPool;
import com.kdyncs.dragonsong.server.subsystem.messenger.protocol.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final ConnectionPool connections;
    private final ApplicationPool applications;
    
    @Autowired
    public UserService(ConnectionPool connections, ApplicationPool applications) {
        this.connections = connections;
        this.applications = applications;
    }
    
    public void block(Command command) {
    
    }
    
    public void transmit(Command command) {
        
        log.info("Sending direct message");
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // Rebuild Message
        Readinator reader = new Readinator(command.getCommand());
        UserTransmit message = new UserTransmit(reader);
        
        // TODO: Validate Protocol State
        
        // Lookup Application
        Application application = applications.get(connection.getApplicationKey());
        UserPool users = application.getUsers();
        
        // Get Recipient
        String connectionID = users.find(message.getIdentifier());
        ClientConnection recipient = connections.get(connectionID);
        
        // Bounce message back to recipient
        recipient.getWriter().write(command.getCommand());
        
        log.info("Direct message sent");
        
    }
    
    public void unblock(Command command) {
    
    }
    
    public void getUptime(Command command) {
    
    }
    
    public void whatis(Command command) {
    
    }
    
    public void whois(Command command) {
    
    }
}
