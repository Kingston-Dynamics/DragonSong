package com.kdyncs.dragonsong.client.gui.components;

import com.kdyncs.dragonsong.client.common.DragonConfig;
import com.kdyncs.dragonsong.client.common.DragonSong;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@FxmlView("/view/ChatView.fxml")
public class ChatView {

    private final DragonSong server;
    private final FxWeaver weaver;

    @Autowired
    public ChatView(DragonSong server, FxWeaver weaver) {
        this.server = server;
        this.weaver = weaver;

        server.connect();

        server.sendAuthHandshake();
    }
}
