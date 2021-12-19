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

package com.kdyncs.dragonsong.protocol.networking;

import java.io.DataInputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkReader implements Runnable {
    
    // Logging
    private static final Logger LOG = LogManager.getLogger();
    // Threading
    private final Thread thread;
    // Parent
    private NetworkManager manager;
    private boolean running;
    
    public NetworkReader(NetworkManager manager) {
        this.manager = manager;
        this.thread = new Thread(this);
    }
    
    @Override
    public void run() {
        
        LOG.debug("Thread Starting");
        
        while (running) {
            try {
                
                DataInputStream input = new DataInputStream(manager.getSocket().getInputStream());
                
                // Get Data Length
                // TODO: This throws an EOF Excepetion on socket close. Not very clean.
                int length = input.readInt();
                LOG.info("Received Length {} Bytes", length);
                
                // Prepare Data Buffer
                byte[] data = new byte[length];
                
                // Fill Data buffer
                input.readFully(data, 0, data.length);
                LOG.info("Received Data {} Bytes", length);
                
                // Process Input
                manager.handleInput(data);
                
            } catch (IOException ex) {
                if (manager.getSocket().isClosed()) {
                    LOG.info("Socket Closed", ex);
                    running = false;
                } else {
                    LOG.debug("Issue Reading From Socket", ex);
                }
            }
        }
        
        LOG.debug("Thread Stopped");
        
    }
    
    public void setManager(NetworkManager manager) {
        this.manager = manager;
    }
    
    public void start() {
        LOG.info("Attempting to start Network Reader");
        
        if (thread.isAlive()) {
            LOG.warn("Network Reader is Already Started");
            return;
        }
        
        running = true;
        thread.start();
    }
    
    public void stop() {
        LOG.info("Attempting to stop Network Reader");
        
        if (!thread.isAlive()) {
            LOG.warn("Network Reader is Already Stopped");
            return;
        }
        
        running = false;
    }
}
