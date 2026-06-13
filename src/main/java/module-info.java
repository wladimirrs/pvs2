module package1.pvs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens pvs to javafx.fxml, javafx.graphics;
    exports pvs;
    exports Klassen;
    opens Klassen to javafx.fxml, javafx.graphics;
    exports DAO;
    opens DAO to javafx.fxml, javafx.graphics;
    exports Controller;
    opens Controller to javafx.fxml, javafx.graphics;
}