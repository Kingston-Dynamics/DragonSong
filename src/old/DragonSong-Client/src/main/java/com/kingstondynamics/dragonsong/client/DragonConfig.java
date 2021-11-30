package com.kingstondynamics.dragonsong.client;

/**
 * @author peter
 */
public class DragonConfig {
    
    // Connection Details
    private final String hostname;
    private final int port;
    
    // Application Details
    private final String APIKey;
    private final String AppID;
    private final String AppVer;
    
    // User Details
    private final String connectionID;
    private final String displayName;
    
    public DragonConfig(String hostname, int port, String APIKey, String AppID, String AppVer, String connectionID, String displayName) {
        this.hostname = hostname;
        this.port = port;
        this.APIKey = APIKey;
        this.AppID = AppID;
        this.AppVer = AppVer;
        this.connectionID = connectionID;
        this.displayName = displayName;
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public int getPort() {
        return port;
    }
    
    public String getAPIKey() {
        return APIKey;
    }
    
    public String getAppID() {
        return AppID;
    }
    
    public String getAppVer() {
        return AppVer;
    }
    
    public String getConnectionID() {
        return connectionID;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
