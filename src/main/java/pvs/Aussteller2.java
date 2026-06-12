package pvs;

public class Aussteller2 {

    private int id;
    private String nachname;
    private String vorname;

    public Aussteller2(int id, String nachname, String vorname) {
        this.id = id;
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public Aussteller2() {}





    public int getId() {
        return id;
    }
    public String getNachname() {
        return nachname;
    }
    public String getVorname() {
        return vorname;
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




    public static Aussteller2 fromId(int id) {  // feste Aussteller
        return switch (id) {
            case 12 -> new Aussteller2(12, "Walder", "Jessica");
            case 13 -> new Aussteller2(13, "Hegenbergh", "Hendrik");
            default -> throw new IllegalArgumentException("Ungültige Aussteller-ID: " + id);
        };
    }

    @Override
    public String toString() {
        return nachname + " " + vorname;
    }
}