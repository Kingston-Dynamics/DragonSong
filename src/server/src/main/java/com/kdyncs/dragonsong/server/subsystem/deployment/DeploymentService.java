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
import com.kdyncs.dragonsong.server.subsystem.messenger.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeploymentService {
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final PartitionPool applications;
    private final AuthenticationService authentication;
    
    @Autowired
    public DeploymentService(PartitionPool applications, AuthenticationService authentication) {
        this.applications = applications;
        this.authentication = authentication;
    }
    
    public void deploy() {
    
    }
    
    public void undeploy(String key) {
        
        log.info("Undeploying application {}", key);
        
        Partition partition = applications.get(key);

        // Sanity Checking
        if (partition == null) {
            throw new DeploymentException("Application ");
        }

        // Shutdown Application
        if (partition != null) {
            
            Map<String, String> users = partition.getUsers().getAll();
            
            for (String user : users.values()) {
                authentication.disconnect(user);
            }
        }
        
        log.info("undeployed application {}", key);
    }
}
