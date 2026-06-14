package Controller;

import DAO.VertragstypenDAO;
import Klassen.Vertragstyp;
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

public class VertragstypenController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe; // Eingabefelder
    @FXML private TextField txtId;
    @FXML private TextField txtBezeichnung;

    @FXML private TableView<Vertragstyp> tblVertragstypen;  // Tabelle
    @FXML private TableColumn<Vertragstyp, Integer> colId;
    @FXML private TableColumn<Vertragstyp, String> colBezeichnung;

    private ObservableList<Vertragstyp> daten;  // Tabelle befüllen
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colBezeichnung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBezeichnung()));
        daten = FXCollections.observableArrayList(VertragstypenDAO.getAll());
        tblVertragstypen.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        int id = Integer.parseInt(txtId.getText());
        String bezeichnung = txtBezeichnung.getText();
        Vertragstyp v = new Vertragstyp(
                id,
                bezeichnung
        );
        VertragstypenDAO.update(v);
        daten.setAll(VertragstypenDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        String bezeichnung = txtBezeichnung.getText();
        Vertragstyp v = new Vertragstyp(
                bezeichnung
        );
        VertragstypenDAO.insert(v);
        daten.setAll(VertragstypenDAO.getAll());
    }





    @FXML
    void loeschen(ActionEvent event) {  // delete
        Vertragstyp selected = tblVertragstypen.getSelectionModel().getSelectedItem();
        if (selected != null) {
            VertragstypenDAO.delete(selected.getId());
            daten.setAll(VertragstypenDAO.getAll());
        }
    }


    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblVertragstypen.setItems(daten);
            return;
        }
        ObservableList<Vertragstyp> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getId() != 0 && String.valueOf(t.getId()).contains(query)) ||
                                (t.getBezeichnung() != null && t.getBezeichnung().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblVertragstypen.setItems(gefiltert);
    }


    /*public static Vertragstyp uebergebeVertragstyp(int id) {    // Vertragstyp übergeben
        Vertragstyp v = VertragstypenDAO.getVertragstypById(id);
        if (v == null) {
            throw new IllegalArgumentException(
                    "Ungültige Vertrags-ID: " + id);
        }
        return new Vertragstyp(
                v.getId(),
                v.getBezeichnung()
        );
    }*/

}
