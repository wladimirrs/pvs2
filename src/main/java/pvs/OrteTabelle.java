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

public class OrteTabelle implements Initializable {

    @FXML private TableView<Ort> tvOrte;
    @FXML private TableColumn<Ort, Integer> tvId;
    @FXML private TableColumn<Ort, String> tvPlz;
    @FXML private TableColumn<Ort, String> tvOrtsname;

    ObservableList<Ort> ortliste = FXCollections.observableArrayList();

    static StatementsMitarbeiter s1 = new StatementsMitarbeiter();

    public static void refresh() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ortliste.setAll((Ort) s1.getAll());

        tvId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvPlz.setCellValueFactory(new PropertyValueFactory<>("plz"));
        tvOrtsname.setCellValueFactory(new PropertyValueFactory<>("ortsname"));
        tvOrte.setItems(ortliste);
    }
}