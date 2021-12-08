package com.kdyncs.dragonsong.server.subsystem.messenger.service;

import com.kdyncs.dragonsong.protocol.message.type.heartbeat.HeartbeatPing;
import com.kdyncs.dragonsong.protocol.message.type.heartbeat.HeartbeatPong;
import com.kdyncs.dragonsong.protocol.utils.Readinator;
import com.kdyncs.dragonsong.server.core.pools.ConnectionPool;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.connection.ClientConnection;
import com.kdyncs.dragonsong.server.subsystem.messenger.protocol.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        Readinator reader = new Readinator(command.getCommand());
        HeartbeatPing input = new HeartbeatPing(reader);

        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());

        // Update Ping Time
        connection.setLastHeartBeat(Instant.now());

        // Make a new Pong message
        HeartbeatPong output = new HeartbeatPong(input.getAuditId());

        // Write Pong
        connection.getWriter().write(output.build());
    }
}
