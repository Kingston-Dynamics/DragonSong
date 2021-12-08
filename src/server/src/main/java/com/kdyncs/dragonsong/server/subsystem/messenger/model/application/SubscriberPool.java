package com.kdyncs.dragonsong.server.subsystem.messenger.model.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SubscriberPool {
    
    private static final Logger log = LogManager.getLogger();
    
    private final Set<String> subscribers;
    
    /**
     * Instantiate Subscriber Pool
     */
    public SubscriberPool() {
        log.info("Creating Subscriber Pool");
        this.subscribers = ConcurrentHashMap.newKeySet();
    }
    
    public void add(String privateID) {
        log.info("Adding {}", privateID);
        subscribers.add(privateID);
    }
    
    public void remove(String privateID) {
        log.info("Removing {}", privateID);
        subscribers.remove(privateID);
    }
    
    public boolean contains(String privateID) {
        log.info("Containing {}", privateID);
        return subscribers.contains(privateID);
    }
    
    public Set<String> getAll() {
        return this.subscribers;
    }
}
