package org.sight.kiosk.controller.function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import org.sight.kiosk.enums.View;
import org.sight.kiosk.util.CustomAlert;
import org.sight.kiosk.util.SceneSwitcher;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CheckController {

    private ToggleGroup checkBoxGroup = new ToggleGroup();

    @FXML
    CheckBox check1;

    @FXML
    CheckBox check2;

    @FXML
    CheckBox check3;

    @FXML
    CheckBox allClick;

    @FXML
    protected void initialize() throws IOException {
        List<CheckBox> checkBoxGroup = checkBoxes();

        allClick.selectedProperty().addListener((observable, oldValue, newValue) -> {
            checkBoxGroup.forEach(checkBox -> checkBox.setSelected(newValue));
        });
    }

    @FXML
    public void onGoingMenu(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.MAIN_VIEW);
    }

    @FXML
    public void onGoingNext(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.MAIN_VIEW);
    }

    @FXML
    public void onFunctionBtn(ActionEvent event) throws Exception {
        List<CheckBox> checkBoxes = checkBoxes();
        String checkedText = checkBoxes.stream()
                .filter(CheckBox::isSelected)               // 선택된 checkBox만 추출
                .map(CheckBox::getText)                     // 선택된 checkBox의 value
                .collect(Collectors.joining(", ")); // 결과값 도출

        CustomAlert.getInstance().info("정보", "정보", "선택한 checkBox는 :: " + checkedText + "입니다.");
    }

    private List<CheckBox> checkBoxes (){
        return List.of(check1, check2, check3);
    }
}
