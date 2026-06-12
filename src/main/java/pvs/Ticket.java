package pvs;

import javafx.beans.property.*;

public class Ticket {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty grund = new SimpleStringProperty();
    private final StringProperty zeitpunkt = new SimpleStringProperty();
    private ObjectProperty<Aussteller1> aussteller1 = new SimpleObjectProperty<>();
    private ObjectProperty<Aussteller2> aussteller2 = new SimpleObjectProperty<>();
    private ObjectProperty<Schuldig> schuldig = new SimpleObjectProperty<>();


    public Ticket(int id, String grund, String zeitpunkt, Aussteller1 aussteller1, Aussteller2 aussteller2, Schuldig schuldig) {   // Konstruktor
        this.id.set(id);
        this.grund.set(grund);
        this.zeitpunkt.set(zeitpunkt);
        this.aussteller1.set(aussteller1);
        this.aussteller2.set(aussteller2);
        this.schuldig.set(schuldig);
    }

    public Ticket(String grund, String zeitpunkt, Aussteller1 aussteller1, Aussteller2 aussteller2, Schuldig schuldig) {   // Konstruktor
        this.grund.set(grund);
        this.zeitpunkt.set(zeitpunkt);
        this.aussteller1.set(aussteller1);
        this.aussteller2.set(aussteller2);
        this.schuldig.set(schuldig);
    }


    public int getId() {                                            // getter und setter
        return id.get();
    }
    public void setId(int value) {
        id.set(value);
    }
    public IntegerProperty idProperty() {
        return id;
    }

    public String getGrund() {
        return grund.get();
    }
    public void setGrund(String value) {
        grund.set(value);
    }
    public StringProperty grundProperty() {
        return grund;
    }


    public String getZeitpunkt() {
        return zeitpunkt.get();
    }
    public void setZeitpunkt(String value) {
        zeitpunkt.set(value);
    }
    public StringProperty zeitpunktProperty() {
        return zeitpunkt;
    }


    public ObjectProperty<Aussteller1> getAussteller1 () {
        return aussteller1;
    }
    public void setAussteller1 (Aussteller1 value) {
        this.aussteller1.set(value);
    }
    public ObjectProperty<Aussteller1> aussteller1Property() {
        return aussteller1;
    }

    public ObjectProperty<Aussteller2> getAussteller2 () {
        return aussteller2;
    }
    public void setAussteller2 (Aussteller2 value) {
        this.aussteller2.set(value);
    }
    public ObjectProperty<Aussteller2> aussteller2Property() {
        return aussteller2;
    }

    public ObjectProperty<Schuldig> getSchuldig () {
        return schuldig;
    }
    public void setSchuldig (Schuldig value) {
        this.schuldig = schuldig;
    }
    public ObjectProperty<Schuldig> schuldigProperty() {
        return schuldig;
    }

}