package pvs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class PvsApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {                 // Im Default-Fenster starten
        FXMLLoader fxmlLoader = new FXMLLoader(PvsApp.class.getResource("/pvs/DefaultView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1150 , 900);
        stage.setResizable(false);
        stage.setTitle("Projektverwaltungssystem");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}