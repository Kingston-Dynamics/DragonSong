package com.kdyncs.dragonsong.client.common.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Launcher extends Application {

    private static ConfigurableApplicationContext context;

    // Logging
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        // Create a Spring Application
        SpringApplication application = new SpringApplication(Launcher.class);

        // This application does not need a web server
        application.setWebApplicationType(WebApplicationType.NONE);

        // Run Application
        context = application.run();
    }

    @Override
    public void start(Stage stage) throws Exception {



    }
}
