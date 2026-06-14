package Klassen;

public class Befugnis {

    Mitarbeiter mitarbeiter_id; // Attribute
    Projekt projekt_id;
    Rolle rolle;


    public Befugnis(Mitarbeiter mitarbeiter_id, Projekt projekt_id, Rolle rolle) {   // Konstruktoren
        this.mitarbeiter_id = mitarbeiter_id;
        this.projekt_id = projekt_id;
        this.rolle = rolle;
    }

    public Befugnis(Projekt projekt_id, Rolle rolle) {
        this.projekt_id = projekt_id;
        this.rolle = rolle;
    }

    public Befugnis () { }



    public Mitarbeiter getMitarbeiter_id() {    // getter und setter
        return mitarbeiter_id;
    }

    public Projekt getProjekt_id() {
        return projekt_id;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setMitarbeiter_id(Mitarbeiter mitarbeiter_id) {
        this.mitarbeiter_id = mitarbeiter_id;
    }
    public void setProjekt_id(Projekt projekt_id) {
        this.projekt_id = projekt_id;
    }
    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }
}