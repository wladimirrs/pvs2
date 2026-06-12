package pvs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ProjektController {

    @FXML private Button btnAendern3;
    @FXML private Button btnEinfuegen3;
    @FXML private Button btnLoeschen3;
    @FXML private Button btnSuchen3;
    @FXML private TextField txtEingabe3;

    @FXML private TextField txtProjektleitungId;
    @FXML private TextField txtProjektId;
    @FXML private TextField txtMitarbeiterId;
    @FXML private TextField txtVon;
    @FXML private TextField txtBis;



    @FXML private TableView<Projekt> tblProjekt;

    @FXML private TableColumn<Projekt, Number> colProjektleitungId;
    @FXML private TableColumn<Projekt, Pro> colProjektId;
    @FXML private TableColumn<Projekt, Mit> colMitarbeiterId;
    @FXML private TableColumn<Projekt, String> colVon;
    @FXML private TableColumn<Projekt, String> colBis;
    private ObservableList<Projekt> daten;


    @FXML
    public void initialize() {
        colProjektleitungId.setCellValueFactory(data -> data.getValue().idProperty());
        colProjektId.setCellValueFactory(data -> data.getValue().proProperty());
        colMitarbeiterId.setCellValueFactory(data -> data.getValue().mitProperty());
        colVon.setCellValueFactory(data -> data.getValue().vonProperty());
        colBis.setCellValueFactory(data -> data.getValue().bisProperty());
        daten = ProjektDAO.getAll();
        tblProjekt.setItems(daten);
    }





    @FXML
    void aendern3(ActionEvent event) {
        int id = Integer.parseInt(txtProjektleitungId.getText());
        int pro = Integer.parseInt(txtProjektId.getText());
        int mit = Integer.parseInt(txtMitarbeiterId.getText());
        if (pro == 0 || mit == 0) {
            return;
        }
        String von = txtVon.getText();
        String bis = txtBis.getText();
        Projekt p = new Projekt(
                id,
                Pro.fromId(pro),
                Mit.fromId(mit),
                von,
                bis
        );
        ProjektDAO.update(p);
        daten.setAll(ProjektDAO.getAll());
    }

    @FXML
    void einfuegen3(ActionEvent event) {
        int pro = Integer.parseInt(txtProjektId.getText());
        int mit = Integer.parseInt(txtMitarbeiterId.getText());
        if (pro == 0 || mit == 0) {
            return;
        }
        String von = txtVon.getText();
        String bis = txtBis.getText();
        Projekt p = new Projekt(
                Pro.fromId(pro),
                Mit.fromId(mit),
                von,
                bis
        );
        ProjektDAO.insert(p);
        daten.setAll(ProjektDAO.getAll());
    }





    @FXML
    void loeschen3(ActionEvent event) {
        Projekt selected = tblProjekt.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ProjektDAO.delete(selected.getId());
            daten.setAll(ProjektDAO.getAll());
        }
    }


    @FXML
    void suchen3(ActionEvent event) {
        String query = txtEingabe3.getText().toLowerCase();
        if (query.isBlank()) {
            tblProjekt.setItems(daten);
            return;
        }
        ObservableList<Projekt> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getPro() != null && t.getPro().toString().contains(query)) ||
                                        (t.getMit() != null && t.getMit().toString().contains(query)) ||
                                        (t.getVon() != null && t.getVon().toString().toLowerCase().contains(query)) ||
                                        (t.getBis() != null && t.getBis().toString().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblProjekt.setItems(gefiltert);
    }


























}
