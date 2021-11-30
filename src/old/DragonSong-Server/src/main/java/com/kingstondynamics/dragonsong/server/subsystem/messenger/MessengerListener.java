package com.kingstondynamics.dragonsong.server.subsystem.messenger;

import com.kingstondynamics.dragonsong.server.subsystem.ConnectionListener;
import com.kingstondynamics.dragonsong.server.subsystem.messenger.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author peter
 */

@Service
public class MessengerListener implements ConnectionListener {
    
    private static final int PORT = 27888;
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final AuthenticationService auth;
    
    // Socket Stuffs
    private ServerSocket socket;
    private int port;
    
    // Listening Thread
    private Thread listener;
    private boolean running;
    
    @Autowired
    public MessengerListener(AuthenticationService auth) {
        this.auth = auth;
    }
    
    @PostConstruct
    public void init() {
        
        // Create a Thread
        this.listener = new Thread(this);
        
        // Name Thread
        this.listener.setName("MESSENGER_LISTENER");
        
        // Make Sure everything is Already Configured
        this.port = PORT;
    }
    
    @Override
    public void run() {
        
        log.info("Attempting to Open Listen Port: " + port);
        
        try {
            
            // Open Listening Socket
            socket = new ServerSocket(port);
            
            // Wait and See
            while (running) {
                
                // Wait for Connection
                Socket client = socket.accept();
                
                // Logging
                log.info("Client Connecting On: {}", socket.getLocalPort());
                log.info("Client Routed to: {}", client.getPort());
                
                // Create a new Connection Object
                auth.connect(client);
            }
            
            log.info("Closing Listening Socket");
            socket.close();
            
        } catch (IOException e) {
            log.error("Exception caught when trying to listen on port {} or listening for a connection", port);
            log.error(e.getMessage());
        }
    }
    
    @Override
    public void start() {
        
        log.info("Attempting to open Messenger Listener on Port {}", port);
        
        if (listener.isAlive()) {
            log.warn("Messenger Listener Already Started");
            return;
        }
        
        running = true;
        listener.start();
    }
    
    @Override
    public void stop() {
        
        log.info("Attempting to close Messenger Listener on Port {}", port);
        
        if (!listener.isAlive()) {
            log.warn("Messenger Listener is Already Stopped");
            return;
        }
        
        try {
            log.info("Closed Messenger Listener");
            running = false;
            socket.close();
            
        } catch (IOException ex) {
            log.error("Error Closing Messenger Listener", ex);
        }
    }
}
