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

public class VertragstypenController {

    @FXML private Button btnAendern3;
    @FXML private Button btnEinfuegen3;
    @FXML private Button btnLoeschen3;
    @FXML private Button btnSuchen3;
    @FXML private TextField txtEingabe3;

    @FXML private TextField txtId;
    @FXML private TextField txtBezeichnung;



    @FXML private TableView<Vertragstyp> tblVertragstypen;

    @FXML private TableColumn<Vertragstyp, Integer> colId;
    @FXML private TableColumn<Vertragstyp, String> colBezeichnung;
    private ObservableList<Vertragstyp> daten;


    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colBezeichnung.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBezeichnung()));
        daten = VertragstypenDAO.getAll();
        tblVertragstypen.setItems(daten);
    }





    @FXML
    void aendern3(ActionEvent event) {
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
    void einfuegen3(ActionEvent event) {
        String bezeichnung = txtBezeichnung.getText();
        Vertragstyp v = new Vertragstyp(
                bezeichnung
        );
        VertragstypenDAO.insert(v);
        daten.setAll(VertragstypenDAO.getAll());
    }





    @FXML
    void loeschen3(ActionEvent event) {
        Vertragstyp selected = tblVertragstypen.getSelectionModel().getSelectedItem();
        if (selected != null) {
            VertragstypenDAO.delete(selected.getId());
            daten.setAll(VertragstypenDAO.getAll());
        }
    }


    @FXML
    void suchen3(ActionEvent event) {
        String query = txtEingabe3.getText().toLowerCase();
        if (query.isBlank()) {
            tblVertragstypen.setItems(daten);
            return;
        }
        ObservableList<Vertragstyp> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getBezeichnung() != null && t.getBezeichnung().toString().contains(query))
                        )
                        .toList()
        );
        tblVertragstypen.setItems(gefiltert);
    }


























}
