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

public class VertragstypenTabelle implements Initializable {

    @FXML private TableView<Vertragstyp> tvVertragstypen;
    @FXML private TableColumn<Vertragstyp, Integer> tvId;
    @FXML private TableColumn<Vertragstyp, String> tvBezeichnung;

    ObservableList<Vertragstyp> vertragstypenliste = FXCollections.observableArrayList();

    static StatementsMitarbeiter s1 = new StatementsMitarbeiter();

    public static void refresh() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vertragstypenliste.setAll((Vertragstyp) s1.getAll());

        tvId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        tvVertragstypen.setItems(vertragstypenliste);
    }
}