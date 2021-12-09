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

package com.kdyncs.dragonsong.server.subsystem.messenger.model.application;

import com.kdyncs.dragonsong.server.subsystem.messenger.model.connection.ClientConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
