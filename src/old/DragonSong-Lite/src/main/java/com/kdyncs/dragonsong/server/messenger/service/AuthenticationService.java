package com.kdyncs.dragonsong.server.messenger.service;

import com.kdyncs.dragonsong.playfab.PlayFab;
import com.kdyncs.dragonsong.protocol.message.data.NotificationType;
import com.kdyncs.dragonsong.protocol.message.type.Notification;
import com.kdyncs.dragonsong.server.ConnectionPool;
import com.kdyncs.dragonsong.server.messenger.model.application.Application;
import com.kdyncs.dragonsong.server.messenger.model.connection.ClientConnection;
import com.kdyncs.dragonsong.server.messenger.model.connection.PlayerData;
import com.kdyncs.dragonsong.server.messenger.protocol.Command;
import com.kdyncs.dragonsong.playfab.PlayFabException;
import com.kdyncs.dragonsong.protocol.message.type.AuthenticationLogin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final ConnectionPool connections;
    private final Application application;
    private final PlayFab playfab;

    @Autowired
    public AuthenticationService(ConnectionPool connections, Application application, PlayFab playfab) {
        this.connections = connections;
        this.application = application;
        this.playfab = playfab;
    }

    public void login(Command command) {
        
        log.info("Attempting Login");
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // User Can't Login Twice
        if (isAuthenticated(connection)) {
            log.info("Already Authenticated");
            Notification notification = new Notification(NotificationType.ALREADY_AUTHENTICATED);
            connection.write(notification.build());
            return;
        }
        
        // Extract Message from Command
        AuthenticationLogin msg = new AuthenticationLogin(command.getData());

        // PlayFab Calls
        try  {
            var characters = playfab.listCharacters(msg.getPlayerId());
            var character = playfab.findCharacter(characters, msg.getCharacterId());

            if (character == null) {
                Notification notification = new Notification(NotificationType.AUTHENTICATION_FAILURE);
                connection.write(notification.build());
                return;
            }

            // Update User Information
            connection.setData(new PlayerData(msg.getPlayerId(), msg.getCharacterId(), character.CharacterName));

        } catch (PlayFabException ex) {
            log.warn("Issue calling Playfab; Denying authentication");
            Notification notification = new Notification(NotificationType.AUTHENTICATION_FAILURE);
            connection.write(notification.build());
            return;
        }

        // Log Info
        log.info("Private ID: {}", msg.getPlayerId());
        
        // Add User to Application
        application.getUsers().add(connection.getData().getPlayerId(), connection.getConnectionID());
        
        // Notify User
        Notification notification = new Notification(NotificationType.AUTHENTICATION_SUCCESSFUL);
        connection.write(notification.build());
        
        log.info("Login Successful");
    }
    
    public void logout(Command command) {
        logout(command.getIssuer());
    }

    public void logout(String connectionId) {
        logout(connections.get(connectionId));
    }

    public void logout(ClientConnection connection) {
        log.info("Attempting Logout");

        if (!isAuthenticated(connection)) {
            log.info("Not logged in");
            return;
        }

        // Remove From User Pool
        application.getUsers().remove(connection.getData().getPlayerId());

        log.info("Logout Successful");
    }
    
    public void reconnect(Command command) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    public boolean isAuthenticated(ClientConnection connection) {
        return connection.getData() != null;
    }
}