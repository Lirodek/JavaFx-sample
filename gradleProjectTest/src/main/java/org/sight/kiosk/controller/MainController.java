package org.sight.kiosk.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.sight.kiosk.util.PDFPrinter;
import org.sight.kiosk.util.SceneSwitcher;
import org.sight.kiosk.util.View;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;
    int 증명서_발급_횟수 = 0;


    @FXML
    protected void onHelloButtonClick() {
        try{
            String resultData = PDFPrinter.getInstance().print("test");

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("결과");
            success.setHeaderText("성공!");
            success.setContentText(resultData);
            success.showAndWait();
        } catch (RuntimeException e) {
            e.printStackTrace();
            Alert fail = new Alert(Alert.AlertType.ERROR);
            fail.setTitle("결과");
            fail.setHeaderText("실패!");
            fail.setContentText(e.getMessage());
            fail.showAndWait();
        }
    }

    @FXML
    public void onEndService(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.HELLO_VIEW);
    }

    @FXML
    protected void onGoingCombo(ActionEvent event) throws IOException{
        SceneSwitcher.getInstance().switcher(event, View.COMBO_BOX);
    }
    @FXML
    protected void onGoingCheck(ActionEvent event) throws IOException{
        SceneSwitcher.getInstance().switcher(event, View.CHECK_BOX);
    }
    @FXML
    protected void onGoingRadio(ActionEvent event) throws IOException{
        SceneSwitcher.getInstance().switcher(event, View.RADIO_GROUP);
    }
    @FXML
    protected void onGoingTextField(ActionEvent event) throws IOException{
        SceneSwitcher.getInstance().switcher(event, View.TEXT_FIELD);
    }
}
