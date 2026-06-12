module package1.pvs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens pvs to javafx.fxml, javafx.graphics;
    exports pvs;
}