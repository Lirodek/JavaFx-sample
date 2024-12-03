package org.sight.kiosk.util;

import javafx.scene.control.Alert;

public class CustomAlert {
    private static CustomAlert instance;

    // private CustomAlert 외부에서 객체 생성 방지
    private CustomAlert() {}

    // 인스턴스 반환 메서드
    public static synchronized CustomAlert getInstance() {
        if (instance == null) {
            instance = new CustomAlert();
        }
        return instance;
    }

    public void info(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
