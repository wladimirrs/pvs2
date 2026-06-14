package Klassen;

import javafx.beans.property.*;

public class Mitarbeiter {

    int id; // Attribute
    String nachname;
    String vorname;
    String personalnummer;
    String strasse;
    String hausnummer;
    String geburtsdatum;
    Ort ort;
    Ressort ressort;
    Vertragstyp vertragstyp;
                                                                    // Konstruktoren
    public Mitarbeiter(int id, String nachname, String vorname, String personalnummer, String strasse, String hausnummer, String geburtsdatum, Ort ort, Ressort ressort, Vertragstyp vertragstyp) {
        this.id = id;
        this.nachname = nachname;
        this.vorname = vorname;
        this.personalnummer = personalnummer;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.geburtsdatum = geburtsdatum;
        this.ort = ort;
        this.ressort = ressort;
        this.vertragstyp = vertragstyp;
    }

    public Mitarbeiter(String nachname, String vorname, String personalnummer, String strasse, String hausnummer, String geburtsdatum, Ort ort, Ressort ressort, Vertragstyp vertragstyp) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.personalnummer = personalnummer;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.geburtsdatum = geburtsdatum;
        this.ort = ort;
        this.ressort = ressort;
        this.vertragstyp = vertragstyp;
    }

    public Mitarbeiter(int id, String nachname, String vorname) {
        this.id = id;
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public Mitarbeiter () {}





    public int getId() {    // getter und setter
        return id;
    }

    public String getNachname() {
        return nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public String getPersonalnummer() {
        return personalnummer;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public Ort getOrt() {
        return ort;
    }

    public Ressort getRessort() {
        return ressort;
    }

    public Vertragstyp getVertragstyp() {
        return vertragstyp;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setPersonalnummer(String personalnummer) {
        this.personalnummer = personalnummer;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public void setOrt(Ort ort) {
        this.ort = ort;
    }

    public void setRessort(Ressort ressort) {
        this.ressort = ressort;
    }

    public void setVertragstyp(Vertragstyp vertragstyp) {
        this.vertragstyp = vertragstyp;
    }


    @Override
    public String toString() {
        return id + ": " + nachname + " " + vorname;
    }

}