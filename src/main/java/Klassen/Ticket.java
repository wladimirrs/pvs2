package Klassen;

public class Ticket {

    int id; // Attribute
    String grund;
    String zeitpunkt;
    Mitarbeiter aussteller1;
    Mitarbeiter aussteller2;
    Mitarbeiter schuldig;

                                                // Konstruktoren
    public Ticket(int id, String grund, String zeitpunkt, Mitarbeiter aussteller1, Mitarbeiter aussteller2, Mitarbeiter schuldig) {   // Konstruktor
        this.id = id;
        this.grund = grund;
        this.zeitpunkt = zeitpunkt;
        this.aussteller1 = aussteller1;
        this.aussteller2 = aussteller2;
        this.schuldig = schuldig;
    }

    public Ticket(String grund, String zeitpunkt, Mitarbeiter aussteller1, Mitarbeiter aussteller2, Mitarbeiter schuldig) {   // Konstruktor
        this.grund = grund;
        this.zeitpunkt = zeitpunkt;
        this.aussteller1 = aussteller1;
        this.aussteller2 = aussteller2;
        this.schuldig = schuldig;
    }

    public Ticket() {}





    public int getId() {    // getter und setter
        return id;
    }

    public String getGrund() {
        return grund;
    }

    public String getZeitpunkt() {
        return zeitpunkt;
    }

    public Mitarbeiter getAussteller1() {
        return aussteller1;
    }

    public Mitarbeiter getAussteller2() {
        return aussteller2;
    }

    public Mitarbeiter getSchuldig() {
        return schuldig;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }

    public void setZeitpunkt(String zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }

    public void setAussteller1(Mitarbeiter aussteller1) {
        this.aussteller1 = aussteller1;
    }

    public void setAussteller2(Mitarbeiter aussteller2) {
        this.aussteller2 = aussteller2;
    }

    public void setSchuldig(Mitarbeiter schuldig) {
        this.schuldig = schuldig;
    }



}