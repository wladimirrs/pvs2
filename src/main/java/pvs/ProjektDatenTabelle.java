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

public class ProjektDatenTabelle implements Initializable {

    @FXML private TableView<ProjektDaten> tvProjektdaten;
    @FXML private TableColumn<ProjektDaten, Integer> tvId;
    @FXML private TableColumn<ProjektDaten, String> tvBezeichnung;
    @FXML private TableColumn<ProjektDaten, String> tvBeginn;
    @FXML private TableColumn<ProjektDaten, String> tvAbschluss;
    @FXML private TableColumn<ProjektDaten, String> tvPlanabschluss;

    ObservableList<ProjektDaten> pdliste = FXCollections.observableArrayList();

    static StatementsMitarbeiter s1 = new StatementsMitarbeiter();

    public static void refresh() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pdliste.setAll((ProjektDaten) s1.getAll());

        tvId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        tvBeginn.setCellValueFactory(new PropertyValueFactory<>("beginn"));
        tvAbschluss.setCellValueFactory(new PropertyValueFactory<>("abschluss"));
        tvPlanabschluss.setCellValueFactory(new PropertyValueFactory<>("planabschluss"));
        tvProjektdaten.setItems(pdliste);
    }
}