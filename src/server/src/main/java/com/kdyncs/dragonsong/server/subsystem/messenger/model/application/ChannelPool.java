package com.kdyncs.dragonsong.server.subsystem.messenger.model.application;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author peter
 */

@Component
@Scope("prototype")
public class ChannelPool {
    
    private final Map<String, Channel> channels;
    
    public ChannelPool() {
        channels = new ConcurrentHashMap<>(1000);
    }
    
    public void add(String channelID, Channel channel) {
        channels.put(channelID, channel);
    }
    
    public void remove(String channelID) {
        channels.remove(channelID);
    }
    
    public boolean contains(String channelID) {
        return channels.containsKey(channelID);
    }
    
    public Channel find(String channelID) {
        return channels.get(channelID);
    }
}
