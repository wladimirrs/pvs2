package Klassen;

import pvs.Mitarbeiter;

public class Leitung {

    int id; // Attribute
    Projekt projekt_id;
    Mitarbeiter mitarbeiter_id;
    String von;
    String bis;


    public Leitung(int id, Projekt projekt_id, Mitarbeiter mitarbeiter_id, String von, String bis) {   // Konstruktoren
        this.id = id;
        this.projekt_id = projekt_id;
        this.mitarbeiter_id = mitarbeiter_id;
        this.von = von;
        this.bis = bis;
    }

    public Leitung(Projekt projekt_id, Mitarbeiter mitarbeiter_id, String von, String bis) {
        this.projekt_id = projekt_id;
        this.mitarbeiter_id = mitarbeiter_id;
        this.von = von;
        this.bis = bis;
    }

    public Leitung () { }


    public int getId() {                    // getter und setter
        return id;
    }
    public Projekt getProjekt_id() {
        return projekt_id;
    }
    public Mitarbeiter getMitarbeiter_id() {
        return mitarbeiter_id;
    }
    public String getVon() {
        return von;
    }
    public String getBis() {
        return bis;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setProjekt_id(Projekt projekt_id) {
        this.projekt_id = projekt_id;
    }
    public void setMitarbeiter_id(Mitarbeiter mitarbeiter_id) {
        this.mitarbeiter_id = mitarbeiter_id;
    }
    public void setVon(String von) {
        this.von = von;
    }
    public void setBis(String bis) {
        this.bis = bis;
    }



}