package Controller;

import DAO.TicketDAO;
import Klassen.Ticket;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pvs.Mitarbeiter;
import pvs.MitarbeiterController;

public class TicketController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe;
    @FXML private TextField txtId;  // Textfelder
    @FXML private TextField txtGrund;
    @FXML private TextField txtZeitpunkt;
    @FXML private TextField txtAussteller1;
    @FXML private TextField txtAussteller2;
    @FXML private TextField txtSchuldig;

    @FXML private TableView<Ticket> tblTicket;  // Tabelle
    @FXML private TableColumn<Ticket, Integer> colId;
    @FXML private TableColumn<Ticket, String> colGrund;
    @FXML private TableColumn<Ticket, String> colZeitpunkt;
    @FXML private TableColumn<Ticket, Mitarbeiter> colAussteller1;
    @FXML private TableColumn<Ticket, Mitarbeiter> colAussteller2;
    @FXML private TableColumn<Ticket, Mitarbeiter> colSchuldig;


    private ObservableList<Ticket> daten;   // Tabelle befüllen
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colGrund.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGrund()));
        colZeitpunkt.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getZeitpunkt()));
        colAussteller1.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAussteller1()));
        colAussteller2.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAussteller2()));
        colSchuldig.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getSchuldig()));
        daten = FXCollections.observableArrayList(TicketDAO.getAll());
        tblTicket.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        int id = Integer.parseInt(txtId.getText());
        String grund = txtGrund.getText();
        String zeitpunkt = txtZeitpunkt.getText();
        if (grund == null || grund.isBlank()
                || zeitpunkt == null || zeitpunkt.isBlank()) {
            return;
        }
        int aussteller1 = Integer.parseInt(txtAussteller1.getText());
        int aussteller2 = Integer.parseInt(txtAussteller2.getText());
        int schuldig = Integer.parseInt(txtSchuldig.getText());
        Ticket t = new Ticket(
                id,
                grund,
                zeitpunkt,
                MitarbeiterController.uebergebeMitarbeiter(aussteller1),
                MitarbeiterController.uebergebeMitarbeiter(aussteller2),
                MitarbeiterController.uebergebeMitarbeiter(schuldig)
        );
        TicketDAO.update(t);
        daten.setAll(TicketDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        String grund = txtGrund.getText();
        String zeitpunkt = txtZeitpunkt.getText();
        if (grund == null || grund.isBlank()
                || zeitpunkt == null || zeitpunkt.isBlank()) {
            return;
        }
        int aussteller1 = Integer.parseInt(txtAussteller1.getText());
        int aussteller2 = Integer.parseInt(txtAussteller2.getText());
        int schuldig = Integer.parseInt(txtSchuldig.getText());
        Ticket t = new Ticket(
                grund,
                zeitpunkt,
                MitarbeiterController.uebergebeMitarbeiter(aussteller1),
                MitarbeiterController.uebergebeMitarbeiter(aussteller2),
                MitarbeiterController.uebergebeMitarbeiter(schuldig)
        );
        TicketDAO.insert(t);
        daten.setAll(TicketDAO.getAll());
    }





    @FXML
    void loeschen(ActionEvent event) {  // delete
        Ticket selected = tblTicket.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TicketDAO.delete(selected.getId());
            daten.setAll(TicketDAO.getAll());
        }
    }


    @FXML
    void suchen(ActionEvent event) {   // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblTicket.setItems(daten);
            return;
        }
        ObservableList<Ticket> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getId() != 0 && String.valueOf(t.getId()).contains(query)) ||
                                (t.getGrund() != null && t.getGrund().toLowerCase().contains(query)) ||
                                        (t.getZeitpunkt() != null && t.getZeitpunkt().toLowerCase().contains(query)) ||
                                        (t.getAussteller1() != null && String.valueOf(t.getAussteller1()).contains(query)) ||
                                        (t.getAussteller2() != null && String.valueOf(t.getAussteller2()).contains(query)) ||
                                        (t.getSchuldig() != null && String.valueOf(t.getSchuldig()).contains(query))
                        )
                        .toList()
        );
        tblTicket.setItems(gefiltert);
    }


}
