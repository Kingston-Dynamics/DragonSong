package com.kdyncs.dragonsong.server;

import com.kdyncs.dragonsong.server.messenger.MessengerListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author peter
 */

@Component
@SpringBootApplication
@ComponentScan(basePackages = "com.kdyncs.dragonsong")
@EntityScan("com.kdyncs.dragonsong")
@EnableScheduling
public class Server implements CommandLineRunner {

    private final Logger log = LogManager.getLogger();
    private final MessengerListener messenger;
    
    public Server(MessengerListener messenger) {
        this.messenger = messenger;
    }
    
    public static void main(String[] argv) {
        
        // Create a Spring Application
        SpringApplication application = new SpringApplication(Server.class);
        
        // Run Application
        application.run(argv);
    }
    
    @Override
    public void run(String[] argv) {
        log.info("Starting DragonSong");
        this.start();
    }

    ////////////////////////
    // SERVER START LOGIC //
    ////////////////////////
    public void start() {
        
        ////////////////////////////////
        // STARTUP MESSENGER LISTENER //
        ////////////////////////////////
        messenger.start();
    }
}
