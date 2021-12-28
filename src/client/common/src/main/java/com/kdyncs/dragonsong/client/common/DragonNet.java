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

package com.kdyncs.dragonsong.client.common;

import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.message.type.authentication.AuthenticationDisconnect;
import com.kdyncs.dragonsong.protocol.message.type.channel.ChannelTransmit;
import com.kdyncs.dragonsong.protocol.networking.NetworkManager;
import com.kdyncs.dragonsong.protocol.networking.NetworkReader;
import com.kdyncs.dragonsong.protocol.networking.NetworkWriter;
import com.kdyncs.dragonsong.protocol.utils.Determinator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;

@Component
public class DragonNet implements NetworkManager {
    
    // Logging
    private static final Logger LOG = LoggerFactory.getLogger(DragonNet.class);

    // Client Configuration
    private final DragonConfig config;
    private final ApplicationContext context;

    // Test
    private AutoHeartBeat heartBeat;

    // Socket Connections
    private Socket socket;
    private NetworkWriter writer;
    private NetworkReader reader;

    @Autowired
    public DragonNet(DragonConfig config, ApplicationContext context) {
        this.config = config;
        this.context = context;
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
            reader = new NetworkReader(this);
            writer = new NetworkWriter(this);
            
            // Start Connections
            reader.start();
            writer.start();

            // Startup Heartbeat
            heartBeat = context.getBean(AutoHeartBeat.class);
            
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
        
        try {
        
            Message message = new AuthenticationDisconnect();
            write(message);

            // Stop Heartbeat
            heartBeat.stop();

            LOG.info("Closing Reader");
            reader.stop();
            LOG.info("Closing Writer");
            writer.stop();
            LOG.info("Closing Socket");
            socket.close();
        
        } catch (IOException ex) {
        	LOG.error("Shits Broke", ex);
        }
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
