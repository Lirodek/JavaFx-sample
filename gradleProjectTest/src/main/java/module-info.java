module org.sight.kiosk.gradleprojecttest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.sight.kiosk.gradleprojecttest to javafx.fxml;
    exports org.sight.kiosk.gradleprojecttest;
}