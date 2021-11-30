package com.kdyncs.dragonsong.server.messenger.service;

import com.kdyncs.dragonsong.protocol.message.type.UserTransmit;
import com.kdyncs.dragonsong.protocol.utils.Readinator;
import com.kdyncs.dragonsong.server.ConnectionPool;
import com.kdyncs.dragonsong.server.messenger.protocol.Command;
import com.kdyncs.dragonsong.server.messenger.model.application.Application;
import com.kdyncs.dragonsong.server.messenger.model.application.UserPool;
import com.kdyncs.dragonsong.server.messenger.model.connection.ClientConnection;
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
    private final Application application;
    
    @Autowired
    public UserService(ConnectionPool connections, Application application) {
        this.connections = connections;
        this.application = application;
    }
    
    public void block(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void transmit(Command command) {
        
        log.info("Sending direct message");

        // Rebuild Message
        Readinator reader = new Readinator(command.getData());
        UserTransmit message = new UserTransmit(reader);
        
        // TODO: Validate Protocol State
        UserPool users = application.getUsers();
        
        // Get Recipient
        String connectionID = users.find(message.getIdentifier());
        ClientConnection recipient = connections.get(connectionID);
        
        // Bounce message back to recipient
        recipient.write(command.getData());
        
        log.info("Direct message sent");
    }
    
    public void unblock(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void getUptime(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void whatis(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void whois(Command command) {
        throw new UnsupportedOperationException();
    }
}
