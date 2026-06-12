package pvs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjektTabelle implements Initializable {

    @FXML private TableView<Projekt> tvProjekt;
    @FXML private TableColumn<Projekt, Integer> tvId;
    @FXML private TableColumn<Projekt, Integer> tvPro;
    @FXML private TableColumn<Projekt, Integer> tvMit;
    @FXML private TableColumn<Projekt, String> tvVon;
    @FXML private TableColumn<Projekt, String> tvBis;

    ObservableList<Projekt> projektliste = FXCollections.observableArrayList();

    static StatementsMitarbeiter s1 = new StatementsMitarbeiter();

    public static void refresh() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projektliste.setAll((Projekt) s1.getAll());

        tvId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvPro.setCellValueFactory(new PropertyValueFactory<>("pro"));
        tvMit.setCellValueFactory(new PropertyValueFactory<>("mit"));
        tvVon.setCellValueFactory(new PropertyValueFactory<>("von"));
        tvBis.setCellValueFactory(new PropertyValueFactory<>("bis"));
        tvProjekt.setItems(projektliste);
    }
}