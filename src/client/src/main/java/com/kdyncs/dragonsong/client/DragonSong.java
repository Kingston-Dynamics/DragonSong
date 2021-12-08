package com.kdyncs.dragonsong.client;

import com.kingstondynamics.dragonsong.protocol.message.Message;
import com.kingstondynamics.dragonsong.protocol.message.type.authentication.AuthenticationLogin;
import com.kingstondynamics.dragonsong.protocol.message.type.channel.ChannelConnect;
import com.kingstondynamics.dragonsong.protocol.message.type.channel.ChannelTransmit;
import com.kingstondynamics.dragonsong.protocol.message.type.heartbeat.HeartbeatPing;
import com.kingstondynamics.dragonsong.protocol.message.type.user.UserTransmit;

import java.util.LinkedList;

/**
 * @author peter
 */
public class DragonSong {
    
    // Data Connection
    private final DragonNet network;
    private final DragonConfig config;
    
    public DragonSong(DragonConfig config) {
        
        // Setup Networking
        this.config = config;
        this.network = new DragonNet(config);
        
        new LinkedList<>();
    }
    
    public void connect() {
        network.connect();
    }
    
    public void disconnect() {
        network.disconnect();
    }
    
    public void sendAuthHandshake() {
        Message handshake = new AuthenticationLogin(config.getAPIKey(), config.getAppID(), config.getAppVer(), config.getConnectionID(), config.getDisplayName());
        network.write(handshake);
    }
    
    public void sendPing() {
        Message ping = new HeartbeatPing();
        network.write(ping);
    }
    
    public void joinChannel(String channelID) {
        Message join = new ChannelConnect(channelID);
        network.write(join);
    }
    
    public void publishMessage(String channelID, String message) {
        Message packet = new ChannelTransmit(channelID, message);
        network.write(packet);
    }
    
    public void directMessage(String privateId, String message) {
        Message packet = new UserTransmit(privateId, message);
        network.write(packet);
    }
}