package org.sight.kiosk.controller.function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.sight.kiosk.util.CustomAlert;
import org.sight.kiosk.util.SceneSwitcher;
import org.sight.kiosk.util.View;

import java.io.IOException;

public class TextFieldController {

    @FXML
    private TextField textField;

    @FXML
    private Label perfectLabel;

    @FXML
    private void onGoingMenu(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.MAIN_VIEW);
    }

    @FXML
    private void onGoingNext(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.COMBO_BOX);
    }

    @FXML
    private void onComplate(ActionEvent event) throws IOException {
        String inputText = textField.getText().trim();
        if ( inputText == null || inputText.isEmpty() ) {
            String title = "정보";
            String header = "정보";
            String content = "이름을 입력해주세요.";
            CustomAlert.getInstance().info(title, header, content);
            return;
        }
        inputText += "님 환영합니다 !! ";
        perfectLabel.setText(inputText);
    }
}
