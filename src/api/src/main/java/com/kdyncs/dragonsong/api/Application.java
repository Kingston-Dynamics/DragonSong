package com.kdyncs.dragonsong.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author peter
 */

@SpringBootApplication
@EnableScheduling
public class Application {
    
    public static void main(String[] args) {
        
        //Log Shit
        Logger log = LogManager.getLogger();
        log.info("Starting up DragonSong API");
        
        // Create a spring Application so we have more control over initialization
        SpringApplication application = new SpringApplication(Application.class);
        
        // Custom Properties so that we can pass additional arguments to Spring
        //Properties properties = new Properties();
        
        // We'll use this to assemble a list of Config Locations
        //StringBuilder configLocations = new StringBuilder();
        
        // Add a config folder
        //configLocations.append("classpath:config/database/,");
        //configLocations.append("classpath:config/server/,");
        //configLocations.append("classpath:config/services/");
        
        // Add the Folders as Config Locations
        //properties.setProperty("spring.config.additional-location", configLocations.toString());
        //application.setDefaultProperties(properties);
        
        
        //Run Application For Real
        SpringApplication.run(Application.class, args);
    }
}
