package com.kdyncs.dragonsong.client.gui.components;

import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/view/MainView.fxml")
public class MainView {

    private final FxWeaver weaver;

    @Autowired
    public MainView(FxWeaver weaver) {
        this.weaver = weaver;
    }
}
