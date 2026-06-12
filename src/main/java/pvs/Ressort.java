package pvs;

public class Ressort {

    private int id;
    private String bezeichnung;

    public Ressort(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public Ressort() {}

    public Ressort (String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }






    public int getId() {
        return id;
    }
    public String getBezeichnung() {
        return bezeichnung;
    }





    public static Ressort fromId(int id) {
        Ressort r = MitarbeiterDAO.getByRessort(id);
        if (r == null) {
            throw new IllegalArgumentException(
                    "Ungültige Aussteller-ID: " + id);
        }
        return new Ressort(
                r.getId(),
                r.getBezeichnung()
        );
    }

    @Override
    public String toString() {
        return bezeichnung;
    }
}