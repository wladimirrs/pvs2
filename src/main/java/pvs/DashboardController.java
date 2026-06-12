package pvs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class DashboardController {

    @FXML
    private Button btnBeenden;  // Logout-Button und Beenden-Knopf bei anderen fenstern übernommen

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnHome;     // Navigation

    @FXML
    private Button btnMitarbeiter;

    @FXML
    private Button btnTickets;

    @FXML
    private Button btnProjekte;

    @FXML
    private Button btnOrte;

    @FXML
    private Button btnRessorts;

    @FXML
    private Button btnVertragstypen;

    @FXML
    private Button btnProjektdaten;

    @FXML                       // Fenster content und main (Mitte und oben)
    private AnchorPane contentPane;

    @FXML
    private BorderPane mainPane;





    @FXML
    void beenden(ActionEvent event) { // App beenden
        System.exit(0);
    }

    @FXML
    void loadLogout(ActionEvent event) {
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("DefaultView"); // Beim Logout zurück zum DefaultView
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);

        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);
        AnchorPane.setRightAnchor(view, 0.0);
    }

    @FXML
    void loadHome(ActionEvent event) { // Dashboard laden
        ViewLoader viewLoader = new ViewLoader();
        Pane view = viewLoader.loadView("Dashboard");
        mainPane.getChildren().clear();
        mainPane.getChildren().add(view);
    }

    @FXML
    void loadMitarbeiter(ActionEvent event) { // Mitarbeiter laden
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("MitarbeiterView");

        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }

    @FXML
    void loadTickets(ActionEvent event) { // Tickets laden
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("TicketView");

        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);  // Rand
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }



    @FXML
    void loadProjekte(ActionEvent event) { // Projekthistorie laden
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("ProjektView");

        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }







    @FXML
    void loadOrte(ActionEvent event) { // Orte laden
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("OrteView");

        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }


    @FXML
    void loadRessorts(ActionEvent event) { // Ressorts laden
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("RessortsView");

        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }




    @FXML
    void loadVertragstypen(ActionEvent event) { // Verträge laden
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("VertragstypenView");

        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }




    @FXML
    void loadProjektdaten(ActionEvent event) { // Projekte laden
        ViewLoader loader = new ViewLoader();
        Pane view = loader.loadView("ProjektdatenView");

        if (view != null) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        }
    }


}
