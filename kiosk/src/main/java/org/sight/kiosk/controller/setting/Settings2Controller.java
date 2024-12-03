package org.sight.kiosk.controller.setting;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.sight.kiosk.enums.View;
import org.sight.kiosk.util.SceneSwitcher;

import java.io.IOException;

public class Settings2Controller {

    @FXML
    private Button certificateA1Btn, certificateA2Btn, certificateBBtn, confirmBtn;

    // 클릭된 버튼 저장용
    private Button selectedCertificateButton = null;

    @FXML
    private void onCertificateButtonClick(ActionEvent event) {
        if (selectedCertificateButton != null) {
            // 이전 선택 버튼 초기화
            selectedCertificateButton.setStyle("");
        }
        // 현재 선택된 버튼 강조
        selectedCertificateButton = (Button) event.getSource();
        selectedCertificateButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
    }

    @FXML
    private void onConfirmButtonClick(ActionEvent event) {
        try {
            SceneSwitcher.getInstance().switcher(event, View.APPLICATION1_VIEW);
        } catch (IOException e) {
            e.printStackTrace();
            // 에러 발생 시 사용자에게 알림 표시 (옵션)
        }
    }
}