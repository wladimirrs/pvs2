package pvs;

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
import javafx.scene.layout.AnchorPane;

public class ProjektDatenController {

    @FXML private Button btnAendern3;
    @FXML private Button btnEinfuegen3;
    @FXML private Button btnLoeschen3;
    @FXML private Button btnSuchen3;
    @FXML private TextField txtEingabe3;

    @FXML private TextField txtId;
    @FXML private TextField txtBezeichnung;
    @FXML private TextField txtBeginn;
    @FXML private TextField txtAbschluss;
    @FXML private TextField txtPlanabschluss;



    @FXML private TableView<ProjektDaten> tblProjektdaten;

    @FXML private TableColumn<ProjektDaten, Integer> colId;
    @FXML private TableColumn<ProjektDaten, String> colBezeichnung;
    @FXML private TableColumn<ProjektDaten, String> colBeginn;
    @FXML private TableColumn<ProjektDaten, String> colAbschluss;
    @FXML private TableColumn<ProjektDaten, String> colPlanabschluss;
    private ObservableList<ProjektDaten> daten;


    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colBezeichnung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBezeichnung()));
        colBeginn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBeginn()));
        colAbschluss.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAbschluss()));
        colPlanabschluss.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlanabschluss()));
        daten = ProjektDatenDAO.getAll();
        tblProjektdaten.setItems(daten);
    }





    @FXML
    void aendern3(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String bezeichnung = txtBezeichnung.getText();
        String beginn = txtBeginn.getText();
        String abschluss = txtAbschluss.getText();
        String planabschluss = txtPlanabschluss.getText();
        ProjektDaten pp = new ProjektDaten(
                id,
                bezeichnung,
                beginn,
                abschluss,
                planabschluss
        );
        ProjektDatenDAO.update(pp);
        daten.setAll(ProjektDatenDAO.getAll());
    }

    @FXML
    void einfuegen3(ActionEvent event) {
        String bezeichnung = txtBezeichnung.getText();
        String beginn = txtBeginn.getText();
        String abschluss = txtAbschluss.getText();
        String planabschluss = txtPlanabschluss.getText();
        ProjektDaten pp = new ProjektDaten(
                bezeichnung,
                beginn,
                abschluss,
                planabschluss
        );
        ProjektDatenDAO.insert(pp);
        daten.setAll(ProjektDatenDAO.getAll());
    }





    @FXML
    void loeschen3(ActionEvent event) {
        ProjektDaten selected = tblProjektdaten.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ProjektDatenDAO.delete(selected.getId());
            daten.setAll(ProjektDatenDAO.getAll());
        }
    }


    @FXML
    void suchen3(ActionEvent event) {
        String query = txtEingabe3.getText().toLowerCase();
        if (query.isBlank()) {
            tblProjektdaten.setItems(daten);
            return;
        }
        ObservableList<ProjektDaten> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getBezeichnung() != null && t.getBezeichnung().toString().contains(query)) ||
                                        (t.getBeginn() != null && t.getBeginn().toString().contains(query)) ||
                                        (t.getAbschluss() != null && t.getAbschluss().toString().contains(query)) ||
                                        (t.getPlanabschluss() != null && t.getPlanabschluss().toString().contains(query))
                        )
                        .toList()
        );
        tblProjektdaten.setItems(gefiltert);
    }


























}
