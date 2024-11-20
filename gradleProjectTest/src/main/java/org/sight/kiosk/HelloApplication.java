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

public class HelloApplication extends Application {

    private static Stage primaryStageObj;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStageObj = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/main-view.fxml"));
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
}