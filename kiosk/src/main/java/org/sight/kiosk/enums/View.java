package org.sight.kiosk.enums;

public enum View {

    MAIN_VIEW("/views/main-view"),
    HELLO_VIEW("/views/hello-view"),
    RFID_VIEW("/views/rfid-view"),
    RADIO_GROUP("/views/function/radio-view"),
    TEXT_FIELD("/views/function/textField-view"),
    COMBO_BOX("/views/function/combo-view"),
    CHECK_BOX("/views/function/check-view"),
    CERTIFICATE_VIEW("/views/manageSetting/certificate-view"),
    APPLICATION1_VIEW("/views/manageSetting/application1-view"),
    APPLICATION2_VIEW("/views/manageSetting/application2-view"),
    SETTING_GROUP("/views/manageSetting/setting-view"),
    ;

    private final String path;

    View(String path){
        this.path = path + ".fxml";
    }

    public String getPath(){
        return path;
    }
}
