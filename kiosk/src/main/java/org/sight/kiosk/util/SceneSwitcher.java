package org.sight.kiosk.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.sight.kiosk.enums.View;

import java.io.IOException;

public class SceneSwitcher {
    private static SceneSwitcher instance;

    // private 생성자로 외부에서 객체 생성 방지
    private SceneSwitcher() {}

    // 인스턴스 반환 메서드
    public static synchronized SceneSwitcher getInstance() {
        if (instance == null) {
            instance = new SceneSwitcher();
        }
        return instance;
    }

    public void switcher(ActionEvent event, View view) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(view.getPath()));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }
}
