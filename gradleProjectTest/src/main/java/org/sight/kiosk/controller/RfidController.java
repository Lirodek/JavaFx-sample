package org.sight.kiosk.controller;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.sight.kiosk.enums.POLLING_STATUS;
import org.sight.kiosk.enums.View;
import org.sight.kiosk.rfidUtils.*;
import org.sight.kiosk.util.SceneSwitcher;
import org.sight.kiosk.util.TestCardUID;

import javax.smartcardio.CardException;
import javax.smartcardio.CardNotPresentException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.swing.*;
import java.io.IOException;
import java.security.PrivilegedAction;

public class RfidController implements PollingEvent.CardPollingHandler{
    @FXML
    private Label welcomeText;
    private Scene scene;

    private PcscReader _pcscReader;
    private ACSClass _acsClass;
    private CardPollingTask _cardPolling;
    private CardSelector _cardSelector;
    private String deviceId;

    private POLLING_STATUS _pollingStatus = POLLING_STATUS.POLLING_OFF;
    boolean isSuccess = false;

    @FXML
    protected void initialize() throws Exception{
        _pcscReader = new PcscReader();
        // Instantiate an event handler object
        _pcscReader.setPollingEventHandler(new PollingEvent());

        // Register the event handler implementation of this class
        _pcscReader.getPollingEventHandler().addEventListener(this);

        _acsClass = new ACSClass(_pcscReader);
        _cardSelector = new CardSelector(_pcscReader);
        _pcscReader.setEventHandler(new ReaderEvents());

        reset();
        listReaders();
        start();
    }

    /* Terminates Application */
    public void closeSystem(){
        Platform.exit();
        System.exit(0);
    }

    /* change Application */
    @FXML
    public void startButton(ActionEvent event) throws IOException {
        SceneSwitcher.getInstance().switcher(event, View.MAIN_VIEW);

    }

    @FXML
    protected void onHelloButtonClick2() {welcomeText.setText("안녕하세요 JavaFx에 오신것을 환영합니다.");}



    @Override
    public void onCardFound(PollingEvent.CardPollingEventArg event) {
        System.out.println("found!!!");

        try{
            byte[] atr = _pcscReader.getAtr();
            CommandAPDU commandApdu = _pcscReader.getCommandApdu();
            ResponseAPDU responseApdu = _pcscReader.getResponseApdu();
            System.out.println("card uid :: " + _cardSelector.getUid());


            String cardType = _cardSelector.readCardType(atr);
            System.out.println("helper ByteAsString :: " + Helper.byteAsString(atr, true));
            System.out.println("cardType: " + cardType);
            String cardUID = TestCardUID.getCardUID(_pcscReader.getCardChannel());
            System.out.println("cardChannel :: " + cardUID);


            if(_cardPolling != null){

            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onCardRemoved(PollingEvent.CardPollingEventArg event) {

    }

    public void reset()
    {
        if(_cardPolling != null)
        {
            _cardPolling.cancel(true);
            _cardPolling = null;
        }

        _pollingStatus = POLLING_STATUS.POLLING_OFF;
        System.out.println("Initialize Smart Card resources.");

    }

    private void listReaders()
    {
        String[] readerList = null;

        try
        {
            readerList = _pcscReader.listTerminals();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return;
        }
        finally
        {
            if (readerList == null)
            {
                System.out.println("No PC/SC reader detected");
                return;
            }
            else
            {

                for (int i = 0; i < readerList.length; i++)
                {
                    System.out.println("Name: " + readerList[i]);
                }
                deviceId = readerList[0];
                System.out.println("Start polling to detect card in reader.");
            }
        }
    }

    public void start() throws Exception
    {
        //connect to the selected reader
        connect(deviceId);

        try
        {
            _cardPolling = new CardPollingTask();

            // 새로운 스레드에서 Task 실행
            Thread pollingThread = new Thread(_cardPolling);
            pollingThread.setDaemon(true); // 애플리케이션 종료 시 함께 종료
            pollingThread.start();

            _pollingStatus = POLLING_STATUS.POLLING_OFF;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isStatusChanged() throws CardException, Exception
    {
        boolean isStatusChanged = false;

        POLLING_STATUS status;
        boolean isCardPresent = _pcscReader.getActiveTerminal().isCardPresent();

        if (isCardPresent)
            status = POLLING_STATUS.CARD_PRESENT;

        else
            status = POLLING_STATUS.CARD_ABSENT;

        isStatusChanged = _pollingStatus != status;
        _pollingStatus = status;

        return isStatusChanged;
    }

    public boolean connect(final String readerName) {
        try {
            boolean isSuccess = runPrivileged(() -> {
                try {
                    if (_pcscReader._connectionActive) {
                        _pcscReader.disconnect();
                    }

                    if (_pcscReader.connect(readerName, "*") == 0) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception ex) {
                    return false;
                }
            });

            return isSuccess;
        } catch (Exception ex) {
            return false;
        }
    }

    private <T> T runPrivileged(PrivilegedAction<T> action) {
        return action.run();
    }

    public class CardPollingTask extends Task<Void> {

        @Override
        protected Void call() throws Exception {
            try {
                while (!isCancelled()) { // Task의 isCancelled() 메서드 사용
                    try {
                        if (isStatusChanged()) {
                            _pcscReader.getStatusChange();
                        }
                    } catch (CardNotPresentException ex) {
                        ex.printStackTrace();
                    } catch (CardException ex) {
                        // 카드 예외 처리
                        if (ex.getCause().toString().endsWith("SCARD_E_NO_READERS_AVAILABLE")
                                || ex.getCause().toString().endsWith("SCARD_E_SERVICE_STOPPED")) {
                            // JavaFX UI 업데이트를 위한 메시지
                            updateMessage("Error in polling");
                            return null; // 종료
                        }
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return null;
                    }
                }
                return null;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            // Handle task success (UI updates if necessary)
        }
    }
}