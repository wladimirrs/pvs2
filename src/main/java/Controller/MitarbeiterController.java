package Controller;

import DAO.*;
import Klassen.Mitarbeiter;
import Klassen.Ort;
import Klassen.Ressort;
import Klassen.Vertragstyp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MitarbeiterController {

    @FXML                       // Buttons
    private Button btnAendern;
    @FXML
    private Button btnEinfuegen;
    @FXML
    private Button btnLoeschen;
    @FXML
    private Button btnSuchen;

    @FXML private TextField txtEingabe;     // Eingabefelder
    @FXML private TextField txtId;
    @FXML private TextField txtNachname;
    @FXML private TextField txtVorname;
    @FXML private TextField txtPersonalnummer;
    @FXML private TextField txtStrasse;
    @FXML private TextField txtHausnummer;
    @FXML private TextField txtGeburtsdatum;
    @FXML private TextField txtOrt;
    @FXML private TextField txtRessort;
    @FXML private TextField txtVertragstyp;

    @FXML private TableView<Mitarbeiter> tblMitarbeiter;        // Tabelle
    @FXML private TableColumn<Mitarbeiter, Integer> colId;
    @FXML private TableColumn<Mitarbeiter, String> colNachname;
    @FXML private TableColumn<Mitarbeiter, String> colVorname;
    @FXML private TableColumn<Mitarbeiter, String> colPersonallnummer;
    @FXML private TableColumn<Mitarbeiter, String> colStrasse;
    @FXML private TableColumn<Mitarbeiter, String> colHausnummer;
    @FXML private TableColumn<Mitarbeiter, String> colGeburtsdatum;
    @FXML private TableColumn<Mitarbeiter, Ort> colOrt;
    @FXML private TableColumn<Mitarbeiter, Ressort> colRessort;
    @FXML private TableColumn<Mitarbeiter, Vertragstyp> colVertragstyp;

    private ObservableList<Mitarbeiter> daten;  // Tabelle befüllen
    @FXML
    public void initialize() {      // Spalten setzen
        colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        colNachname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNachname()));
        colVorname.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVorname()));
        colPersonallnummer.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPersonalnummer()));
        colStrasse.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStrasse()));
        colHausnummer.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHausnummer()));
        colGeburtsdatum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGeburtsdatum()));
        colOrt.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getOrt()));
        colRessort.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getRessort()));
        colVertragstyp.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getVertragstyp()));
        daten = FXCollections.observableArrayList(MitarbeiterDAO.getAll());
        tblMitarbeiter.setItems(daten);
    }



    @FXML
    void einfuegen(ActionEvent event) {     // insert
        String nachname = txtNachname.getText();
        String vorname = txtVorname.getText();
        String personalnummer = txtPersonalnummer.getText();
        String strasse = txtStrasse.getText();
        String hausnummer = txtHausnummer.getText();
        String geburtsdatum = txtGeburtsdatum.getText();
        int ort = Integer.parseInt(txtOrt.getText());
        int ressort = Integer.parseInt(txtRessort.getText());
        int vertragstyp = Integer.parseInt(txtVertragstyp.getText());
        if (nachname == null || vorname == null || nachname.isEmpty() || vorname.isEmpty()) {
            return;
        }
        Mitarbeiter m = new Mitarbeiter(
                nachname,
                vorname,
                personalnummer,
                strasse,
                hausnummer,
                geburtsdatum,
                OrteController.uebergebeOrt(ort),       // da Foreign Key
                RessortsController.uebergebeRessort(ressort),
                VertragstypenController.uebergebeVertragstyp(vertragstyp)
        );
        MitarbeiterDAO.insert(m);
        daten.setAll(MitarbeiterDAO.getAll());
    }

    @FXML
    void loeschen(ActionEvent event) {              // gewählten Datensatz löschen
        Mitarbeiter selected = tblMitarbeiter.getSelectionModel().getSelectedItem();
        if (selected != null) {
            MitarbeiterDAO.delete(selected.getId());
            daten.setAll(MitarbeiterDAO.getAll());
        }
    }

    @FXML
    void aendern(ActionEvent event) {   // update
        int id = Integer.parseInt(txtId.getText());
        String nachname = txtNachname.getText();
        String vorname = txtVorname.getText();
        String personalnummer = txtPersonalnummer.getText();
        String strasse = txtStrasse.getText();
        String hausnummer = txtHausnummer.getText();
        String geburtsdatum = txtGeburtsdatum.getText();
        int ort = Integer.parseInt(txtOrt.getText());
        int ressort = Integer.parseInt(txtRessort.getText());
        int vertragstyp = Integer.parseInt(txtVertragstyp.getText());
        if (nachname == null || vorname == null || nachname.isEmpty() || vorname.isEmpty()) {
            return;
        }
        Mitarbeiter m = new Mitarbeiter(
                id,
                nachname,
                vorname,
                personalnummer,
                strasse,
                hausnummer,
                geburtsdatum,
                OrteController.uebergebeOrt(ort),
                RessortsController.uebergebeRessort(ressort),
                VertragstypenController.uebergebeVertragstyp(vertragstyp)
        );
        MitarbeiterDAO.update(m);
        daten.setAll(MitarbeiterDAO.getAll());
    }

    @FXML
    void suchen(ActionEvent event) {    // Suche
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblMitarbeiter.setItems(daten);
            return;
        }
        ObservableList<Mitarbeiter> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(m ->
                                (m.getId() != 0 && String.valueOf(m.getId()).contains(query)) ||
                                (m.getNachname() != null && m.getNachname().toLowerCase().contains(query)) ||
                                        (m.getVorname() != null && m.getVorname().toLowerCase().contains(query)) ||
                                        (m.getPersonalnummer() != null && m.getPersonalnummer().toLowerCase().contains(query)) ||
                                        (m.getStrasse() != null && m.getStrasse().toLowerCase().contains(query)) ||
                                        (m.getHausnummer() != null && m.getHausnummer().toLowerCase().contains(query)) ||
                                        (m.getGeburtsdatum() != null && m.getGeburtsdatum().toLowerCase().contains(query)) ||
                                        (m.getOrt() != null && String.valueOf(m.getOrt()).contains(query)) ||
                                        (m.getRessort() != null && String.valueOf(m.getRessort()).contains(query)) ||
                                        (m.getVertragstyp() != null && String.valueOf(m.getVertragstyp()).contains(query))
                        )
                        .toList()
        );
        tblMitarbeiter.setItems(gefiltert);
    }

    public static Mitarbeiter uebergebeMitarbeiter(int id) {    // Mitarbeiter übergeben
        Mitarbeiter m = MitarbeiterDAO.getMitarbeiterById(id);
        if (m == null) {
            throw new IllegalArgumentException(
                    "Ungültige Mitarbeiter-ID: " + id);
        }
        return new Mitarbeiter(
                m.getId(),
                m.getNachname(),
                m.getVorname()
        );
    }


}