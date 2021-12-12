package com.kdyncs.dragonsong.client.gui;

import com.kdyncs.dragonsong.client.gui.components.MainView;
import com.kdyncs.dragonsong.client.gui.event.StageReadyEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

    private final FxWeaver weaver;


    @Autowired
    PrimaryStageInitializer(FxWeaver weaver) {
        this.weaver = weaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.stage;
        Scene scene = new Scene(weaver.loadView(MainView.class), 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}
