package com.kingstondynamics.dragonsong.client;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.message.type.authentication.AuthenticationDisconnect;
import com.kingstondynamics.dragonsong.protocol.message.type.channel.ChannelTransmit;
import com.kingstondynamics.dragonsong.protocol.networking.NetworkManager;
import com.kingstondynamics.dragonsong.protocol.networking.NetworkReader;
import com.kingstondynamics.dragonsong.protocol.networking.NetworkWriter;
import com.kingstondynamics.dragonsong.protocol.utils.Determinator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

/**
 * @author peter
 */
public class DragonNet implements NetworkManager {
    
    // Logging
    private static final Logger LOG = LoggerFactory.getLogger(DragonNet.class);
    // Client Configuration
    private final DragonConfig config;
    // Socket Connections
    private Socket socket;
    private NetworkWriter writer;
    
    public DragonNet(DragonConfig config) {
        this.config = config;
    }
    
    public void connect() {
        
        LOG.debug("Attempting to Open Connection");
        
        // We can't connect if we've already connected
        if (isOpen()) {
            LOG.debug("Connection Already Open");
            return;
        }
        
        try {
            
            // Connect to Server
            LOG.debug("Opening Socket");
            socket = new Socket(config.getHostname(), config.getPort());
            
            // Create Data Streams
            LOG.debug("Opening Streams");
            NetworkReader reader = new NetworkReader(this);
            writer = new NetworkWriter(this);
            
            // Start Connections
            reader.start();
            writer.start();
            
        } catch (IOException ex) {
            
            // Log Fault
            LOG.error("Failed to Open Dragonsong Connection", ex);
        }
    }
    
    public void disconnect() {
        
        LOG.info("Attempting to Close Connection");
        
        if (!isOpen()) {
            LOG.debug("No Connection to Close");
        }
        
        //try {
        
        Message message = new AuthenticationDisconnect();
        write(message);
        
        LOG.info("Closing Reader");
        //reader.stop();
        LOG.info("Closing Writer");
        //writer.stop();
        LOG.info("Closing Socket");
        //socket.close();
        
        //} catch (IOException ex) {
        //	LOG.error("Shits Broke", ex);
        //}
    }
    
    public void write(Message packet) {
        
        LOG.debug("Attempting to Write Message");
        
        if (!isOpen()) {
            LOG.error("Cannot Write; Connection Closed");
            return;
        }
        
        LOG.debug("Writing Message");
        writer.write(packet.build());
    }
    
    public Boolean isOpen() {
        
        if (socket == null) {
            return false;
        }
        
        return !socket.isClosed();
    }
    
    @Override
    public void handleInput(byte[] data) {
        
        // Determine Packet Type
        MessageType type = Determinator.determinate(data);
        
        // Log Stuff
        LOG.debug("{} {}", type.name(), type.getType());
        
        if (type == MessageType.CHANNEL_TRANSMIT) {
            ChannelTransmit transmit = new ChannelTransmit(data);
            
            LOG.info("Message: {}", transmit.getTransmission());
        }
    }
    
    @Override
    public Socket getSocket() {
        return this.socket;
    }
    
}
