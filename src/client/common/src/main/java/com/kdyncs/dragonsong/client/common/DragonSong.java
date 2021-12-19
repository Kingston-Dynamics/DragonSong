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

import com.kdyncs.dragonsong.common.event.ShutdownEvent;
import com.kdyncs.dragonsong.protocol.message.Message;
import com.kdyncs.dragonsong.protocol.message.type.channel.ChannelConnect;
import com.kdyncs.dragonsong.protocol.message.type.channel.ChannelTransmit;
import com.kdyncs.dragonsong.protocol.message.type.heartbeat.HeartbeatPing;
import com.kdyncs.dragonsong.protocol.message.type.user.UserTransmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DragonSong {
    
    // Data Connection
    private final DragonNet network;
    private final DragonConfig config;

    @Autowired
    public DragonSong(DragonConfig config, DragonNet network) {
        
        // Setup Networking
        this.config = config;
        this.network = network;
    }
    
    public void connect() {
        network.connect();
    }
    
    public void disconnect() {
        network.disconnect();
    }
    
    public void sendAuthHandshake() {
        //TODO: Fix this
        //Message handshake = new AuthenticationLogin(config.getAPIKey(), config.getAppID(), config.getAppVer(), config.getConnectionID(), config.getDisplayName());
        //network.write(handshake);
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

    @EventListener
    private void onShutdown(ShutdownEvent event) {

        // Force Disconnection
        disconnect();
    }
}