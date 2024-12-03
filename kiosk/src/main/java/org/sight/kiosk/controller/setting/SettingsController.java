package org.sight.kiosk.controller.setting;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class SettingsController {

    @FXML
    private CheckBox certificateA11, certificateA22, certificateBB;
    @FXML
    private CheckBox reason1, reason2, reason3, reason4, reason5;
    @FXML
    private CheckBox destination1, destination2, destination3, destination4, destination5;
    @FXML
    private CheckBox infoA, infoB;
    @FXML
    private Button saveSettingsButton, downloadSettingButton, importSettingButton;

    // settings.properties 경로 설정
    private static final String PROPERTIES_FILE = "src/main/resources/settings/setting.properties";
    // USB 경로
    private static final String ENCRYPTED_FILE = "F:/encrypted_setting.properties";
    // 16바이트 키 (암호화/복호화에 사용)
    private static final String SECRET_KEY = "1234567890123456";


    // 설정정보 저장
    @FXML
    public void onSaveSettings() {
        saveSettingsButton.setOnAction(event -> saveSettingsToFile());
    }

    // 암호화된 설정파일 다운로드
    @FXML
    public void onDownloadSettingButtonClick() {
        downloadSettingButton.setOnAction(event -> downloadEncryptedSettingFile());
    }

    //
    @FXML
    public void onImportSettingButtonClick() {
        importSettingButton.setOnAction(event -> downloadDecryptSettingFile());
    }

    private void saveSettingsToFile() {
        // Properties 객체 생성
        Properties properties = new Properties();

        // 증명서 종류
        StringBuilder certificateTypes = new StringBuilder();
        if (certificateA11.isSelected()) certificateTypes.append("A-1,");
        if (certificateA22.isSelected()) certificateTypes.append("A-2,");
        if (certificateBB.isSelected()) certificateTypes.append("B,");
        if (certificateTypes.length() > 0) {
            certificateTypes.setLength(certificateTypes.length() - 1); // 마지막 콤마 제거
        }
        properties.setProperty("certificate.types", certificateTypes.toString());

        // 발급 사유
        StringBuilder issuanceReasons = new StringBuilder();
        if (reason1.isSelected()) issuanceReasons.append("발급사유1,");
        if (reason2.isSelected()) issuanceReasons.append("발급사유2,");
        if (reason3.isSelected()) issuanceReasons.append("발급사유3,");
        if (reason4.isSelected()) issuanceReasons.append("발급사유4,");
        if (reason5.isSelected()) issuanceReasons.append("발급사유5,");
        if (issuanceReasons.length() > 0) {
            issuanceReasons.setLength(issuanceReasons.length() - 1); // 마지막 콤마 제거
        }
        properties.setProperty("issuance.reasons", issuanceReasons.toString());

        // 제출처
        StringBuilder submissionDestinations = new StringBuilder();
        if (destination1.isSelected()) submissionDestinations.append("제출처1,");
        if (destination2.isSelected()) submissionDestinations.append("제출처2,");
        if (destination3.isSelected()) submissionDestinations.append("제출처3,");
        if (destination4.isSelected()) submissionDestinations.append("제출처4,");
        if (destination5.isSelected()) submissionDestinations.append("제출처5,");
        if (submissionDestinations.length() > 0) {
            submissionDestinations.setLength(submissionDestinations.length() - 1); // 마지막 콤마 제거
        }
        properties.setProperty("submission.destinations", submissionDestinations.toString());

        // 발급 정보
        StringBuilder issuanceInfo = new StringBuilder();
        if (infoA.isSelected()) issuanceInfo.append("A,");
        if (infoB.isSelected()) issuanceInfo.append("B,");
        if (issuanceInfo.length() > 0) {
            issuanceInfo.setLength(issuanceInfo.length() - 1); // 마지막 콤마 제거
        }
        properties.setProperty("issuance.info", issuanceInfo.toString());

        // 설정 저장
        try (FileOutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
            properties.store(output, "Kiosk Settings");
            System.out.println("설정정보 저장 성공");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadEncryptedSettingFile() {
        try {
            // Properties 파일을 읽어 내용 암호화
            byte[] encryptedData = encryptFile(PROPERTIES_FILE);

            // 암호화된 데이터 파일로 저장
            try (FileOutputStream output = new FileOutputStream(ENCRYPTED_FILE)) {
                output.write(encryptedData);
                System.out.println("설정정보 다운로드 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 파일 전체를 암호화하는 메서드
    private byte[] encryptFile(String filePath) throws Exception {
        // 파일을 읽어서 바이트 배열로 변환
        FileInputStream fileInputStream = new FileInputStream(filePath);
        byte[] fileData = fileInputStream.readAllBytes();
        fileInputStream.close();

        // 암호화 키 생성
        SecretKeySpec secretKey = generateSecretKey();

        // AES 암호화
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(fileData);

        return encryptedData;
    }

    // AES 암호화 키 생성
    private SecretKeySpec generateSecretKey() {
        String secretKey = SECRET_KEY; // 16바이트 길이의 고정 키 (보안을 위해 변경 필요)
        return new SecretKeySpec(secretKey.getBytes(), "AES");
    }

    // 암호화된 파일을 복호화하여 설정파일로 저장하는 메서드
    private void downloadDecryptSettingFile() {
        try {
            // 1. USB에서 암호화된 파일 읽기
            byte[] encryptedData = Files.readAllBytes(Paths.get(ENCRYPTED_FILE));

            // 2. 암호화된 파일 복호화
            byte[] decryptedData = decryptFile(encryptedData);

            // 3. 복호화된 데이터가 설정파일로 저장
            try (FileOutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
                output.write(decryptedData);
                System.out.println("설정파일이 성공적으로 복원되었습니다.");
            }

        } catch (IOException e) {
            // IOException은 별도로 처리
            e.printStackTrace();
        } catch (Exception e) {
            // 일반적인 예외 처리
            e.printStackTrace();
        }
    }

    // 파일을 복호화하는 메서드
    private byte[] decryptFile(byte[] encryptedData) throws Exception {
        // 암호화된 데이터를 복호화하기 위한 키 생성
        SecretKeySpec secretKey = generateSecretKey();

        // AES 복호화
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedData);
    }
}