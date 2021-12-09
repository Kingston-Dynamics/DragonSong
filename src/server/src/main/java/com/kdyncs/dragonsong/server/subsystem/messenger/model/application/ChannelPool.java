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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

Component
@Scope("prototype")
public class ChannelPool {
    
    private final Map<String, Channel> channels;
    
    public ChannelPool() {
        channels = new ConcurrentHashMap<>(1000);
    }
    
    public void add(String channelID, Channel channel) {
        channels.put(channelID, channel);
    }
    
    public void remove(String channelID) {
        channels.remove(channelID);
    }
    
    public boolean contains(String channelID) {
        return channels.containsKey(channelID);
    }
    
    public Channel find(String channelID) {
        return channels.get(channelID);
    }
}
