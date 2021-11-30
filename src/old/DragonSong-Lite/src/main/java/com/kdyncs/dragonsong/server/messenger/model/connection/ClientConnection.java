package com.kdyncs.dragonsong.server.messenger.model.connection;

import com.kdyncs.dragonsong.protocol.networking.NetworkError;
import com.kdyncs.dragonsong.protocol.networking.NetworkManager;
import com.kdyncs.dragonsong.protocol.networking.NetworkReader;
import com.kdyncs.dragonsong.protocol.networking.NetworkWriter;
import com.kdyncs.dragonsong.server.messenger.protocol.Command;
import com.kdyncs.dragonsong.server.messenger.protocol.Processor;
import com.kdyncs.dragonsong.server.messenger.service.ConnectionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.time.Instant;

/**
 * @author peter
 */

@Component
@Scope("prototype")
public class ClientConnection implements NetworkManager {

    // Logging
    private final Logger log = LogManager.getLogger();

    // Spring Components
    private final Processor processor;
    private final ConnectionService connection;
    
    // Store instance of Socket
    private Socket socket;
    private final Instant connectionTime;
    
    // Input and Output
    private NetworkWriter writer;
    private NetworkReader reader;

    // PlayFab Integration
    private PlayerData data;
    
    // Connection Pool Reference
    private String connectionID;
    private boolean active;
    private Instant lastHeartBeat;

    @Autowired
    public ClientConnection(Processor processor, ConnectionService connection) {
        this.processor = processor;
        this.connection = connection;

        // Client is Inactive by Default
        this.active = false;

        // Set Heartbeat Time to a default value
        lastHeartBeat = Instant.now();

        // Set Connection Time
        connectionTime = Instant.now();
    }
    
    @Override
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PlayerData getData() {
        return data;
    }

    public void setData(PlayerData data) {
        this.data = data;
    }

    public String getConnectionID() {
        return connectionID;
    }
    
    public void setConnectionID(String connectionID) {
        this.connectionID = connectionID;
    }

    public NetworkWriter getWriter() {
        return writer;
    }
    
    public void setWriter(NetworkWriter writer) {
        this.writer = writer;
    }
    
    public NetworkReader getReader() {
        return reader;
    }
    
    public void setReader(NetworkReader reader) {
        this.reader = reader;
    }

    public void write(byte[] data) {
        if (active) {
            writer.write(data);
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLastHeartBeat(Instant time) {
        lastHeartBeat = time;
    }

    public Instant getLastHeartBeat() {
        return lastHeartBeat;
    }

    public Instant getConnectionTime() {
        return connectionTime;
    }

    @Override
    public void handleError(NetworkError error) {

        switch (error) {
            case END_OF_FILE:
                log.info("Client disconnected, terminating connection");
                connection.remove(this);
                return;
            case SOCKET_CLOSED:
                log.info("Socket Closed");
                return;
            case UNKNOWN:
                log.error("Unknown network error encountered, terminating connection");
                connection.remove(this);
                return;
        }

        log.error("Unhandled network error, terminating connection.");
        connection.remove(this);
    }

    @Override
    public void handleInput(byte[] data) {
        processor.queueCommand(new Command(connectionID, data));
    }

    @Override
    public String getName() {
        return getConnectionID();
    }
}
