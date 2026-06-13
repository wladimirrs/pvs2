package pvs;

import Klassen.Ort;
import Klassen.Ressort;
import javafx.beans.property.*;

public class Mitarbeiter {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nachname = new SimpleStringProperty();
    private final StringProperty vorname = new SimpleStringProperty();
    private final StringProperty personalnummer = new  SimpleStringProperty();
    private final StringProperty strasse = new SimpleStringProperty();
    private final StringProperty hausnummer = new SimpleStringProperty();
    private final StringProperty geburtsdatum = new  SimpleStringProperty();
    private ObjectProperty<Ort> ort = new SimpleObjectProperty<>();
    private ObjectProperty<Ressort> ressort = new SimpleObjectProperty<>();
    private ObjectProperty<Vertragstyp> vertragstyp = new SimpleObjectProperty<>();
                                                                    // Konstruktor mit id für aendern
    public Mitarbeiter(int id, String nachname, String vorname, String personalnummer, String strasse, String hausnummer, String geburtsdatum, Ort ort, Ressort ressort, Vertragstyp vertragstyp) {   // Konstruktor
        this.id.set(id);
        this.nachname.set(nachname);
        this.vorname.set(vorname);
        this.personalnummer.set(personalnummer);
        this.strasse.set(strasse);
        this.hausnummer.set(hausnummer);
        this.geburtsdatum.set(geburtsdatum);
        this.ort.set(ort);
        this.ressort.set(ressort);
        this.vertragstyp.set(vertragstyp);
    }
                                                                    // Konstruktor ohne id für einfuegen
    public Mitarbeiter(String nachname, String vorname, String personalnummer, String strasse, String hausnummer, String geburtsdatum, Ort ort, Ressort ressort, Vertragstyp vertragstyp) {   // Konstruktor
        this.nachname.set(nachname);
        this.vorname.set(vorname);
        this.personalnummer.set(personalnummer);
        this.strasse.set(strasse);
        this.hausnummer.set(hausnummer);
        this.geburtsdatum.set(geburtsdatum);
        this.ort.set(ort);
        this.ressort.set(ressort);
        this.vertragstyp.set(vertragstyp);
    }

    Mitarbeiter (int id, String nachname, String vorname) {
        this.id.set(id);
        this.nachname.set(nachname);
        this.vorname.set(vorname);
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




    public String getNachname() {
        return nachname.get();
    }
    public void setNachname(String value) {
        nachname.set(value);
    }
    public StringProperty nachnameProperty() {
        return nachname;
    }





    public String getVorname() {
        return vorname.get();
    }
    public void setVorname(String value) {
        vorname.set(value);
    }
    public StringProperty vornameProperty() {
        return vorname;
    }





    public String getPersonalnummer() {
        return personalnummer.get();
    }
    public void setPersonalnummer(String value) {
        personalnummer.set(value);
    }
    public StringProperty personalnummerProperty() {
        return personalnummer;
    }



    public String getStrasse() {
        return strasse.get();
    }
    public void setStrasse(String value) {
        strasse.set(value);
    }
    public StringProperty strasseProperty() {
        return strasse;
    }





    public String getHausnummer() {
        return hausnummer.get();
    }
    public void setHausnummer(String value) {
        hausnummer.set(value);
    }
    public StringProperty hausnummerProperty() {
        return hausnummer;
    }




    public String getGeburtsdatum() {
        return geburtsdatum.get();
    }
    public void setGeburtsdatum(String value) {
        geburtsdatum.set(value);
    }
    public StringProperty geburtsdatumProperty() {
        return geburtsdatum;
    }



    public ObjectProperty<Ort> getOrt () {
        return ort;
    }
    public void setOrt (Ort value) {
        this.ort = ort;
    }
    public ObjectProperty<Ort> ortProperty() {
        return ort;
    }




    public ObjectProperty<Ressort> getRessort() {
        return ressort;
    }
    public void setRessort(Ressort value) {
        this.ressort = ressort;
    }
    public ObjectProperty<Ressort> ressortProperty() {
        return ressort;
    }



    public ObjectProperty<Vertragstyp> getVertragstyp() {
        return vertragstyp;
    }
    public void setVertragstyp(Vertragstyp value) {
        this.vertragstyp = vertragstyp;
    }
    public ObjectProperty<Vertragstyp> vertragstypProperty() {
        return vertragstyp;
    }
    
}