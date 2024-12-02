package org.sight.kiosk;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class HelloApplication extends Application {
    private static Stage primaryStageObj;

    @Override
    public void start(Stage primaryStage) throws IOException {
        propsTest();

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
        try (InputStream input = getClass().getResourceAsStream("/settings/application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
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
}