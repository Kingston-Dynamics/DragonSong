package com.kdyncs.dragonsong.client.gui;

import com.kdyncs.dragonsong.client.gui.components.MainView;
import com.kdyncs.dragonsong.client.gui.event.StageReadyEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PrimaryStageInitializer {

    private final FxWeaver weaver;


    @Autowired
    PrimaryStageInitializer(FxWeaver weaver) {
        this.weaver = weaver;
    }

    @EventListener
    private void onApplicationEvent(StageReadyEvent event) {

        // Get Stage from Event
        Stage stage = event.stage;

        // Loan up Main View
        Scene scene = new Scene(weaver.loadView(MainView.class));

        // Assign Scene to Stage
        stage.setScene(scene);

        // No need to resize this.
        stage.setResizable(false);

        // Set Title Text
        stage.setTitle("DragonSong Client");

        // Show Stage
        stage.show();
    }
}
