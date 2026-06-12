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

public class TicketController {

    @FXML private Button btnAendern2;
    @FXML private Button btnEinfuegen2;
    @FXML private Button btnLoeschen2;
    @FXML private Button btnSuchen2;
    @FXML private TextField txtEingabe2;

    @FXML private TextField txtId;
    @FXML private TextField txtGrund;
    @FXML private TextField txtZeitpunkt;
    @FXML private TextField txtAussteller1;
    @FXML private TextField txtAussteller2;
    @FXML private TextField txtSchuldig;



    @FXML private TableView<Ticket> tblTicket;

    @FXML private TableColumn<Ticket, Number> colId;
    @FXML private TableColumn<Ticket, String> colGrund;
    @FXML private TableColumn<Ticket, String> colZeitpunkt;
    @FXML private TableColumn<Ticket, Aussteller1> colAussteller1;
    @FXML private TableColumn<Ticket, Aussteller2> colAussteller2;
    @FXML private TableColumn<Ticket, Schuldig> colSchuldig;
    private ObservableList<Ticket> daten;


    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colGrund.setCellValueFactory(data -> data.getValue().grundProperty());
        colZeitpunkt.setCellValueFactory(data -> data.getValue().zeitpunktProperty());
        colAussteller1.setCellValueFactory(data -> data.getValue().aussteller1Property());
        colAussteller2.setCellValueFactory(data -> data.getValue().aussteller2Property());
        colSchuldig.setCellValueFactory(data -> data.getValue().schuldigProperty());
        daten = TicketDAO.getAll();
        tblTicket.setItems(daten);
    }





    @FXML
    void aendern2(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String grund = txtGrund.getText();
        String zeitpunkt = txtZeitpunkt.getText();
        if (grund == null || grund.isBlank()
                || zeitpunkt == null || zeitpunkt.isBlank()) {
            return;
        }
        int aussteller1Id = Integer.parseInt(txtAussteller1.getText());
        int aussteller2Id = Integer.parseInt(txtAussteller2.getText());
        int schuldigId = Integer.parseInt(txtSchuldig.getText());
        Ticket t = new Ticket(
                id,
                grund,
                zeitpunkt,
                Aussteller1.fromId(aussteller1Id),
                Aussteller2.fromId(aussteller2Id),
                Schuldig.fromId(schuldigId)
        );
        TicketDAO.update(t);
        daten.setAll(TicketDAO.getAll());
    }

    @FXML
    void einfuegen2(ActionEvent event) {
        String grund = txtGrund.getText();
        String zeitpunkt = txtZeitpunkt.getText();
        if (grund == null || grund.isBlank()
                || zeitpunkt == null || zeitpunkt.isBlank()) {
            return;
        }
        int aussteller1Id = Integer.parseInt(txtAussteller1.getText());
        int aussteller2Id = Integer.parseInt(txtAussteller2.getText());
        int schuldigId = Integer.parseInt(txtSchuldig.getText());
        Ticket t = new Ticket(
                grund,
                zeitpunkt,
                Aussteller1.fromId(aussteller1Id),
                Aussteller2.fromId(aussteller2Id),
                Schuldig.fromId(schuldigId)
        );
        TicketDAO.insert(t);
        daten.setAll(TicketDAO.getAll());
    }





    @FXML
    void loeschen2(ActionEvent event) {
        Ticket selected = tblTicket.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TicketDAO.delete(selected.getId());
            daten.setAll(TicketDAO.getAll());
        }
    }


    @FXML
    void suchen2(ActionEvent event) {
        String query = txtEingabe2.getText().toLowerCase();
        if (query.isBlank()) {
            tblTicket.setItems(daten);
            return;
        }
        ObservableList<Ticket> gefiltert = FXCollections.observableArrayList(
                daten.stream()
                        .filter(t ->
                                (t.getGrund() != null && t.getGrund().toLowerCase().contains(query)) ||
                                        (t.getZeitpunkt() != null && t.getZeitpunkt().toLowerCase().contains(query)) ||
                                        (t.getAussteller1() != null && t.getAussteller1().toString().toLowerCase().contains(query)) ||
                                        (t.getAussteller2() != null && t.getAussteller2().toString().toLowerCase().contains(query)) ||
                                        (t.getSchuldig() != null && t.getSchuldig().toString().toLowerCase().contains(query))
                        )
                        .toList()
        );
        tblTicket.setItems(gefiltert);
    }


























}
