package com.kdyncs.dragonsong.client.gui;

import com.kdyncs.dragonsong.client.gui.event.StageReadyEvent;
import com.kdyncs.dragonsong.common.event.ShutdownEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;

public class GraphicalApplication extends Application {

    private ConfigurableApplicationContext context;

    // Logging
    private static final Logger log = LogManager.getLogger();

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
    public void start(Stage stage) {
        context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {

        context.publishEvent(new ShutdownEvent(this));


        this.context.close();
        Platform.exit();
    }
}
