package Klassen;

public class Rolle {

    private int id; // Attribute
    private String bezeichnung;

    public Rolle(int id, String bezeichnung) {    // Konstruktoren
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public Rolle() {}
    public Rolle (String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }






    public int getId() {
        return id;
    }   // getter und setter
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String toString() {
        return bezeichnung;
    }
}