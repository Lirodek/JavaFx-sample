package org.sight.kiosk.controller.function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.sight.kiosk.enums.View;
import org.sight.kiosk.util.CustomAlert;
import org.sight.kiosk.util.SceneSwitcher;

import java.io.IOException;

public class ComboController {

    @FXML
    ComboBox<String> comboBox;

    @FXML
    protected void initialize() {
        comboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void onGoingMenu(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.MAIN_VIEW);
    }

    @FXML
    private void onGoingNext(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.CHECK_BOX);
    }

    @FXML
    private void onFunctionBtn() throws IOException {
        String value = comboBox.getValue();
        String title = "정보";
        String header = "정보";
        String content = value + "님 환영합니다.";
        CustomAlert.getInstance().info(title, header, content);
    }
}
