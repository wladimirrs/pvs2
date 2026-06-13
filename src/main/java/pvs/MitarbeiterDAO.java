package pvs;

import Controller.OrteController;
import Controller.RessortsController;
import Controller.VertragstypenController;
import Klassen.Vertragstyp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MitarbeiterDAO {


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
                        OrteController.uebergebeOrt(rs.getInt("ort")),
                        RessortsController.uebergebeRessort(rs.getInt("ressort")),
                        VertragstypenController.uebergebeVertragstyp(rs.getInt("vertragstyp"))
                );
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
            ps.setInt(7, m.getOrt().get().getId());
            ps.setInt(8, m.getRessort().get().getId());
            ps.setInt(9, m.getVertragstyp().get().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void update(Mitarbeiter m) {
        String sql = "UPDATE mitarbeiter SET nachname=?, vorname=?, personalnummer=?, strasse=?, hausnummer=?, geburtsdatum=?, ort=?, ressort=?, vertragstyp=? WHERE id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNachname());
            ps.setString(2, m.getVorname());
            ps.setString(3, m.getPersonalnummer());
            ps.setString(4, m.getStrasse());
            ps.setString(5, m.getHausnummer());
            ps.setString(6, m.getGeburtsdatum());
            ps.setInt(7, m.getOrt().get().getId());
            ps.setInt(8, m.getRessort().get().getId());
            ps.setInt(9, m.getVertragstyp().get().getId());
            ps.setInt(10, m.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static Mitarbeiter getMitarbeiterById(int id) {    // Vertragstyp suchen nach Id
        String sql = "SELECT * FROM vertragstypen WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Mitarbeiter(
                        rs.getInt("id"),
                        rs.getString("nachname"),
                        rs.getString("vorname")
                );
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche anhand der ID: " + e.getMessage());
        }
        return null;
    }





}