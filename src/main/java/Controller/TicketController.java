package Controller;

import DAO.MitarbeiterDAO;
import DAO.TicketDAO;
import Klassen.Ticket;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Klassen.Mitarbeiter;

public class TicketController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe;
    @FXML private TextField txtId;  // Textfelder
    @FXML private TextField txtGrund;
    @FXML private TextField txtZeitpunkt;
    @FXML private ChoiceBox<Mitarbeiter> cbAussteller1;
    @FXML private ChoiceBox<Mitarbeiter> cbAussteller2;
    @FXML private ChoiceBox<Mitarbeiter> cbSchuldig;

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
        cbAussteller1.setItems(FXCollections.observableArrayList(MitarbeiterDAO.getAll()));
        cbAussteller2.setItems(FXCollections.observableArrayList(MitarbeiterDAO.getAll()));
        cbSchuldig.setItems(FXCollections.observableArrayList(MitarbeiterDAO.getAll()));
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
        Mitarbeiter aussteller1 = cbAussteller1.getValue();
        Mitarbeiter aussteller2 = cbAussteller2.getValue();
        Mitarbeiter schuldig = cbAussteller1.getValue();
        Ticket t = new Ticket(
                id,
                grund,
                zeitpunkt,
                aussteller1,
                aussteller2,
                schuldig
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
        Mitarbeiter aussteller1 = cbAussteller1.getValue();
        Mitarbeiter aussteller2 = cbAussteller2.getValue();
        Mitarbeiter schuldig = cbAussteller1.getValue();
        Ticket t = new Ticket(
                grund,
                zeitpunkt,
                aussteller1,
                aussteller2,
                schuldig
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
