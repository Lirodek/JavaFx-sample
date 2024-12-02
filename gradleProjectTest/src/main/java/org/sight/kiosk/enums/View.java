package org.sight.kiosk.enums;

public enum View {

    MAIN_VIEW("/views/main-view"),
    HELLO_VIEW("/views/hello-view"),
    RFID_VIEW("/views/rfid-view"),
    RADIO_GROUP("/views/function/radio-view"),
    TEXT_FIELD("/views/function/textField-view"),
    COMBO_BOX("/views/function/combo-view"),
    CHECK_BOX("/views/function/check-view"),
    ;

    private final String path;

    View(String path){
        this.path = path + ".fxml";
    }

    public String getPath(){
        return path;
    }
}
