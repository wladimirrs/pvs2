package Controller;

import DAO.ProjekteDAO;
import DAO.RessortsDAO;
import Klassen.Projekt;
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

public class ProjekteController {

    @FXML private Button btnAendern;    // Buttons
    @FXML private Button btnEinfuegen;
    @FXML private Button btnLoeschen;
    @FXML private Button btnSuchen;

    @FXML private TextField txtEingabe;    // Textfelder
    @FXML private TextField txtId;
    @FXML private TextField txtBezeichnung;
    @FXML private TextField txtBeginn;
    @FXML private TextField txtAbschluss;
    @FXML private TextField txtPlanabschluss;

    @FXML private TableView<Projekt> tblProjekte;   // Tabelle
    @FXML private TableColumn<Projekt, Integer> colId;
    @FXML private TableColumn<Projekt, String> colBezeichnung;
    @FXML private TableColumn<Projekt, String> colBeginn;
    @FXML private TableColumn<Projekt, String> colAbschluss;
    @FXML private TableColumn<Projekt, String> colPlanabschluss;


    private ObservableList<Projekt> daten;  // Tabelle befüllen
    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colBezeichnung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBezeichnung()));
        colBeginn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBeginn()));
        colAbschluss.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAbschluss()));
        colPlanabschluss.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlanabschluss()));
        daten = FXCollections.observableArrayList(ProjekteDAO.getAll());
        tblProjekte.setItems(daten);
    }





    @FXML
    void aendern(ActionEvent event) {   // update
        int id = Integer.parseInt(txtId.getText());
        String bezeichnung = txtBezeichnung.getText();
        String beginn = txtBeginn.getText();
        String abschluss = txtAbschluss.getText();
        String planabschluss = txtPlanabschluss.getText();
        Projekt pp = new Projekt(
                id,
                bezeichnung,
                beginn,
                abschluss,
                planabschluss
        );
        ProjekteDAO.update(pp);
        daten.setAll(ProjekteDAO.getAll());
    }

    @FXML
    void einfuegen(ActionEvent event) { // insert
        String bezeichnung = txtBezeichnung.getText();
        String beginn = txtBeginn.getText();
        String abschluss = txtAbschluss.getText();
        String planabschluss = txtPlanabschluss.getText();
        Projekt pp = new Projekt(
                bezeichnung,
                beginn,
                abschluss,
                planabschluss
        );
        ProjekteDAO.insert(pp);
        daten.setAll(ProjekteDAO.getAll());
    }





    @FXML
    void loeschen(ActionEvent event) { // delete
        Projekt selected = tblProjekte.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ProjekteDAO.delete(selected.getId());
            daten.setAll(ProjekteDAO.getAll());
        }
    }


    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblProjekte.setItems(daten);
            return;
        }
        ObservableList<Projekt> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getId() != 0 && String.valueOf(t.getId()).contains(query)) ||
                                (t.getBezeichnung() != null && t.getBezeichnung().toLowerCase().contains(query)) ||
                                        (t.getBeginn() != null && t.getBeginn().toLowerCase().contains(query)) ||
                                        (t.getAbschluss() != null && t.getAbschluss().toLowerCase().contains(query)) ||
                                        (t.getPlanabschluss() != null && t.getPlanabschluss().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblProjekte.setItems(gefiltert);
    }


    public static Projekt uebergebeProjekt(int id) {    // Projekt übergeben
        Projekt p = ProjekteDAO.getProjektById(id);
        if (p == null) {
            throw new IllegalArgumentException(
                    "Ungültige Ressort-ID: " + id);
        }
        return new Projekt(
                p.getId(),
                p.getBezeichnung()
        );
    }

























}
