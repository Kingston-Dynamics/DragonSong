package com.kingstondynamics.dragonsong.server.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("dragonsong.dedicated")
public class DedicatedConfiguration {
    
    private boolean isDedicated;
    private String apiKey;
    private String appID;
    private String appVer;
    
    public boolean isDedicated() {
        return isDedicated;
    }
    
    public void setIsDedicated(boolean isDedicated) {
        this.isDedicated = isDedicated;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getAppID() {
        return appID;
    }
    
    public void setAppID(String appID) {
        this.appID = appID;
    }
    
    public String getAppVer() {
        return appVer;
    }
    
    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }
}
