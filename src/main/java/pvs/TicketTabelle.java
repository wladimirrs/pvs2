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

public class TicketTabelle implements Initializable {

    @FXML private TableView<Ticket> tvTicket;
    @FXML private TableColumn<Ticket, Integer> tvId;
    @FXML private TableColumn<Ticket, String> tvGrund;
    @FXML private TableColumn<Ticket, String> tvZeitpunkt;
    @FXML private TableColumn<Ticket, Integer> tvAussteller1;
    @FXML private TableColumn<Ticket, Integer> tvAussteller2;
    @FXML private TableColumn<Ticket, Integer> tvSchuldig;

    ObservableList<Ticket> ticketliste = FXCollections.observableArrayList();

    static StatementsMitarbeiter s1 = new StatementsMitarbeiter();

    public static void refresh() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ticketliste.setAll((Ticket) s1.getAll());

        tvId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvGrund.setCellValueFactory(new PropertyValueFactory<>("grund"));
        tvZeitpunkt.setCellValueFactory(new PropertyValueFactory<>("zeitpunkt"));
        tvAussteller1.setCellValueFactory(new PropertyValueFactory<>("aussteller1"));
        tvAussteller2.setCellValueFactory(new PropertyValueFactory<>("aussteller2"));
        tvSchuldig.setCellValueFactory(new PropertyValueFactory<>("schuldig"));
        tvTicket.setItems(ticketliste);
    }
}