package pvs;

import javafx.beans.property.*;

public class Projekt {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private ObjectProperty<Pro> pro = new SimpleObjectProperty<>();
    private ObjectProperty<Mit> mit = new SimpleObjectProperty<>();
    private final StringProperty von = new SimpleStringProperty();
    private final StringProperty bis = new SimpleStringProperty();


    public Projekt(int id, Pro pro, Mit mit, String von, String bis) {   // Konstruktor
        this.id.set(id);
        this.pro.set(pro);
        this.mit.set(mit);
        this.von.set(von);
        this.bis.set(bis);
    }

    public Projekt(Pro pro, Mit mit, String von, String bis) {   // Konstruktor
        this.pro.set(pro);
        this.mit.set(mit);
        this.von.set(von);
        this.bis.set(bis);
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



    public ObjectProperty<Pro> getPro () {
        return pro;
    }
    public void setPro (Pro value) {
        this.pro.set(value);
    }
    public ObjectProperty<Pro> proProperty() {
        return pro;
    }



    public ObjectProperty<Mit> getMit () {
        return mit;
    }
    public void setMit (Mit value) {
        this.mit.set(value);
    }
    public ObjectProperty<Mit> mitProperty() {
        return mit;
    }






    public String getVon() {
        return von.get();
    }
    public void setVon(String value) {
        von.set(value);
    }
    public StringProperty vonProperty() {
        return von;
    }


    public String getBis() {
        return bis.get();
    }
    public void setBis(String value) {
        bis.set(value);
    }
    public StringProperty bisProperty() {
        return bis;
    }






}