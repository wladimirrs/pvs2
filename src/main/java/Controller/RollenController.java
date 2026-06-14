package Controller;

import DAO.RessortsDAO;
import DAO.RollenDAO;
import Klassen.Rolle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RollenController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe; // Texteingabe
    @FXML private TextField txtId;
    @FXML private TextField txtBezeichnung;

    @FXML private TableView<Rolle> tblRollen;   // Tabelle
    @FXML private TableColumn<Rolle, Integer> colId;
    @FXML private TableColumn<Rolle, String> colBezeichnung;


    private ObservableList<Rolle> daten;  // befüllt Tabelle
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colBezeichnung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBezeichnung()));
        daten = FXCollections.observableArrayList(RollenDAO.getAll());
        tblRollen.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        int id = Integer.parseInt(txtId.getText());
        String bezeichnung = txtBezeichnung.getText();
        Rolle r = new Rolle(
                id,
                bezeichnung
        );
        RollenDAO.update(r);
        daten.setAll(RollenDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        String bezeichnung = txtBezeichnung.getText();
        Rolle r = new Rolle(
                bezeichnung
        );
        RollenDAO.insert(r);
        daten.setAll(RollenDAO.getAll());
    }





    @FXML
    void loeschen(ActionEvent event) {  // delete
        Rolle selected = tblRollen.getSelectionModel().getSelectedItem();
        if (selected != null) {
            RollenDAO.delete(selected.getId());
            daten.setAll(RollenDAO.getAll());
        }
    }


    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblRollen.setItems(daten);
            return;
        }
        ObservableList<Rolle> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getId() != 0 && String.valueOf(t.getId()).contains(query)) ||
                                        (t.getBezeichnung() != null && t.getBezeichnung().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblRollen.setItems(gefiltert);
    }


    public static Rolle uebergebeRolle(int id) {    // Ressort übergeben
        Rolle r = RollenDAO.getRolleById(id);
        if (r == null) {
            throw new IllegalArgumentException(
                    "Ungültige Rollen-ID: " + id);
        }
        return new Rolle(
                r.getId(),
                r.getBezeichnung()
        );
    }























}
