package pvs;

import DAO.OrteDAO;
import DAO.RessortsDAO;
import DAO.VertragstypenDAO;
import Klassen.Ort;
import Klassen.Ressort;
import Klassen.Vertragstyp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MitarbeiterController {

    @FXML
    private Button btnAendern;

    @FXML
    private Button btnEinfuegen;

    @FXML
    private Button btnLoeschen;

    @FXML
    private Button btnSuchen;


    @FXML private TableView<Mitarbeiter> tblMitarbeiter;        // Tabelle

    @FXML private TableColumn<Mitarbeiter, Number> colId;             // Tabellenspalten
    @FXML private TableColumn<Mitarbeiter, String> colNachname;
    @FXML private TableColumn<Mitarbeiter, String> colVorname;
    @FXML private TableColumn<Mitarbeiter, String> colPersonallnummer;
    @FXML private TableColumn<Mitarbeiter, String> colStrasse;
    @FXML private TableColumn<Mitarbeiter, String> colHausnummer;
    @FXML private TableColumn<Mitarbeiter, String> colGeburtsdatum;
    @FXML private TableColumn<Mitarbeiter, Ort> colOrt;
    @FXML private TableColumn<Mitarbeiter, Ressort> colRessort;
    @FXML private TableColumn<Mitarbeiter, Vertragstyp> colVertragstyp;

    @FXML private TextField txtEingabe;                         // Eingabefelder
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

    private ObservableList<Mitarbeiter> daten;







    @FXML
    public void initialize() {      // Spalten setzen

        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colNachname.setCellValueFactory(data -> data.getValue().nachnameProperty());
        colVorname.setCellValueFactory(data -> data.getValue().vornameProperty());
        colPersonallnummer.setCellValueFactory(data -> data.getValue().personalnummerProperty());
        colStrasse.setCellValueFactory(data -> data.getValue().strasseProperty());
        colHausnummer.setCellValueFactory(data -> data.getValue().hausnummerProperty());
        colGeburtsdatum.setCellValueFactory(data -> data.getValue().geburtsdatumProperty());
        colOrt.setCellValueFactory(data -> data.getValue().ortProperty());
        colRessort.setCellValueFactory(data -> data.getValue().ressortProperty());
        colVertragstyp.setCellValueFactory(data -> data.getValue().vertragstypProperty());
        daten = MitarbeiterDAO.getAll();
        tblMitarbeiter.setItems(daten);
    }



    @FXML
    void einfuegen(ActionEvent event) {     // Inhalt aus den Eingabefeldern entnehmen
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
                OrteDAO.getOrtById(ort),       // da Foreign Key
                RessortsDAO.getRessortById(ressort),
                MitarbeiterDAO.getByVertragstyp(vertragstyp)
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
    void aendern(ActionEvent event) {
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
                OrteDAO.getOrtById(ort),
                RessortsDAO.getRessortById(ressort),
                MitarbeiterDAO.getByVertragstyp(vertragstyp)
        );
        MitarbeiterDAO.update(m);
        daten.setAll(MitarbeiterDAO.getAll());
    }

    @FXML
    void suchen(ActionEvent event) {
        String query = txtEingabe.getText().toLowerCase();
        if (query.isBlank()) {
            tblMitarbeiter.setItems(daten);
            return;
        }
        ObservableList<Mitarbeiter> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(m ->        // Suche nicht nach id
                                (m.getNachname() != null && m.getNachname().toLowerCase().contains(query)) ||
                                        (m.getVorname() != null && m.getVorname().toLowerCase().contains(query)) ||
                                        (m.getPersonalnummer() != null && m.getPersonalnummer().toLowerCase().contains(query)) ||
                                        (m.getStrasse() != null && m.getStrasse().toLowerCase().contains(query)) ||
                                        (m.getHausnummer() != null && m.getHausnummer().toLowerCase().contains(query)) ||
                                        (m.getGeburtsdatum() != null && m.getGeburtsdatum().toLowerCase().contains(query)) ||
                                        (m.getOrt() != null && m.getOrt().toString().toLowerCase().contains(query)) ||
                                        (m.getRessort() != null && m.getRessort().toString().toLowerCase().contains(query)) ||
                                        (m.getVertragstyp() != null && m.getVertragstyp().toString().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblMitarbeiter.setItems(gefiltert);
    }

    public static Mitarbeiter uebergebeMitarbeiter(int id) {    // Vertragstyp übergeben
        Mitarbeiter m = MitarbeiterDAO.getMitarbeiterById(id);
        if (m == null) {
            throw new IllegalArgumentException(
                    "Ungültige Vertrags-ID: " + id);
        }
        return new Mitarbeiter(
                m.getId(),
                m.getNachname(),
                m.getVorname()
        );
    }


}