package com.kdyncs.dragonsong.server.messenger.model.application;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author peter
 */

@Component
@Scope("prototype")
public class Channel {
    
    // Channel Data
    private String name;
    private SubscriberPool subscribers;
    
    // Default Constructor
    public Channel() {
        subscribers = new SubscriberPool();
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public SubscriberPool getSubscribers() {
        return subscribers;
    }
    
    public void setSubscribers(SubscriberPool subscribers) {
        this.subscribers = subscribers;
    }
}
