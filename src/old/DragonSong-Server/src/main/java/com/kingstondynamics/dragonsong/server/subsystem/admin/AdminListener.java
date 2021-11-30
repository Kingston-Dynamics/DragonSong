package com.kingstondynamics.dragonsong.server.subsystem.admin;

import com.kingstondynamics.dragonsong.server.subsystem.ConnectionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author peter
 */

@Service
public class AdminListener implements ConnectionListener {
    
    private static final int PORT = 27889;
    private final Logger log = LogManager.getLogger();
    
    private ServerSocket socket;
    //private boolean listening;
    private int port;
    
    //
    private Thread listener;
    
    @PostConstruct
    public void init() {
        
        // Create a Usable Thread
        this.listener = new Thread(this);

        // Name Thread
        this.listener.setName("ADMIN_LISTENER");

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
            while (!Thread.interrupted()) {
                
                //Wait for Connection
                Socket client = socket.accept();
                log.info("Admin Connecting On: " + socket.getLocalPort());
                log.info("Admin Routed to: " + client.getPort());
                
                // Close Connection, We don't support this just yet.
                client.close();
            }
            
            log.info("Closing Listening Socket");
            socket.close();
            
        } catch (IOException e) {
            log.error("Exception caught when trying to listen on port " + port + " or listening for a connection");
            log.error(e.getMessage());
        }
    }
    
    @Override
    public void start() {
        
        if (listener.isAlive()) {
            log.warn("Admin Listener Already Started");
            return;
        }
        
        listener.start();
    }
    
    @Override
    public void stop() {
        
        log.info("Attempting to close Admin Listener on Port {}", port);
        
        if (!listener.isAlive()) {
            log.warn("Admin Listener is Already Stopped");
            return;
        }
        
        try {
            listener.interrupt();
            socket.close();
            log.info("Closed Admin Listener");
        } catch (IOException ex) {
            log.error("Error Closing Admin Listener", ex);
        }
    }
}
