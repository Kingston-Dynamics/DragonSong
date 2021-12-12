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

package com.kdyncs.dragonsong.server;

import com.kdyncs.dragonsong.server.subsystem.ConnectionListener;
import com.kdyncs.dragonsong.server.subsystem.admin.AdminListener;
import com.kdyncs.dragonsong.server.subsystem.command.CommandListener;
import com.kdyncs.dragonsong.server.subsystem.deployment.DeploymentManager;
import com.kdyncs.dragonsong.server.subsystem.messenger.MessengerListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
@ComponentScan(basePackages = "com.kdyncs.dragonsong")
@EnableJpaRepositories("com.kdyncs.dragonsong")
@EntityScan("com.kdyncs.dragonsong")
public class Server implements CommandLineRunner {
    
    public static final int CAPACITY = 2048;
    
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Spring Components
    private final ConnectionListener messenger;
    private final AdminListener admin;
    private final ConnectionListener command;
    private final DeploymentManager deployment;
    
    public Server(MessengerListener messenger, AdminListener admin, CommandListener command, DeploymentManager deployment) {
        this.messenger = messenger;
        this.admin = admin;
        this.command = command;
        this.deployment = deployment;
    }
    
    public static void main(String[] args) {
        
        // Create a Spring Application
        SpringApplication application = new SpringApplication(Server.class);

        // This application does not use a web server
        application.setWebApplicationType(WebApplicationType.NONE);
        
        // Run Application
        application.run(args);
    }
    
    @Override
    public void run(String... args) {
        log.info("Starting DragonSong");
        this.start();
    }
    
    public void start() {
        
        ////////////////////////////////
        // STARTUP MESSENGER LISTENER //
        ////////////////////////////////
        messenger.start();
        
        ////////////////////////////
        // STARTUP ADMIN LISTENER //
        ////////////////////////////
        admin.start();
        
        //////////////////////////////
        // STARTUP COMMAND LISTENER //
        //////////////////////////////
        command.start();
        
        ////////////////////////////////
        // STARTUP DEPLOYMENT MANAGER //
        ////////////////////////////////
        deployment.start();
        
    }
    
    ///////////////////////////
    // SERVER SHUTDOWN LOGIC //
    ///////////////////////////
    public void stop() {
        
        /////////////////////////////////
        // SHUTDOWN MESSENGER LISTENER //
        /////////////////////////////////
        messenger.stop();
        
        /////////////////////////////
        // SHUTDOWN ADMIN LISTENER //
        /////////////////////////////
        admin.stop();
        
        ///////////////////////////////
        // SHUTDOWN COMMAND LISTENER //
        ///////////////////////////////
        command.stop();
        
        /////////////////////////////////
        // SHUTDOWN DEPLOYMENT MANAGER //
        /////////////////////////////////
        deployment.stop();
    }
}
