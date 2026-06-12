package pvs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StatementsMitarbeiter {

    private final String url = "jdbc:mysql://127.0.0.1:3306/pvs";
    private final String user = "root";
    private final String password = "";




    public static void insert(Mitarbeiter m) {
        String sql = "INSERT INTO mitarbeiter (nachname, vorname, personalnummer, strasse, hausnummer, geburtsdatum, ort, ressort, vertragstyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNachname());
            ps.setString(2, m.getVorname());
            ps.setString(3, m.getPersonalnummer());
            ps.setString(4, m.getStrasse());
            ps.setString(5, m.getHausnummer());
            ps.setString(6, m.getGeburtsdatum());
            ps.setObject(7, m.getOrt());
            ps.setObject(8, m.getRessort());
            ps.setObject(9, m.getVertragstyp());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }








    public static void delete(int id) {
        String sql = "DELETE FROM mitarbeiter WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static void update(Mitarbeiter m) {
        String sql = "UPDATE mitarbeiter SET nachname=?, vorname=?, personallnummer=?, strasse=?, hausnummer=?, geburtsdatum=?, ort=?, ressort=?, vertragstyp=? WHERE id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNachname());
            ps.setString(2, m.getVorname());
            ps.setString(3, m.getPersonalnummer());
            ps.setString(4, m.getStrasse());
            ps.setString(5, m.getHausnummer());
            ps.setString(6, m.getGeburtsdatum());
            ps.setObject(7, m.getOrt());
            ps.setObject(8, m.getRessort());
            ps.setObject(9, m.getVertragstyp());
            ps.setInt(10, m.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    public static ObservableList<Mitarbeiter> getAll() {
        ObservableList<Mitarbeiter> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM mitarbeiter";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Mitarbeiter m = new Mitarbeiter(
                        rs.getInt("id"),
                        rs.getString("nachname"),
                        rs.getString("vorname"),
                        rs.getString("personalnummer"),
                        rs.getString("strasse"),
                        rs.getString("hausnummer"),
                        rs.getString("geburtsdatum"),
                        (Ort) rs.getObject("ort"),
                        (Ressort) rs.getObject("ressort"),
                        (Vertragstyp) rs.getObject("vertragstyp")
                );
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }




}
