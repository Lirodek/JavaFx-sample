package org.sight.kiosk.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.sight.kiosk.util.SceneSwitcher;
import org.sight.kiosk.util.View;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;
    int 증명서_발급_횟수 = 0;


    @FXML
    protected void onHelloButtonClick() {

        if (Math.random() < 0.5) {
            // 성공 (50% 확률)
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("결과");
            success.setHeaderText("성공!");
            success.setContentText("증명서 발급의 성공하였습니다!\n 증명서 발급횟수 :: " + ++증명서_발급_횟수);
            success.showAndWait();
        } else {
            // 실패 (50% 확률)
            Alert fail = new Alert(Alert.AlertType.ERROR);
            fail.setTitle("결과");
            fail.setHeaderText("실패!");
            fail.setContentText("50% 확률로 증명서 발급의 실패하였습니다!\n 증명서 발급횟수 :: " + 증명서_발급_횟수);
            fail.showAndWait();
        }
    }

    @FXML
    public void onEndService(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.HELLO_VIEW);
    }

    @FXML
    protected void onGoingDetailView(ActionEvent event) throws IOException{
        SceneSwitcher.getInstance().switcher(event, View.NEW_VIEW);
    }
}
