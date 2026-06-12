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

public class RessortsTabelle implements Initializable {

    @FXML private TableView<Ressort> tvRessorts;
    @FXML private TableColumn<Ressort, Integer> tvId;
    @FXML private TableColumn<Ressort, String> tvBezeichnung;

    ObservableList<Ressort> ressortliste = FXCollections.observableArrayList();

    static StatementsMitarbeiter s1 = new StatementsMitarbeiter();

    public static void refresh() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ressortliste.setAll((Ressort) s1.getAll());

        tvId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        tvRessorts.setItems(ressortliste);
    }
}