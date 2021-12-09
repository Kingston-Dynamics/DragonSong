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

import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class NetworkWriter implements Runnable {
    
    // Logging
    private static final Logger LOG = LoggerFactory.getLogger(NetworkWriter.class);
    
    // Threading
    private final Thread thread;
    // Output Queue
    private final LinkedBlockingQueue<byte[]> queue;
    private boolean running;
    // Parent
    private NetworkManager manager;
    
    public NetworkWriter(NetworkManager manager) {
        this.manager = manager;
        this.queue = new LinkedBlockingQueue<>();
        this.thread = new Thread(this);
    }
    
    @Override
    public void run() {
        
        LOG.debug("Thread Starting");
        
        while (running) {
            try {
                
                // Queue Up Writable Data
                byte[] data = queue.take();
                
                // Write Message length and Data
                DataOutputStream output = new DataOutputStream(manager.getSocket().getOutputStream());
                
                //Byteinator.intToBytes(data.length);
                LOG.info("Writing Length {} Bytes", data.length);
                output.write(Byteinator.intToBytes(data.length));
                
                LOG.info("Writing Message {} Bytes", data.length);
                output.write(data);
                output.flush();
                
            } catch (InterruptedException ex) {
                LOG.warn("Writing Interrupted");
            } catch (IOException ex) {
                LOG.warn("Writing Issue", ex);
            }
        }
        
        LOG.debug("Thread Stopped");
    }
    
    public void write(byte[] data) {
        queue.add(data);
    }
    
    public void setManager(NetworkManager manager) {
        this.manager = manager;
    }
    
    public void start() {
        LOG.info("Attempting to start Network Writer");
        
        if (thread.isAlive()) {
            LOG.warn("Network Writer is Already Started");
            return;
        }
        
        running = true;
        thread.start();
    }
    
    public void stop() {
        LOG.info("Attempting to stop Network Writer");
        
        if (!thread.isAlive()) {
            LOG.warn("Network Writer is Already Stopped");
            return;
        }
        
        running = false;
        thread.interrupt();
    }
}
