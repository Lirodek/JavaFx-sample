package org.sight.kiosk.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class DetailController {

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
    protected void onTestButtonClicked(ActionEvent event) {
        System.out.println("함수 실행");
    }
}
