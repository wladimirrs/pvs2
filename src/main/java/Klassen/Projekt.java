package Klassen;

public class Projekt {

    private int id; // Attribute
    private String bezeichnung;
    private String beginn;
    private String abschluss;
    private String planabschluss;
                                                    // Konstruktoren
    public Projekt(int id, String bezeichnung, String beginn, String abschluss, String planabschluss) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.beginn = beginn;
        this.abschluss = abschluss;
        this.planabschluss = planabschluss;
    }
    public Projekt(String bezeichnung, String beginn, String abschluss, String planabschluss) {
        this.bezeichnung = bezeichnung;
        this.beginn = beginn;
        this.abschluss = abschluss;
        this.planabschluss = planabschluss;
    }

    public Projekt (int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public Projekt() {}





    public int getId() {
        return id;
    }                       // getter und setter
    public String getBezeichnung() {
        return bezeichnung;
    }
    public String getBeginn() {
        return beginn;
    }
    public String getAbschluss() {
        return abschluss;
    }
    public String getPlanabschluss() {
        return planabschluss;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    public void setBeginn(String beginn) {
        this.beginn = beginn;
    }
    public void setAbschluss(String abschluss) {
        this.abschluss = abschluss;
    }
    public void setPlanabschluss(String planabschluss) {
        this.planabschluss = planabschluss;
    }


    @Override
    public String toString() {
        return bezeichnung;
    }

}