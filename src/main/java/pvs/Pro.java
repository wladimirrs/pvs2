package pvs;

public class Pro {

    private int id;
    private String bezeichnung;

    public Pro(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public Pro() {}





    public int getId() {
        return id;
    }
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }



    public static Pro fromId(int id) {
        AlleProjekte a = MitarbeiterDAO.getByProjekt(id);
        if (a == null) {
            throw new IllegalArgumentException(
                    "Ungültige Aussteller-ID: " + id);
        }
        return new Pro(
                a.getId(),
                a.getBezeichnung()
        );
    }




    @Override
    public String toString() {
        return id + ": " + bezeichnung;
    }
}