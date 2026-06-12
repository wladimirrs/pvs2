package pvs;

public class Ort {

    private int ortId;
    private String plz;
    private String ortsname;

    public Ort(int ortId, String plz, String ortsname) {
        this.ortId = ortId;
        this.plz = plz;
        this.ortsname = ortsname;
    }

    public Ort (String plz, String ortsname) {
        this.plz = plz;
        this.ortsname = ortsname;
    }

    public Ort() {}

    public int getId() {
        return ortId;
    }
    public String getPlz() {
        return plz;
    }
    public String getOrtsname() {
        return ortsname;
    }

    public static Ort fromId(int id) {      // für Controller
        Ort o = MitarbeiterDAO.getByOrt(id);
        if (o == null) {
            throw new IllegalArgumentException(
                    "Ungültige Aussteller-ID: " + id);
        }
        return new Ort(
                o.getId(),
                o.getPlz(),
                o.getOrtsname()
        );
    }

    @Override
    public String toString() {
        return plz + " " + ortsname;
    }

}