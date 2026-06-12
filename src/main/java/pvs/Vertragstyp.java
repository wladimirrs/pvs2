package pvs;

public class Vertragstyp {

    private int id;
    private String bezeichnung;

    public Vertragstyp(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public Vertragstyp() {}

    public Vertragstyp (String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }



    public int getId() {
        return id;
    }
    public String getBezeichnung() {
        return bezeichnung;
    }



    public static Vertragstyp fromId(int id) {
        Vertragstyp v = MitarbeiterDAO.getByVertragstyp(id);
        if (v == null) {
            throw new IllegalArgumentException(
                    "Ungültige Aussteller-ID: " + id);
        }
        return new Vertragstyp(
                v.getId(),
                v.getBezeichnung()
        );
    }

    @Override
    public String toString() {
        return bezeichnung;
    }
}