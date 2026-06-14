package Controller;

import DAO.BefugnisDAO;
import Klassen.*;
import javafx.beans.property.SimpleObjectProperty;
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

public class BefugnisController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe; // Textfelder
    @FXML private TextField txtMitarbeiterId;
    @FXML private TextField txtProjektId;
    @FXML private TextField txtRolle;

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
        daten = FXCollections.observableArrayList(BefugnisDAO.getAll());
        tblBefugnis.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        int mitarbeiter_id = Integer.parseInt(txtMitarbeiterId.getText());
        int projekt_id = Integer.parseInt(txtProjektId.getText());
        int rolle = Integer.parseInt(txtRolle.getText());
        if (projekt_id == 0 || mitarbeiter_id == 0 ||  rolle == 0) {
            return;
        }
        Befugnis b = new Befugnis(
                MitarbeiterController.uebergebeMitarbeiter(mitarbeiter_id),
                ProjekteController.uebergebeProjekt(projekt_id),
                RollenController.uebergebeRolle(rolle)
        );
        BefugnisDAO.update(b);
        daten.setAll(BefugnisDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        int projekt_id = Integer.parseInt(txtProjektId.getText());
        int rolle = Integer.parseInt(txtRolle.getText());
        if (projekt_id == 0 || rolle == 0) {
            return;
        }
        Befugnis b = new Befugnis(
                ProjekteController.uebergebeProjekt(projekt_id),
                RollenController.uebergebeRolle(rolle)
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
