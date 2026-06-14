package pvs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class DefaultController {

    @FXML   // Nur Login und Registrierung
    private Button btnRegister;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnBeenden;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private BorderPane mainPane;





    @FXML
    void beenden(ActionEvent event) { // App beenden
        System.exit(0);
    }


    @FXML
    void loadLogin(ActionEvent event) {                     // Zur Loginseite
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
    void loadRegister(ActionEvent event) {                  // Zur Registrierungsseite
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("RegisterView");

        if (view != null) {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }


}
