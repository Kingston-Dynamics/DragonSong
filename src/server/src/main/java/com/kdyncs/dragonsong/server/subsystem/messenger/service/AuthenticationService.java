package com.kdyncs.dragonsong.server.subsystem.messenger.service;

import com.kdyncs.dragonsong.protocol.message.data.NotificationType;
import com.kdyncs.dragonsong.protocol.message.type.authentication.AuthenticationLogin;
import com.kdyncs.dragonsong.protocol.message.type.notification.Notification;
import com.kdyncs.dragonsong.protocol.networking.NetworkReader;
import com.kdyncs.dragonsong.protocol.networking.NetworkWriter;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.application.Application;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.connection.ClientConnection;
import com.kdyncs.dragonsong.server.subsystem.messenger.protocol.Command;
import com.kdyncs.dragonsong.server.core.pools.ConnectionPool;
import com.kdyncs.dragonsong.server.subsystem.deployment.ApplicationPool;
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
    private final ApplicationPool applications;
    private final ApplicationContext context;
    
    @Autowired
    public AuthenticationService(ConnectionPool connections, ApplicationPool applications, ApplicationContext context) {
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
        
        // Start Timeout
        // ClientConnectionTimer timer = context.getBean(ClientConnectionTimer.class);
        // timer.setUser(connection);
        // timer.start();
        
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
        if (connection.getApplicationKey() != null) {
            // TODO: Remove User Channels
            
            // Remove From User Pool
            applications.get(connection.getApplicationKey()).getUsers().remove(connection.getExternalID());
        }
        
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
        
        // User Can't Login Twice
        if (connection.getApplicationKey() != null) {
            log.info("Already Authenticated");
            Notification notification = new Notification(NotificationType.ALREADY_AUTHENTICATED);
            connection.getWriter().write(notification.build());
            return;
        }
        
        // Extract Message from Command
        AuthenticationLogin message = new AuthenticationLogin(command.getCommand());
        
        // Application must be deployed
        if (applications.isDeployed(message.getApplicationKey())) {
            log.info("Wrong Application");
            Notification notification = new Notification(NotificationType.APPLICATION_NOT_DEPLOYED);
            connection.getWriter().write(notification.build());
            return;
        }
        
        // Get Application
        Application application = applications.get(message.getApplicationKey());
        
        // Verify Application
        if (!application.getApplicationKey().equals(message.getApplicationKey())) {
            // TODO: What to do here
            log.warn("Application Mismatch {}", application.getApplicationKey());
        }
        
        // Update User Information
        connection.setDisplayName(message.getDisplayName());
        connection.setApplicationKey(application.getApplicationKey());
        connection.setExternalID(message.getUniqueIdentifier());
        
        log.info("Private ID: {}", message.getUniqueIdentifier());
        
        // Add User to Application
        application.getUsers().add(connection.getExternalID(), connection.getConnectionID());
        
        // Notify User
        Notification notification = new Notification(NotificationType.AUTHENTICATION_SUCCESSFUL);
        connection.getWriter().write(notification.build());
        
        log.info("Login Successful");
        
    }
    
    public void logout(Command command) {
    
    }
    
    public void reconnect(Command command) {
    
    }
    
    // TODO This is the code from the Application for connecting.
    // Transform it so it fits here
//  public boolean connect(ClientConnection user, Authentication auth) {
//
//      String APIKey = auth.getAPIKey().getFieldData();
//      String AppID = auth.getAppID().getFieldData();
//      String AppVer = auth.getAppVer().getFieldData();
//
//      if (this.APIKey.equals(APIKey) && this.AppID.equals(AppID) && this.AppVer.equals(AppVer)) {
//
//          //Get Provided Private ID
//          String privateID = auth.getUniqueIdentifier().getFieldData();
//          String connectionID = user.getConnectionID();
//
//          //Check if User Already Exists
//          if (users.findUser(privateID) != null) {
//              LOG.debug("User with this Private ID already exists");
//              
//              //If User is Reconnecting We gotta do something
//              if (!connectionPool.getConnection(users.findUser(privateID)).isConnected()) {
//                  reconnect(user, auth, privateID, connectionID);
//                  LOG.info("User Reconnected");
//                  return true;
//              }
//              
//              //If they're already connected they can fuck off. 
//              LOG.error("User Already Connected");
//              return false;
//          }
//          //Add User to Application User Pool
//          users.registerUser(privateID, connectionID);
//
//          //Update client references
//          user.setPrivateID(privateID);
//          user.setApplication(this);
//
//          //Successfully Authenticated
//          return true;
//      } else {
//          LOG.error("AUTH DETAILS INVALID");
//          return false;
//      }
//  }

//  private void reconnect(ClientConnection user, Authentication auth, String privateID, String connectionID) {
//      
//      //Get the Old Connection
//      ClientConnection oldConnection = connectionPool.getConnection(users.findUser(privateID));
//      
//      //Stop it from deleting itself
//      oldConnection.stopReconnectTimer();
//      
//      //Replace the user with the new connection
//      users.replaceUser(privateID, connectionID);
//      
//      // Get Old Connection Data
////      List<Transmission> oldTransmissions = oldConnection.getTransmissionCache();
//      
//      // Destroy the Old User
//      oldConnection.destroy();
//      
//      // Send Data to User if Necessary
//      //for (Transmission transmission : oldTransmissions) {
//      //    user.post(transmission);
//      //}
//  }

}
