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

package com.kdyncs.dragonsong.server.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("dragonsong.dedicated")
public class DedicatedConfiguration {
    
    private boolean isDedicated;
    private String apiKey;
    
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
}
