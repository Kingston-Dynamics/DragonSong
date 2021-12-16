package com.kdyncs.dragonsong.client.gui.components;

import com.kdyncs.dragonsong.client.common.DragonConfig;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/view/ChatView.fxml")
public class ChatView {

    private final DragonConfig config;
    private final FxWeaver weaver;

    @Autowired
    public ChatView(DragonConfig config, FxWeaver weaver) {
        this.config = config;
        this.weaver = weaver;
    }

}
