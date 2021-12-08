package com.kingstondynamics.dragonsong.server;

import com.kingstondynamics.dragonsong.server.subsystem.ConnectionListener;
import com.kingstondynamics.dragonsong.server.subsystem.admin.AdminListener;
import com.kingstondynamics.dragonsong.server.subsystem.command.CommandListener;
import com.kingstondynamics.dragonsong.server.subsystem.deployment.DeploymentManager;
import com.kingstondynamics.dragonsong.server.subsystem.messenger.MessengerListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

/**
 * @author peter
 */

@Component
@SpringBootApplication
@ComponentScan(basePackages = "com.kingstondynamics.dragonsong")
@EnableJpaRepositories("com.kingstondynamics.dragonsong")
@EntityScan("com.kingstondynamics.dragonsong")
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
