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

package com.kdyncs.dragonsong.server.subsystem.command;

import com.kdyncs.dragonsong.server.subsystem.ConnectionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class CommandListener implements ConnectionListener {
    
    private static final int PORT = 27887;
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Connection Details
    private ServerSocket socket;
    private int port;
    
    // Listening Thread
    private Thread listener;
    
    @PostConstruct
    public void init() {
        this.listener = new Thread(this);

        // Name Thread
        this.listener.setName("COMMAND_LISTENER");

        this.port = PORT;
    }
    
    @Override
    public void run() {
        
        log.info("Attempting to Open Listen Port: " + port);
        
        try {
            
            // Open Listening Socket, LOCAL HOST ONLY
            socket = new ServerSocket(port, 0, InetAddress.getLoopbackAddress());
            
            // Wait and See
            while (!Thread.interrupted()) {
                
                // Wait for Connection
                Socket client = socket.accept();
                
                // Logging
                log.info("Client Connecting On: {}", socket.getLocalPort());
                log.info("Client Routed to: {}", client.getPort());
                
                // Close Connection, We don't support this just yet.
                client.close();
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
        
        if (listener.isAlive()) {
            log.warn("Command Listener Already Started");
            return;
        }
        
        listener.start();
    }
    
    @Override
    public void stop() {
        
        log.info("Attempting to close Command Listener on Port {}", port);
        
        if (!listener.isAlive()) {
            log.warn("Command Listener is Already Stopped");
            return;
        }
        
        try {
            listener.interrupt();
            socket.close();
            log.info("Closed Command Listener");
        } catch (IOException ex) {
            log.error("Error Closing Command Listener", ex);
        }
    }
}
