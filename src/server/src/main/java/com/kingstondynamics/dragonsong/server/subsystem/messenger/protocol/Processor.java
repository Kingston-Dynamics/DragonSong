/* Copyright (C) KINGSTON DYNAMICS INC. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.kingstondynamics.dragonsong.server.subsystem.messenger.protocol;

import com.kingstondynamics.dragonsong.protocol.message.data.MessageType;
import com.kingstondynamics.dragonsong.protocol.utils.Determinator;
import com.kingstondynamics.dragonsong.server.subsystem.messenger.service.*;
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
    private final AuthenticationService authentication;
    private final ApplicationService application;
    private final ChannelService channel;
    private final PingService ping;
    private final SelfService self;
    private final ServerService server;
    private final UserService user;
    
    @Autowired
    public Processor(AuthenticationService authentication, ApplicationService application, ChannelService channel, PingService ping, SelfService self, ServerService server, UserService user) {
        
        this.authentication = authentication;
        this.application = application;
        this.channel = channel;
        this.ping = ping;
        this.self = self;
        this.server = server;
        this.user = user;
        
        this.queue = new LinkedBlockingQueue<>();
        this.thread = new Thread(this);
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
            }
        }
        log.debug("Thread Stopping");
    }
    
    private void processCommand(Command command) {
        
        log.debug("processing command");
        
        MessageType type = Determinator.determinate(command.getCommand());
        
        switch (type) {
            case APPLICATION_CHANNEL_LIST:
                application.getChannels(command);
                break;
            case APPLICATION_NAME:
                application.getName(command);
                break;
            case APPLICATION_TRANSMIT:
                application.transmit(command);
                break;
            case APPLICATION_UPTIME:
                application.getUptime(command);
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
                authentication.disconnect(command);
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
                unimplementedCommand(command);
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
                unknownCommand(command);
                break;
        }
    }
    
    private void unknownCommand(Command command) {
    
    }
    
    private void unimplementedCommand(Command command) {
    
    }
}
