package pvs;

public class ProjektDaten {

    private int id;
    private String bezeichnung;
    private String beginn;
    private String abschluss;
    private String planabschluss;

    public ProjektDaten(int id, String bezeichnung, String beginn, String abschluss, String planabschluss) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.beginn = beginn;
        this.abschluss = abschluss;
        this.planabschluss = planabschluss;
    }
    public ProjektDaten(String bezeichnung, String beginn, String abschluss, String planabschluss) {
        this.bezeichnung = bezeichnung;
        this.beginn = beginn;
        this.abschluss = abschluss;
        this.planabschluss = planabschluss;
    }

    public ProjektDaten() {}





    public int getId() {
        return id;
    }
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


    @Override
    public String toString() {
        return bezeichnung;
    }

}