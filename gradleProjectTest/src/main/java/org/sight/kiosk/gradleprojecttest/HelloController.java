package org.sight.kiosk.gradleprojecttest;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class HelloController {
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

    /* Terminates Application */
    public void closeSystem(){
        Platform.exit();
        System.exit(0);
    }

//    public void minimizeWindow(){
//        MainLauncher.getPrimaryStage().setIconified(true);
//    }

    @FXML
    protected void onHelloButtonClick2() {welcomeText.setText("안녕하세요 JavaFx에 오신것을 환영합니다.");}
}