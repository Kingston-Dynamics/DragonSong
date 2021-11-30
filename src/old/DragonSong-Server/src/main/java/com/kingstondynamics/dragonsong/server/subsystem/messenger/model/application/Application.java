package com.kingstondynamics.dragonsong.server.subsystem.messenger.model.application;

import com.kingstondynamics.dragonsong.server.subsystem.messenger.model.connection.ClientConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author peter
 */

@Component
@Scope("prototype")
public class Application {

    // Spring Components
    private final UserPool users;
    private final ChannelPool channels;
    
    @Autowired
    public Application(UserPool users, ChannelPool channels) {
        this.users = users;
        this.channels = channels;
    }
    
    // Application Data
    private String ApplicationKey;
    
    public void disconnect(ClientConnection user) {
        users.remove(user.getExternalID());
    }
    
    public String getApplicationKey() {
        return ApplicationKey;
    }
    
    public void setApplicationKey(String applicationKey) {
        this.ApplicationKey = applicationKey;
    }

    public ChannelPool getChannels() {
        return channels;
    }
    
    public UserPool getUsers() {
        return users;
    }
}
