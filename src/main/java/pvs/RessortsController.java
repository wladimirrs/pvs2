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

public class RessortsController {

    @FXML private Button btnAendern3;
    @FXML private Button btnEinfuegen3;
    @FXML private Button btnLoeschen3;
    @FXML private Button btnSuchen3;
    @FXML private TextField txtEingabe3;

    @FXML private TextField txtId;
    @FXML private TextField txtBezeichnung;



    @FXML private TableView<Ressort> tblRessorts;

    @FXML private TableColumn<Ressort, Integer> colId;
    @FXML private TableColumn<Ressort, String> colBezeichnung;
    private ObservableList<Ressort> daten;


    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colBezeichnung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBezeichnung()));
        daten = RessortsDAO.getAll();
        tblRessorts.setItems(daten);
    }





    @FXML
    void aendern3(ActionEvent event) {
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
    void einfuegen3(ActionEvent event) {
        String bezeichnung = txtBezeichnung.getText();
        Ressort r = new Ressort(
                bezeichnung
        );
        RessortsDAO.insert(r);
        daten.setAll(RessortsDAO.getAll());
    }





    @FXML
    void loeschen3(ActionEvent event) {
        Ressort selected = tblRessorts.getSelectionModel().getSelectedItem();
        if (selected != null) {
            RessortsDAO.delete(selected.getId());
            daten.setAll(RessortsDAO.getAll());
        }
    }


    @FXML
    void suchen3(ActionEvent event) {
        String query = txtEingabe3.getText().toLowerCase();
        if (query.isBlank()) {
            tblRessorts.setItems(daten);
            return;
        }
        ObservableList<Ressort> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getBezeichnung() != null && t.getBezeichnung().toString().contains(query))
                        )
                        .toList()
        );
        tblRessorts.setItems(gefiltert);
    }


























}
