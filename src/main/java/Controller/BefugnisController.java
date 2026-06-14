package Controller;

import DAO.*;
import Klassen.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Klassen.Mitarbeiter;

public class BefugnisController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe; // Textfelder
    @FXML private ChoiceBox<Mitarbeiter> cbMitarbeiterId;
    @FXML private ChoiceBox<Projekt> cbProjektId;
    @FXML private ChoiceBox<Rolle> cbRolle;

    @FXML private TableView<Befugnis> tblBefugnis;    // Tabelle
    @FXML private TableColumn<Befugnis, Mitarbeiter> colMitarbeiterId;
    @FXML private TableColumn<Befugnis, Projekt> colProjektId;
    @FXML private TableColumn<Befugnis, Rolle> colRolle;

    private ObservableList<Befugnis> daten;  // Tabelle befüllen
    @FXML
    public void initialize() {
        colMitarbeiterId.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getMitarbeiter_id()));
        colProjektId.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getProjekt_id()));
        colRolle.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getRolle()));
        cbMitarbeiterId.setItems(FXCollections.observableArrayList(MitarbeiterDAO.getAll()));
        cbProjektId.setItems(FXCollections.observableArrayList(ProjekteDAO.getAll()));
        cbRolle.setItems(FXCollections.observableArrayList(RollenDAO.getAll()));
        daten = FXCollections.observableArrayList(BefugnisDAO.getAll());
        tblBefugnis.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        Mitarbeiter mitarbeiter_id = cbMitarbeiterId.getValue();
        Projekt projekt_id = cbProjektId.getValue();
        Rolle rolle = cbRolle.getValue();
        if (projekt_id == null || mitarbeiter_id == null ||  rolle == null) {
            return;
        }
        Befugnis b = new Befugnis(
                mitarbeiter_id,
                projekt_id,
                rolle
        );
        BefugnisDAO.update(b);
        daten.setAll(BefugnisDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        Projekt projekt_id = cbProjektId.getValue();
        Rolle rolle = cbRolle.getValue();
        if (projekt_id == null || rolle == null) {
            return;
        }
        Befugnis b = new Befugnis(
                projekt_id,
                rolle
        );
        BefugnisDAO.insert(b);
        daten.setAll(BefugnisDAO.getAll());
    }





    @FXML
    void loeschen(ActionEvent event) {  // delete
        Befugnis selected = tblBefugnis.getSelectionModel().getSelectedItem();
        if (selected != null) {
            BefugnisDAO.delete(selected.getMitarbeiter_id().getId());
            daten.setAll(BefugnisDAO.getAll());
        }
    }


    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblBefugnis.setItems(daten);
            return;
        }
        ObservableList<Befugnis> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getMitarbeiter_id() != null && String.valueOf(t.getMitarbeiter_id()).contains(query)) ||
                                (t.getProjekt_id() != null && String.valueOf(t.getProjekt_id()).contains(query)) ||
                                        (t.getRolle() != null && String.valueOf(t.getRolle()).contains(query))
                        )
                        .toList()
        );
        tblBefugnis.setItems(gefiltert);
    }



}
