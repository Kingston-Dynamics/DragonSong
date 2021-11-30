package com.kdyncs.dragonsong.server.messenger.service;

import com.kdyncs.dragonsong.protocol.message.type.ChannelConnect;
import com.kdyncs.dragonsong.protocol.message.type.ChannelTransmit;
import com.kdyncs.dragonsong.protocol.utils.Readinator;
import com.kdyncs.dragonsong.server.ConnectionPool;
import com.kdyncs.dragonsong.server.messenger.model.application.*;
import com.kdyncs.dragonsong.server.messenger.protocol.Command;
import com.kdyncs.dragonsong.server.messenger.model.connection.ClientConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final ConnectionPool connections;
    private final Application application;
    
    public ChannelService(ConnectionPool connections, Application application) {
        this.connections = connections;
        this.application = application;
    }
    
    public void connect(Command command) {
        
        log.info("Joining Channel");
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // Rebuild Message
        Readinator reader = new Readinator(command.getData());
        ChannelConnect message = new ChannelConnect(reader);
        
        // TODO: Validate Protocol State

        // Get Channels
        ChannelPool channels = application.getChannels();
        
        // Create Channel if not exists
        if (!channels.contains(message.getChannelId())) {
            Channel channel = new Channel();
            channel.setName(message.getChannelId());
            channels.add(channel.getName(), channel);
        }
        
        Channel channel = channels.find(message.getChannelId());
        SubscriberPool subscribers = channel.getSubscribers();
        
        if (subscribers.contains(connection.getData().getPlayerId())) {
            // TODO: Send Notification
            return;
        }
        
        subscribers.add(connection.getData().getPlayerId());
        
        log.info("Channel Joined");
    }
    
    public void disconnect(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void getName(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void transmit(Command command) {
        
        log.info("Transmitting to Channel");
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // Rebuild Message
        Readinator reader = new Readinator(command.getData());
        ChannelTransmit message = new ChannelTransmit(reader);
        
        // TODO: Validate Protocol State

        // Get Users
        UserPool users = application.getUsers();
        
        // Get Channels
        ChannelPool channels = application.getChannels();
        
        // TODO: Validate channel exists
        Channel channel = channels.find(message.getChannelId());
        SubscriberPool subscribers = channel.getSubscribers();
        
        // TODO: Validate calling user is in subscriber pool
        
        log.info("Subscriber Count: {}", subscribers.getAll().size());
        
        log.info("Transmitting to Users");
        for (String privateId : subscribers.getAll()) {
            
            log.info("Transmitting");
            
            log.info("Private ID: {}", privateId);
            String connectionId = users.find(privateId);
            log.info("Connection ID: {}", connectionId);
            
            // Bounce channel communication over to other users on connection.
            connection.write(command.getData());
        }
        
        log.info("Transmission Finished");
    }
    
    public void getUserCount(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void getUserList(Command command) {
        throw new UnsupportedOperationException();
    }
}
