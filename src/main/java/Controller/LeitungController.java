package Controller;

import DAO.LeitungDAO;
import DAO.MitarbeiterDAO;
import DAO.ProjekteDAO;
import DAO.RollenDAO;
import Klassen.Leitung;
import Klassen.Ort;
import Klassen.Projekt;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Klassen.Mitarbeiter;

public class LeitungController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe; // Textfelder
    @FXML private TextField txtId;
    @FXML private ChoiceBox<Projekt> cbProjektId;
    @FXML private ChoiceBox<Mitarbeiter> cbMitarbeiterId;
    @FXML private TextField txtVon;
    @FXML private TextField txtBis;

    @FXML private TableView<Leitung> tblLeitung;    // Tabelle
    @FXML private TableColumn<Leitung, Integer> colId;
    @FXML private TableColumn<Leitung, Projekt> colProjektId;
    @FXML private TableColumn<Leitung, Mitarbeiter> colMitarbeiterId;
    @FXML private TableColumn<Leitung, String> colVon;
    @FXML private TableColumn<Leitung, String> colBis;

    private ObservableList<Leitung> daten;  // Tabelle befüllen
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colProjektId.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getProjekt_id()));
        colMitarbeiterId.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getMitarbeiter_id()));
        colVon.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVon()));
        colBis.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBis()));
        cbProjektId.setItems(FXCollections.observableArrayList(ProjekteDAO.getAll()));
        cbMitarbeiterId.setItems(FXCollections.observableArrayList(MitarbeiterDAO.getAll()));
        daten = FXCollections.observableArrayList(LeitungDAO.getAll());
        tblLeitung.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        int id = Integer.parseInt(txtId.getText());
        Projekt projekt_id = cbProjektId.getValue();
        Mitarbeiter mitarbeiter_id = cbMitarbeiterId.getValue();
        if (projekt_id == null || mitarbeiter_id == null) {
            return;
        }
        String von = txtVon.getText();
        String bis = txtBis.getText();
        Leitung p = new Leitung(
                id,
                projekt_id,
                mitarbeiter_id,
                von,
                bis
        );
        LeitungDAO.update(p);
        daten.setAll(LeitungDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        Projekt projekt_id = cbProjektId.getValue();
        Mitarbeiter mitarbeiter_id = cbMitarbeiterId.getValue();
        if (projekt_id == null || mitarbeiter_id == null) {
            return;
        }
        String von = txtVon.getText();
        String bis = txtBis.getText();
        Leitung l = new Leitung(
                projekt_id,
                mitarbeiter_id,
                von,
                bis
        );
        LeitungDAO.insert(l);
        daten.setAll(LeitungDAO.getAll());
    }





    @FXML
    void loeschen(ActionEvent event) {  // delete
        Leitung selected = tblLeitung.getSelectionModel().getSelectedItem();
        if (selected != null) {
            LeitungDAO.delete(selected.getId());
            daten.setAll(LeitungDAO.getAll());
        }
    }


    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblLeitung.setItems(daten);
            return;
        }
        ObservableList<Leitung> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getId() != 0 && String.valueOf(t.getId()).contains(query)) ||
                                        (t.getProjekt_id() != null && String.valueOf(t.getProjekt_id()).contains(query)) ||
                                        (t.getMitarbeiter_id() != null && String.valueOf(t.getMitarbeiter_id()).contains(query)) ||
                                        (t.getVon() != null && t.getVon().toLowerCase().contains(query)) ||
                                        (t.getBis() != null && t.getBis().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblLeitung.setItems(gefiltert);
    }



}
