package com.kdyncs.dragonsong.server.messenger.service;

import com.kdyncs.dragonsong.protocol.message.type.HeartbeatPing;
import com.kdyncs.dragonsong.protocol.message.type.HeartbeatPong;
import com.kdyncs.dragonsong.protocol.utils.Readinator;
import com.kdyncs.dragonsong.server.ConnectionPool;
import com.kdyncs.dragonsong.server.messenger.protocol.Command;
import com.kdyncs.dragonsong.server.messenger.model.connection.ClientConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PingService {

    // Logging
    private static final Logger log = LogManager.getLogger();

    // Spring Components
    private final ConnectionPool connections;
    
    @Autowired
    public PingService(ConnectionPool connections) {
        this.connections = connections;
    }
    
    public void ping(Command command) {

        log.info("Received Ping");

        // Extract Contents
        Readinator reader = new Readinator(command.getData());
        HeartbeatPing input = new HeartbeatPing(reader);

        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());

        // Update Ping Time
        connection.setLastHeartBeat(Instant.now());

        // Make a new Pong message
        HeartbeatPong output = new HeartbeatPong(input.getAuditId());

        // Write Pong
        connection.write(output.build());
    }
}
