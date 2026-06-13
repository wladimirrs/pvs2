package Controller;

import DAO.RessortsDAO;
import Klassen.Ressort;
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

public class RessortsController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe; // Texteingabe
    @FXML private TextField txtId;
    @FXML private TextField txtBezeichnung;

    @FXML private TableView<Ressort> tblRessorts;   // Tabelle
    @FXML private TableColumn<Ressort, Integer> colId;
    @FXML private TableColumn<Ressort, String> colBezeichnung;


    private ObservableList<Ressort> daten;  // befüllt Tabelle
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colBezeichnung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBezeichnung()));
        daten = FXCollections.observableArrayList(RessortsDAO.getAll());
        tblRessorts.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        int id = Integer.parseInt(txtId.getText());
        String bezeichnung = txtBezeichnung.getText();
        Ressort r = new Ressort(
                id,
                bezeichnung
        );
        RessortsDAO.update(r);
        daten.setAll(RessortsDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        String bezeichnung = txtBezeichnung.getText();
        Ressort r = new Ressort(
                bezeichnung
        );
        RessortsDAO.insert(r);
        daten.setAll(RessortsDAO.getAll());
    }





    @FXML
    void loeschen(ActionEvent event) {  // delete
        Ressort selected = tblRessorts.getSelectionModel().getSelectedItem();
        if (selected != null) {
            RessortsDAO.delete(selected.getId());
            daten.setAll(RessortsDAO.getAll());
        }
    }


    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblRessorts.setItems(daten);
            return;
        }
        ObservableList<Ressort> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getId() != 0 && String.valueOf(t.getId()).contains(query)) ||
                                (t.getBezeichnung() != null && t.getBezeichnung().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblRessorts.setItems(gefiltert);
    }


    public static Ressort uebergebeRessort(int id) {    // Ressort übergeben
        Ressort r = RessortsDAO.getRessortById(id);
        if (r == null) {
            throw new IllegalArgumentException(
                    "Ungültige Ressort-ID: " + id);
        }
        return new Ressort(
                r.getId(),
                r.getBezeichnung()
        );
    }























}
