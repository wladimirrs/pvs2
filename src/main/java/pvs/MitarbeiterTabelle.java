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

public class MitarbeiterTabelle {/*

    @FXML private TableView<Mitarbeiter> tvMitarbeiter;
    @FXML private TableColumn<Mitarbeiter, Integer> tvId;
    @FXML private TableColumn<Mitarbeiter, String> tvNachname;
    @FXML private TableColumn<Mitarbeiter, String> tvVorname;
    @FXML private TableColumn<Mitarbeiter, String> tvPersonalnummer;
    @FXML private TableColumn<Mitarbeiter, String> tvStrasse;
    @FXML private TableColumn<Mitarbeiter, String> tvHausnummer;
    @FXML private TableColumn<Mitarbeiter, String> tvGeburtsdatum;
    @FXML private TableColumn<Mitarbeiter, Ort> tvOrt;
    @FXML private TableColumn<Mitarbeiter, Ressort> tvRessort;
    @FXML private TableColumn<Mitarbeiter, Vertragstyp> tvVertragstyp;
    ObservableList<Mitarbeiter> mitarbeiterliste = FXCollections.observableArrayList();

    static StatementsMitarbeiter s1 = new StatementsMitarbeiter();

    public static void refresh() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mitarbeiterliste.setAll(s1.getAll());

        tvId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        tvVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        tvPersonalnummer.setCellValueFactory(new PropertyValueFactory<>("personalnummer"));
        tvStrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
        tvHausnummer.setCellValueFactory(new PropertyValueFactory<>("hausnummer"));
        tvGeburtsdatum.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
        tvOrt.setCellValueFactory(new PropertyValueFactory<>("ort"));
        tvRessort.setCellValueFactory(new PropertyValueFactory<>("ressort"));
        tvVertragstyp.setCellValueFactory(new PropertyValueFactory<>("vertragstyp"));
        tvMitarbeiter.setItems(mitarbeiterliste);
    }*/
}