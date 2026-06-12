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

public class OrteController {

    @FXML private Button btnAendern3;
    @FXML private Button btnEinfuegen3;
    @FXML private Button btnLoeschen3;
    @FXML private Button btnSuchen3;
    @FXML private TextField txtEingabe3;

    @FXML private TextField txtId;
    @FXML private TextField txtPlz;
    @FXML private TextField txtOrtsname;



    @FXML private TableView<Ort> tblOrte;

    @FXML private TableColumn<Ort, Integer> colId;
    @FXML private TableColumn<Ort, String> colPlz;
    @FXML private TableColumn<Ort, String> colOrtsname;
    private ObservableList<Ort> daten;


    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colPlz.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlz()));
        colOrtsname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrtsname()));
        daten = OrteDAO.getAll();
        tblOrte.setItems(daten);
    }





    @FXML
    void aendern3(ActionEvent event) {
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
    void einfuegen3(ActionEvent event) {
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
    void loeschen3(ActionEvent event) {
        Ort selected = tblOrte.getSelectionModel().getSelectedItem();
        if (selected != null) {
            OrteDAO.delete(selected.getId());
            daten.setAll(OrteDAO.getAll());
        }
    }


    @FXML
    void suchen3(ActionEvent event) {
        String query = txtEingabe3.getText().toLowerCase();
        if (query.isBlank()) {
            tblOrte.setItems(daten);
            return;
        }
        ObservableList<Ort> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getPlz() != null && t.getPlz().toString().contains(query)) ||
                                        (t.getOrtsname() != null && t.getOrtsname().toString().contains(query))
                        )
                        .toList()
        );
        tblOrte.setItems(gefiltert);
    }


























}
