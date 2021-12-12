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
