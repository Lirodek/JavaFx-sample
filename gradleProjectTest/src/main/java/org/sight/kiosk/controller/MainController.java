package org.sight.kiosk.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.sight.kiosk.enums.View;
import org.sight.kiosk.util.PDFPrinter;
import org.sight.kiosk.util.SNMPPrinterStatus;
import org.sight.kiosk.util.SceneSwitcher;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label printStatus;

    private ScheduledExecutorService executorService;

    @FXML
    protected void initialize() {
        executorService = Executors.newSingleThreadScheduledExecutor();

        // 10초마다 비동기로 작업 실행
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("스케쥴러 동작중 . . .");
            
            // SNMPPrinterStatus.f() 호출 결과 가져오기
            String status = SNMPPrinterStatus.f();
        
            // JavaFX UI 스레드에서 Label 업데이트
            Platform.runLater(() -> printStatus.setText(status));
        }, 0, 10, TimeUnit.SECONDS);
    }


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
        } finally {
            printStatus.setText(SNMPPrinterStatus.f());
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
