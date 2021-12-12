package com.kdyncs.dragonsong.client.common.gui;

import com.kdyncs.dragonsong.client.common.gui.components.MainView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Launcher extends Application {

    private ConfigurableApplicationContext context;

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
    public void start(Stage stage) {
        FxWeaver weaver = context.getBean(FxWeaver.class);
        Parent root = weaver.loadView(MainView.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        this.context.close();
        Platform.exit();
    }
}
