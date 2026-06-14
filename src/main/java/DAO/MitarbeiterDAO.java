package DAO;

import Controller.OrteController;
import Controller.RessortsController;
import Controller.VertragstypenController;
import Klassen.Mitarbeiter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pvs.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterDAO {


    public static List<Mitarbeiter> getAll() {  // Suche
        List<Mitarbeiter> list = new ArrayList<>();
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
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM mitarbeiter WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Mitarbeiter m) {  // Einfügen
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
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Mitarbeiter m) {  // Ändern
        String sql = "UPDATE mitarbeiter SET nachname=?, vorname=?, personalnummer=?, strasse=?, hausnummer=?, geburtsdatum=?, ort=?, ressort=?, vertragstyp=? WHERE id=?";
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
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }



    public static Mitarbeiter getMitarbeiterById(int id) {    // Vertragstyp suchen nach Id
        String sql = "SELECT * FROM mitarbeiter WHERE id = ?";
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