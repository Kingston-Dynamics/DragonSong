package com.kdyncs.dragonsong.client.common.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // Logging
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {

        // Create a Spring Application
        SpringApplication application = new SpringApplication(Application.class);

        // This application does not need a web server
        application.setWebApplicationType(WebApplicationType.NONE);

        // Run Application
        application.run(args);
    }
}
