package com.kdyncs.dragonsong.server.messenger.service;

import com.kdyncs.dragonsong.server.ConnectionPool;
import com.kdyncs.dragonsong.server.messenger.protocol.Command;
import com.kdyncs.dragonsong.server.messenger.model.application.Application;
import com.kdyncs.dragonsong.server.messenger.model.connection.ClientConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final ConnectionPool connections;
    private final Application application;
    private final AuthenticationService authentication;

    @Autowired
    public ApplicationService(ConnectionPool connections, Application application, AuthenticationService authentication) {
        this.connections = connections;
        this.application = application;
        this.authentication = authentication;
    }
    
    // Get All Channels
    public void getChannels(Command command) {
        
        log.trace("Getting All Channels");
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // Validate protocol state
        if (!authentication.isAuthenticated(connection)) {
            //TODO: Complain about unauthenticated
            return;
        }

        // TODO: Return channels to User
        application.getChannels();
    }

    // Transmit to Every Logged In User
    public void transmit(Command command) {
        throw new UnsupportedOperationException("Unimplemented");
    }
}
