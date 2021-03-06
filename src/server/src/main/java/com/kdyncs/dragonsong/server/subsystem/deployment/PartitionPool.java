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

package com.kdyncs.dragonsong.server.subsystem.deployment;

import com.kdyncs.dragonsong.server.subsystem.messenger.model.application.Partition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PartitionPool {

    private final Map<String, Partition> applications;
    private final Logger log = LogManager.getLogger();

    @Autowired
    public PartitionPool() {
        log.info("Creating new Partition Pool");
        applications = new ConcurrentHashMap<>(100);
    }

    public void add(Partition partition) {
        log.info("Adding Partition to Pool");
        applications.put(partition.getApiKey(), partition);
        log.info("Deployments: " + deployCount());
    }
    
    public void remove(String applicationKey) {
        log.debug("Removing Partition {} from Pool", applicationKey);
        applications.remove(applicationKey);
    }
    
    public Partition get(String applicationKey) {
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
