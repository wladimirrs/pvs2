package Controller;

import DAO.OrteDAO;
import Klassen.Ort;
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

public class OrteController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe; // Textfelder
    @FXML private TextField txtId;
    @FXML private TextField txtPlz;
    @FXML private TextField txtOrtsname;

    @FXML private TableView<Ort> tblOrte;   // Tabelle
    @FXML private TableColumn<Ort, Integer> colId;
    @FXML private TableColumn<Ort, String> colPlz;
    @FXML private TableColumn<Ort, String> colOrtsname;


    private ObservableList<Ort> daten;
    @FXML
    public void initialize() {  // legt fest, welche Attribute in welcher Spalte angezeigt werden, lädt Orte in einer Liste und übergibt Daten an die TableView
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colPlz.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlz()));
        colOrtsname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrtsname()));
        daten = FXCollections.observableArrayList(OrteDAO.getAll());
        tblOrte.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        int id = Integer.parseInt(txtId.getText());
        String plz = txtPlz.getText();
        String ortsname = txtOrtsname.getText();
        Ort o = new Ort(
                id,
                plz,
                ortsname
        );
        OrteDAO.update(o);
        daten.setAll(OrteDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        String plz = txtPlz.getText();
        String ortsname = txtOrtsname.getText();
        Ort o = new Ort(
                plz,
                ortsname
        );
        OrteDAO.insert(o);
        daten.setAll(OrteDAO.getAll());
    }





    @FXML
    void loeschen(ActionEvent event) {  // delete
        Ort selected = tblOrte.getSelectionModel().getSelectedItem();
        if (selected != null) {
            OrteDAO.delete(selected.getId());
            daten.setAll(OrteDAO.getAll());
        }
    }


    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {                              // wenn Eingabefeld leer, alle gezeigt
            tblOrte.setItems(daten);
            return;
        }
        ObservableList<Ort> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getId() != 0 && String.valueOf(t.getId()).contains(query)) ||
                                (t.getPlz() != null && t.getPlz().toLowerCase().contains(query)) ||
                                (t.getOrtsname() != null && t.getOrtsname().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblOrte.setItems(gefiltert);
    }


    /*public static Ort uebergebeOrt(int id) {      // für Controller
        Ort o = OrteDAO.getOrtById(id);
        if (o == null) {
            throw new IllegalArgumentException(
                    "Ungültige Ort-ID: " + id);
        }
        return new Ort(
                o.getId(),
                o.getPlz(),
                o.getOrtsname()
        );
    }*/


}
