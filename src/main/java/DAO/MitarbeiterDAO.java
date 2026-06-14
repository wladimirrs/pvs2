package DAO;

import Controller.OrteController;
import Controller.RessortsController;
import Controller.VertragstypenController;
import Klassen.Mitarbeiter;
import Klassen.Ort;
import Klassen.Ressort;
import Klassen.Vertragstyp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pvs.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterDAO {


    public static List<Mitarbeiter> getAll() {
        List<Mitarbeiter> list = new ArrayList<>();
        String sql = "SELECT m.*, " +
                        "o.id as o_id, o.plz, o.ortsname, " +
                        "r.id as r_id, r.bezeichnung as r_bezeichnung, " +
                        "v.id as v_id, v.bezeichnung as v_bezeichnung " +
                        "FROM mitarbeiter m " +
                        "LEFT JOIN orte o ON m.ort = o.id " +
                        "LEFT JOIN ressorts r ON m.ressort = r.id " +
                        "LEFT JOIN vertragstypen v ON m.vertragstyp = v.id";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ort ort = new Ort(
                        rs.getInt("o_id"),
                        rs.getString("plz"),
                        rs.getString("ortsname")
                );
                Ressort ressort = new Ressort(
                        rs.getInt("r_id"),
                        rs.getString("r_bezeichnung")
                );
                Vertragstyp vertragstyp = new Vertragstyp(
                        rs.getInt("v_id"),
                        rs.getString("v_bezeichnung")
                );
                Mitarbeiter m = new Mitarbeiter(
                        rs.getInt("id"),
                        rs.getString("nachname"),
                        rs.getString("vorname"),
                        rs.getString("personalnummer"),
                        rs.getString("strasse"),
                        rs.getString("hausnummer"),
                        rs.getString("geburtsdatum"),
                        ort,
                        ressort,
                        vertragstyp
                );
                list.add(m);
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM mitarbeiter WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Mitarbeiter m) {  // Einfügen
        String sql = "INSERT INTO mitarbeiter (nachname, vorname, personalnummer, strasse, hausnummer, geburtsdatum, ort, ressort, vertragstyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNachname());
            ps.setString(2, m.getVorname());
            ps.setString(3, m.getPersonalnummer());
            ps.setString(4, m.getStrasse());
            ps.setString(5, m.getHausnummer());
            ps.setString(6, m.getGeburtsdatum());
            ps.setInt(7, m.getOrt().getId());
            ps.setInt(8, m.getRessort().getId());
            ps.setInt(9, m.getVertragstyp().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Mitarbeiter m) {  // Ändern
        String sql = "UPDATE mitarbeiter SET nachname=?, vorname=?, personalnummer=?, strasse=?, hausnummer=?, geburtsdatum=?, ort=?, ressort=?, vertragstyp=? WHERE id=?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNachname());
            ps.setString(2, m.getVorname());
            ps.setString(3, m.getPersonalnummer());
            ps.setString(4, m.getStrasse());
            ps.setString(5, m.getHausnummer());
            ps.setString(6, m.getGeburtsdatum());
            ps.setInt(7, m.getOrt().getId());
            ps.setInt(8, m.getRessort().getId());
            ps.setInt(9, m.getVertragstyp().getId());
            ps.setInt(10, m.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }



    public static Mitarbeiter getMitarbeiterById(int id) {    // Vertragstyp suchen nach Id
        String sql = "SELECT * FROM mitarbeiter WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
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