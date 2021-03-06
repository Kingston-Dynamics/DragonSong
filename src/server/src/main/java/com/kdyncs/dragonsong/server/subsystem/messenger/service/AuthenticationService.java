package com.kdyncs.dragonsong.server.subsystem.messenger.service;

import com.kdyncs.dragonsong.protocol.message.data.NotificationType;
import com.kdyncs.dragonsong.protocol.message.type.authentication.AuthenticationLogin;
import com.kdyncs.dragonsong.protocol.message.type.notification.Notification;
import com.kdyncs.dragonsong.protocol.networking.NetworkReader;
import com.kdyncs.dragonsong.protocol.networking.NetworkWriter;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.application.Partition;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.connection.ClientConnection;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.connection.ClientConnectionTimer;
import com.kdyncs.dragonsong.server.subsystem.messenger.protocol.Command;
import com.kdyncs.dragonsong.server.core.pools.ConnectionPool;
import com.kdyncs.dragonsong.server.subsystem.deployment.PartitionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;

@Service
public class AuthenticationService {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final ConnectionPool connections;
    private final PartitionPool applications;
    private final ApplicationContext context;
    
    @Autowired
    public AuthenticationService(ConnectionPool connections, PartitionPool applications, ApplicationContext context) {
        this.connections = connections;
        this.applications = applications;
        this.context = context;
    }
    
    /**
     * Entry Point for New Clients
     *
     * @param socket Socket of Connecting User
     */
    public void connect(Socket socket) {
        
        // Create Connection Instance
        ClientConnection connection = context.getBean(ClientConnection.class);
        
        // Add Socket to Connection
        connection.setSocket(socket);
        
        // Generate a new Connection ID
        String connectionID = Keyinator.generateGUID();
        connection.setConnectionID(connectionID);
        
        // Register Connection
        connections.add(connectionID, connection);
        log.info("Adding Connection: " + connections.size());
        
        // Open Reader and Writer Threads
        NetworkReader reader = new NetworkReader(connection);
        NetworkWriter writer = new NetworkWriter(connection);
        
        // Attach Reader and Writer threads
        connection.setReader(reader);
        connection.setWriter(writer);
        
        // Start Networking
        reader.start();
        writer.start();

        /*
         * Create a Client Connection Timer
         *
         * Upon connection, we afford the user a grace period to log in to a particular application. If this user
         * does not log in within the specified time frame they will be disconnected from the server.
         */
        context.getBean(ClientConnectionTimer.class).setUser(connection);
    }
    
    public void disconnect(ClientConnection connection) {
        
        log.trace("Disconnecting User {}", connection.getExternalID());
        
        // Close Socket
        try {
            connection.getReader().stop();
            connection.getWriter().stop();
            connection.getSocket().close();
        } catch (IOException e) {
            log.info("Issue Closing Socket {}", e.getLocalizedMessage());
        }
        
        // Handle unauthenticated users
        if (isAuthenticated(connection)) {
            // TODO: Remove User Channels
            
            // Remove From User Pool
            applications.get(connection.getApplicationKey()).getUsers().remove(connection.getExternalID());
        }

        // Shutdown Heart Beat Monitor
        connection.getHeartBeatMonitor().stop();
        
        // Remove Connection from Connections List
        connections.remove(connection.getConnectionID());
    }
    
    public void disconnect(String guid) {
        disconnect(connections.get(guid));
    }
    
    public void disconnect(Command command) {
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // Disconnect Client
        disconnect(connection);
    }
    
    public void login(Command command) {
        
        log.info("Attempting Login");
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // User Can't Log in Twice
        if (isAuthenticated(connection)) {
            log.info("Already Authenticated");
            Notification notification = new Notification(NotificationType.ALREADY_AUTHENTICATED);
            connection.getWriter().write(notification.build());
            return;
        }
        
        // Extract Message from Command
        AuthenticationLogin message = new AuthenticationLogin(command.getCommand());
        
        // Application must be deployed
        if (!applications.isDeployed(message.getApplicationKey())) {
            log.info("Application with Key {} not deployed", message.getApplicationKey());
            Notification notification = new Notification(NotificationType.APPLICATION_NOT_DEPLOYED);
            connection.getWriter().write(notification.build());
            return;
        }
        
        // Get Application
        Partition partition = applications.get(message.getApplicationKey());
        
        // Verify Application
        if (!partition.getApiKey().equals(message.getApplicationKey())) {
            /*
             * Note: We used to do some more advanced application verification but that no longer occurs
             * as we removed some old application ID and application version code.
             */
            log.warn("Application Mismatch {}", partition.getApiKey());
        }
        
        // Update User Information
        connection.setDisplayName(message.getDisplayName());
        connection.setApplicationKey(partition.getApiKey());
        connection.setExternalID(message.getUniqueIdentifier());
        
        log.info("Private ID: {}", message.getUniqueIdentifier());
        
        // Add User to Application
        partition.getUsers().add(connection.getExternalID(), connection.getConnectionID());
        
        // Notify User
        Notification notification = new Notification(NotificationType.AUTHENTICATION_SUCCESSFUL);
        connection.getWriter().write(notification.build());
        
        log.info("Login Successful");
        
    }
    
    public void logout(Command command) {
    
    }
    
    public void reconnect(Command command) {
    
    }

    /**
     * Check if Client is Authenticated
     *
     * If Client has an assigned Application Key they are considered to be Authenticated.
     */
    public Boolean isAuthenticated(ClientConnection client) {
        return client.getApplicationKey() != null;
    }
}
