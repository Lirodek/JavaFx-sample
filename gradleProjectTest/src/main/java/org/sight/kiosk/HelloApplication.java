package org.sight.kiosk;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;


public class HelloApplication extends Application {
    private static Stage primaryStageObj;

    @Override
    public void start(Stage primaryStage) throws IOException {
        propsTest();
        readJson();

        primaryStageObj = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/hello-view.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Kiosk-system Demo");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/icon.png").toString()));

        Scene scene = new Scene(root, 600, 400);
        scene.setRoot(root);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());

    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getPrimaryStage() {
        return primaryStageObj;
    }

    public void propsTest(){
        // Properties 객체 생성
        Properties prop = new Properties();
        try (InputStream input = getClass().getResourceAsStream("/settings/setting.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find setting.properties");
                return;
            }

            // properties 파일 로드
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // 속성 가져오기
        String testName = prop.getProperty("test.name", "default");
        System.out.println(testName);
    }

    public void readJson(){
        // ObjectMapper 인스턴스 생성
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // JSON 파일 읽기
            File file = new File("src/main/resources/settings/settings.json");

            // JSON 파일을 Map으로 변환
            Map<String, Object> jsonMap = objectMapper.readValue(file, Map.class);

            // Map 출력
            System.out.println("JSON as Map: " + jsonMap);

            // 특정 키 값 가져오기
            Map<String, Object> test = (Map<String, Object>) jsonMap.get("test");
            String name = (String) test.get("name");
            System.out.println("Test name: " + name);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}