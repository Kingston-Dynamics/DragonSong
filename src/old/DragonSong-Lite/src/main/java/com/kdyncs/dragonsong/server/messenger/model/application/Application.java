package com.kdyncs.dragonsong.server.messenger.model.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author peter
 */

@Component
public class Application {

    // Spring Components
    private final UserPool users;
    private final ChannelPool channels;
    
    @Autowired
    public Application(UserPool users, ChannelPool channels) {
        this.users = users;
        this.channels = channels;
    }

    public ChannelPool getChannels() {
        return channels;
    }
    
    public UserPool getUsers() {
        return users;
    }
}
