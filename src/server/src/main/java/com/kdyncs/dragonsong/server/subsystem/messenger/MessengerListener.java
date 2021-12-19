/*
 * Copyright (C) 2021 Kingston Dynamics Inc.
 *
 * This file is part of DragonSong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kdyncs.dragonsong.server.subsystem.messenger;

import com.kdyncs.dragonsong.server.subsystem.messenger.service.AuthenticationService;
import com.kdyncs.dragonsong.server.subsystem.ConnectionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
