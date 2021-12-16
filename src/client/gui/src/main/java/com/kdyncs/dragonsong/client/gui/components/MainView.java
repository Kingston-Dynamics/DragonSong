package com.kdyncs.dragonsong.client.gui.components;

import com.kdyncs.dragonsong.client.common.DragonConfig;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/view/MainView.fxml")
public class MainView {

    @FXML
    private TextField address;

    @FXML
    private TextField port;

    @FXML
    private Text errorText;

    private final DragonConfig config;
    private final FxWeaver weaver;

    @Autowired
    public MainView(DragonConfig config, FxWeaver weaver) {
        this.config = config;
        this.weaver = weaver;
    }

    @FXML
    private void connect() {
        errorText.setText("Connecting...");

        // Set host information
        config.setHostname(address.getText());
        config.setPort(Integer.parseInt(port.getText()));

        Stage stage = new Stage();

    }
}
