package org.sight.kiosk.controller.function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.sight.kiosk.enums.View;
import org.sight.kiosk.util.CustomAlert;
import org.sight.kiosk.util.SceneSwitcher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class TextFieldController {

    @FXML
    private TextField textField;

    @FXML
    private Label perfectLabel;

    // settings.properties 경로 설정
    private static final String PROPERTIES_FILE = "src/main/resources/settings/setting.properties";

    @FXML
    public void initialize() {
        // FXML 초기화. settings.properties 값 읽기
        try {
            String initialValue = getPropertyValue("test.name");
            if (initialValue != null) {
                textField.setText(initialValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
            perfectLabel.setText("초기 값 로드 실패");
        }
    }

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
        // 값 업데이트
        setPropertyValue("test.name", inputText);

        inputText += "님 환영합니다 !! ";
        perfectLabel.setText(inputText);
    }

    //settings.properties에 값 가져오기
    private String getPropertyValue(String key) throws IOException{
        Properties properties = new Properties();
        try(InputStream input = Files.newInputStream(Paths.get(PROPERTIES_FILE))) {
            properties.load(input);
        }
        return properties.getProperty(key);
    }

    // settings.properties에 값 update
    private void setPropertyValue(String key, String value) throws IOException {
        Properties properties = new Properties();

        // 기존 파일 읽기
        Path path = Paths.get(PROPERTIES_FILE);
        try(InputStream input = Files.newInputStream(path)) {
            properties.load(input);
        }

        // 값 upodate
        properties.setProperty(key, value);

        // 변경된 내용 저장
        try (OutputStream output = Files.newOutputStream(path)) {
            properties.store(output, "내용이 변경됐습니다.");
        }
    }

}
