module org.sight.kiosk.gradleprojecttest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires org.snmp4j;
    requires com.fasterxml.jackson.databind;
    requires java.smartcardio;
//    requires jacob;


    opens org.sight.kiosk to javafx.fxml;
    exports org.sight.kiosk;
    exports org.sight.kiosk.controller;
    opens org.sight.kiosk.controller to javafx.fxml;
    exports org.sight.kiosk.controller.function;
    opens org.sight.kiosk.controller.function to javafx.fxml;
}