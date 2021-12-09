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
