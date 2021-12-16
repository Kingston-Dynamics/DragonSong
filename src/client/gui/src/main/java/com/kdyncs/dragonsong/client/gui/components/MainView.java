package com.kdyncs.dragonsong.client.gui.components;

import com.kdyncs.dragonsong.client.common.DragonConfig;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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

    @Autowired
    public MainView(DragonConfig config) {
        this.config = config;
    }

    @FXML
    private void connect() {
        errorText.setText("Connecting...");

        // Set host information
        config.setHostname(address.getText());
        config.setPort(Integer.parseInt(port.getText()));
    }
}
