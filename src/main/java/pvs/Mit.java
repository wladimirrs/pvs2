package pvs;

public class Mit {

    private int id;
    private String nachname;
    private String vorname;

    public Mit(int id, String nachname, String vorname) {
        this.id = id;
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public Mit() {}





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



    public static Mit fromId(int id) {
        Mitarbeiter m = MitarbeiterDAO.getById(id);
        if (m == null) {
            throw new IllegalArgumentException(
                    "Ungültige Aussteller-ID: " + id);
        }
        return new Mit(
                m.getId(),
                m.getNachname(),
                m.getVorname()
        );
    }




    @Override
    public String toString() {
        return id + ": " + nachname + " " + vorname;
    }
}