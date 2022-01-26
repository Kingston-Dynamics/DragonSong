package com.kdyncs.dragonsong.server.subsystem.messenger.service;

import com.kdyncs.dragonsong.protocol.message.type.channel.ChannelConnect;
import com.kdyncs.dragonsong.protocol.message.type.channel.ChannelTransmit;
import com.kdyncs.dragonsong.protocol.utils.Readinator;
import com.kdyncs.dragonsong.server.core.pools.ConnectionPool;
import com.kdyncs.dragonsong.server.subsystem.deployment.PartitionPool;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.application.*;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.connection.ClientConnection;
import com.kdyncs.dragonsong.server.subsystem.messenger.protocol.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final ConnectionPool connections;
    private final PartitionPool applications;
    
    public ChannelService(ConnectionPool connections, PartitionPool applications) {
        this.connections = connections;
        this.applications = applications;
    }
    
    public void connect(Command command) {
        
        log.info("Joining Channel");
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // Rebuild Message
        Readinator reader = new Readinator(command.getCommand());
        ChannelConnect message = new ChannelConnect(reader);
        
        // TODO: Validate Protocol State
        
        // Lookup Application
        Partition partition = applications.get(connection.getApplicationKey());
        
        // Get Channels
        ChannelPool channels = partition.getChannels();
        
        // Create Channel if not exists
        if (!channels.contains(message.getChannelId())) {
            Channel channel = new Channel();
            channel.setName(message.getChannelId());
            channels.add(channel.getName(), channel);
        }
        
        Channel channel = channels.find(message.getChannelId());
        SubscriberPool subscribers = channel.getSubscribers();
        
        if (subscribers.contains(connection.getExternalID())) {
            // TODO: Send Notification
            return;
        }
        
        subscribers.add(connection.getExternalID());
        
        log.info("Channel Joined");
    }
    
    public void disconnect(Command command) {
    
    }
    
    public void getName(Command command) {
    
    }
    
    public void transmit(Command command) {
        
        log.info("Transmitting to Channel");
        
        // Retrieve Client
        ClientConnection connection = connections.get(command.getIssuer());
        
        // Rebuild Message
        Readinator reader = new Readinator(command.getCommand());
        ChannelTransmit message = new ChannelTransmit(reader);
        
        // TODO: Validate Protocol State
        
        // Lookup Application
        Partition partition = applications.get(connection.getApplicationKey());
        UserPool users = partition.getUsers();
        
        // Get Channels
        ChannelPool channels = partition.getChannels();
        
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
            ClientConnection subscriber = connections.get(connectionId);
            
            // Bounce channel communication over to other users on connection.
            subscriber.getWriter().write(command.getCommand());
        }
        
        log.info("Transmission Finished");
    }
    
    public void getUserCount(Command command) {
    
    }
    
    public void getUserList(Command command) {
    
    }
}
