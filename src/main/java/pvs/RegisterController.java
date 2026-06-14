package pvs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class RegisterController {

    @FXML   // Buttons
    private Button btnRegister;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnAusfuehren;

    @FXML
    private Button btnBeenden;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private BorderPane mainPane;



    @FXML private TextField txtEmail;   // Passwortfeld für verdeckte Eingabe
    @FXML private PasswordField txtPasswort;





    @FXML
    void beenden(ActionEvent event) { // App beenden
        System.exit(0);
    }   // Beenden


    @FXML
    void loadLogin(ActionEvent event) { // zum Login
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("LoginView");

        if (view != null) {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }

    @FXML
    void loadRegister(ActionEvent event) {  // Zur Registrierung
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("RegisterView");

        if (view != null) {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);  // Rand
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }





    @FXML
    void loadAusfuehren(ActionEvent event) throws Exception {       // Registrierung durchführen

        boolean ok = NutzerDAO.register(txtEmail.getText(), txtPasswort.getText()); // Email und PW in die Tabelle schreiben
        if (ok) {                                                               // wenn erfolgreich, zum Login weitergeleitet
            ViewLoader viewLoader = new ViewLoader();
            Pane view = viewLoader.loadView("LoginView");
            mainPane.getChildren().clear();
            mainPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }






}
