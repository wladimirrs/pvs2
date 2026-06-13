package Klassen;

public class Ort {

    private int id;  // Attribute
    private String plz;
    private String ortsname;

    public Ort(int id, String plz, String ortsname) {    // Konstruktoren
        this.id = id;
        this.plz = plz;
        this.ortsname = ortsname;
    }

    public Ort (String plz, String ortsname) {
        this.plz = plz;
        this.ortsname = ortsname;
    }

    public Ort() {}




    public int getId() {
        return id;
    }   // getter und setter
    public String getPlz() {
        return plz;
    }
    public String getOrtsname() {
        return ortsname;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setPlz(String plz) {
        this.plz = plz;
    }
    public void setOrtsname(String ortsname) {
        this.ortsname = ortsname;
    }


    @Override
    public String toString() {
        return plz + " " + ortsname;
    }

}