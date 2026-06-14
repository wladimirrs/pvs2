package Controller;

import DAO.LeitungDAO;
import Klassen.Leitung;
import Klassen.Projekt;
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

public class LeitungController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe; // Textfelder
    @FXML private TextField txtId;
    @FXML private TextField txtProjektId;
    @FXML private TextField txtMitarbeiterId;
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
        daten = FXCollections.observableArrayList(LeitungDAO.getAll());
        tblLeitung.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        int id = Integer.parseInt(txtId.getText());
        int projekt_id = Integer.parseInt(txtProjektId.getText());
        int mitarbeiter_id = Integer.parseInt(txtMitarbeiterId.getText());
        if (projekt_id == 0 || mitarbeiter_id == 0) {
            return;
        }
        String von = txtVon.getText();
        String bis = txtBis.getText();
        Leitung p = new Leitung(
                id,
                ProjekteController.uebergebeProjekt(projekt_id),
                MitarbeiterController.uebergebeMitarbeiter(mitarbeiter_id),
                von,
                bis
        );
        LeitungDAO.update(p);
        daten.setAll(LeitungDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        int projekt_id = Integer.parseInt(txtProjektId.getText());
        int mitarbeiter_id = Integer.parseInt(txtMitarbeiterId.getText());
        if (projekt_id == 0 || mitarbeiter_id == 0) {
            return;
        }
        String von = txtVon.getText();
        String bis = txtBis.getText();
        Leitung l = new Leitung(
                ProjekteController.uebergebeProjekt(projekt_id),
                MitarbeiterController.uebergebeMitarbeiter(mitarbeiter_id),
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
