package com.kingstondynamics.dragonsong.server.subsystem.deployment;

import com.kingstondynamics.dragonsong.server.subsystem.messenger.model.application.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author peter
 */

@Component
public class ApplicationPool {

    private final Map<String, Application> applications;
    private final Logger log = LogManager.getLogger();

    @Autowired
    public ApplicationPool() {
        log.info("Creating new Application Pool");
        applications = new ConcurrentHashMap<>(100);
    }

    public void add(Application application) {
        log.info("Adding Application to Pool");
        applications.put(application.getApplicationKey(), application);
        log.info("Deployments: " + deployCount());
    }
    
    public void remove(String applicationKey) {
        log.debug("Removing Application {} from Pool", applicationKey);
        applications.remove(applicationKey);
    }
    
    public Application get(String applicationKey) {
        return applications.get(applicationKey);
    }
    
    public boolean isDeployed(String applicationKey) {
        return applications.containsKey(applicationKey);
    }
    
    public int deployCount() {
        return applications.size();
    }
    
    public Set<String> getKeys() {
        return applications.keySet();
    }
}
