/* Copyright (C) KINGSTON DYNAMICS INC. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.kdyncs.dragonsong.server.messenger.protocol;

import com.kdyncs.dragonsong.protocol.message.data.MessageType;
import com.kdyncs.dragonsong.protocol.utils.Determinator;
import com.kdyncs.dragonsong.server.messenger.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author peter
 */

@Component
public class Processor implements Runnable {
    
    // Logging
    private final Logger log = LogManager.getLogger();
    
    // Command Queue
    private final LinkedBlockingQueue<Command> queue;
    
    // Threading
    private final Thread thread;
    private boolean isRunning;
    
    // Spring Services
    private final ConnectionService connection;
    private final AuthenticationService authentication;
    private final ApplicationService application;
    private final ChannelService channel;
    private final PingService ping;
    private final SelfService self;
    private final ServerService server;
    private final UserService user;
    
    @Autowired
    public Processor(ConnectionService connection, AuthenticationService authentication, ApplicationService application, ChannelService channel, PingService ping, SelfService self, ServerService server, UserService user) {

        this.connection = connection;
        this.authentication = authentication;
        this.application = application;
        this.channel = channel;
        this.ping = ping;
        this.self = self;
        this.server = server;
        this.user = user;
        
        this.queue = new LinkedBlockingQueue<>();
        this.thread = new Thread(this);
        this.thread.setName("COMMAND_PROCESSOR");

        this.isRunning = false;
    }
    
    public void queueCommand(Command command) {
        queue.add(command);
    }
    
    @PostConstruct
    public void init() {
        start();
    }
    
    public void start() {
        this.isRunning = true;
        this.thread.start();
    }
    
    public void stop() {
        this.isRunning = false;
        this.thread.interrupt();
    }
    
    @Override
    public void run() {
        
        log.debug("Thread Starting");
        
        while (isRunning) {
            
            try {
                
                // Wait for a command to be queued
                Command command = queue.take();
                
                // Handle the Command
                processCommand(command);
                
            } catch (InterruptedException e) {
                // Thread interrupted, likely for shutdown
                log.debug("Thread Interrupted");
                thread.interrupt();
            }
        }
        log.debug("Thread Stopping");
    }
    
    private void processCommand(Command command) {
        
        log.debug("processing command");
        
        MessageType type = Determinator.determinate(command.getData());
        
        switch (type) {
            case APPLICATION_CHANNEL_LIST:
                application.getChannels(command);
                break;
            case APPLICATION_TRANSMIT:
                application.transmit(command);
                break;
            case AUTHENTICATION_LOGIN:
                authentication.login(command);
                break;
            case AUTHENTICATION_LOGOUT:
                authentication.logout(command);
                break;
            case AUTHENTICATION_RECONNECT:
                authentication.reconnect(command);
                break;
            case AUTHENTICATION_DISCONNECT:
                authentication.logout(command);
                connection.remove(command);
                break;
            case CHANNEL_CONNECT:
                channel.connect(command);
                break;
            case CHANNEL_DISCONNECT:
                channel.disconnect(command);
                break;
            case CHANNEL_NAME:
                channel.getName(command);
                break;
            case CHANNEL_TRANSMIT:
                channel.transmit(command);
                break;
            case CHANNEL_USER_COUNT:
                channel.getUserCount(command);
                break;
            case CHANNEL_USER_LIST:
                channel.getUserList(command);
                break;
            case HEARTBEAT_PING:
                ping.ping(command);
                break;
            case HEARTBEAT_PONG:
            case NOTIFICATION:
                unimplementedCommand();
                break;
            case SELF_TRANSMIT:
                self.transmit(command);
                break;
            case SELF_UPTIME:
                self.getUptime(command);
                break;
            case SELF_WHATAMI:
                self.whatami(command);
                break;
            case SELF_WHOAMI:
                self.whoami(command);
                break;
            case SERVER_ADDRESS:
                server.getAddress(command);
                break;
            case SERVER_HOSTNAME:
                server.getHostname(command);
                break;
            case SERVER_PING_TIME:
                server.getPingTime(command);
                break;
            case SERVER_TRANSMIT:
                server.transmit(command);
                break;
            case SERVER_UPTIME:
                server.getUptime(command);
                break;
            case USER_BLOCK:
                user.block(command);
                break;
            case USER_TRANSMIT:
                user.transmit(command);
                break;
            case USER_UNBLOCK:
                user.unblock(command);
                break;
            case USER_UPTIME:
                user.getUptime(command);
                break;
            case USER_WHATIS:
                user.whatis(command);
                break;
            case USER_WHOIS:
                user.whois(command);
                break;
            default:
                unknownCommand();
                break;
        }
    }
    
    private void unknownCommand() {
        throw new UnsupportedOperationException("Unimplemented");
    }
    
    private void unimplementedCommand() {
        throw new UnsupportedOperationException("Unimplemented");
    }
}
