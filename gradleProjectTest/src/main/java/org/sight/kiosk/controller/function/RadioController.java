package org.sight.kiosk.controller.function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.sight.kiosk.util.SceneSwitcher;
import org.sight.kiosk.util.View;

import java.io.IOException;

public class RadioController {

    private ToggleGroup test = new ToggleGroup();

    @FXML
    private RadioButton radioA;
    @FXML
    private RadioButton radioB;

    @FXML
    protected void initialize() {
        // ToggleGroup을 명시적으로 설정
        radioA.setToggleGroup(test);
        radioB.setToggleGroup(test);

        test.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                RadioButton selected = (RadioButton) newValue;
                System.out.println("현재 선택된 옵션: " + selected.getText());
            }
        });
    }

    @FXML
    protected void onTestButtonClicked(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.MAIN_VIEW);
    }

    @FXML
    protected void onNextButton(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.TEXT_FIELD);
    }
}
