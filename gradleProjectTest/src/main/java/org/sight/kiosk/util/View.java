package org.sight.kiosk.util;

public enum View {

    MAIN_VIEW("/views/main-view"),
    HELLO_VIEW("/views/hello-view"),
    NEW_VIEW("/views/function/radio-view"),
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
