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

import com.kdyncs.dragonsong.database.schema.data.dao.PartitionDAO;
import com.kdyncs.dragonsong.database.schema.data.model.PartitionModel;
import com.kdyncs.dragonsong.server.subsystem.messenger.model.application.Partition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeploymentManager implements Runnable {
    
    public static final int L = 10000;
    // Logging
    private static final Logger LOG = LogManager.getLogger();
    
    // Spring Components
    private final PartitionDAO partitionDAO;
    private final PartitionPool partitionPool;
    private final ApplicationContext context;
    private final DeploymentService deploymentService;
    
    // Threading
    private Thread listener;
    private boolean running;
    
    @Autowired
    public DeploymentManager(PartitionDAO applicationDAO, PartitionPool partitionPool, ApplicationContext context, DeploymentService deploymentService) {
        this.partitionDAO = applicationDAO;
        this.partitionPool = partitionPool;
        this.context = context;
        this.deploymentService = deploymentService;
    }
    
    @PostConstruct
    public void init() {
        this.listener = new Thread(this);

        // Name Thread
        this.listener.setName("DEPLOYMENT_MANAGER");

    }
    
    @Override
    public void run() {
        
        LOG.info("Starting deployment manager.");

        while (running) {
            
            LOG.debug("Checking Deployments.");

            /*
              Find Active Applications
             */
            List<PartitionModel> applications = partitionDAO.getAllActivePartitions();
            
            LOG.debug("Currently Active Partitions: " + applications.size());
            LOG.debug("Currently Deployed Partitions: " + partitionPool.deployCount());

            /*
             * Deploy Partitions
             *
             * If a Partition is marked as active it should be deployed onto the server.
             */
            for (PartitionModel partition : applications) {

                // If Not Deploy then Deploy
                if (shouldDeploy(partition)) {

                    // Log Info for debug purposes
                    LOG.info("Deploying Partition {} {}", partition.getName(), partition.getId());
                    
                    // Create Instance of Application Component
                    Partition deployablePartition = context.getBean(Partition.class);
                    
                    // Fill Out Data
                    deployablePartition.setApiKey(partition.getId().toString());
                    
                    // Register (Accept Connections)
                    partitionPool.add(deployablePartition);
                }
            }

            /*
             * Undeploy Any Stopped Applications
             */
            ArrayList<String> undeployables = new ArrayList<>(100);
            
            // Find all undeployable Keys
            for (String key : partitionPool.getKeys()) {
                
                // Check if we should undeploy
                boolean found = shouldUndeploy(applications, key);
                
                // Undeploy if necessary
                if (!found) {
                    undeployables.add(key);
                }
            }
            
            // Undeployificate them
            for (String key : undeployables) {
                deploymentService.undeploy(key);
            }
            
            // Slow it down
            try {
                LOG.debug("Finished Checking Deployments.");
                Thread.sleep(L);
            } catch (InterruptedException ex) {
                LOG.debug("Deployment Manager Interrupted.");
            }
        }
        
        LOG.info("Stopping Deployment Manager.");
    }

    /**
     * Check if Partition should be deployed
     */
    private boolean shouldDeploy(PartitionModel partition) {
        return !partitionPool.isDeployed(partition.getId().toString());
    }

    /**
     * Check if application should undeploy
     *
     * @param applications list of applications
     * @param key          key of stopped application
     * @return should application be undeployed
     */
    private boolean shouldUndeploy(List<PartitionModel> applications, String key) {
        
        for (PartitionModel application : applications) {
            if (key.equals(application.getId().toString())) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Start the Deployment Manager
     */
    public void start() {

        LOG.info("Attempting to start deployment manager");

        if (listener.isAlive()) {
            LOG.warn("Deployment Manager Already Started");
            return;
        }
        
        running = true;
        listener.start();
    }
    
    /**
     * Stop the Deployment Manager
     */
    public void stop() {
        
        LOG.info("Attempting to stop Deployment Manager");
        
        if (!listener.isAlive()) {
            LOG.warn("Deployment Manager is Already Stopped");
            return;
        }
        
        running = false;
        listener.interrupt();
    }
}
