package com.kingstondynamics.dragonsong.protocol.networking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author peter
 */
public class NetworkReader implements Runnable {
    
    // Logging
    private static final Logger LOG = LoggerFactory.getLogger(NetworkReader.class);
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
