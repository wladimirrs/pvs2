package DAO;

import Controller.ProjekteController;
import Klassen.*;
import pvs.DB;
import Controller.MitarbeiterController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeitungDAO {


    public static List<Leitung> getAll() {  // Suche
        List<Leitung> list = new ArrayList<>();
        String sql = "SELECT l.*, " +
                "p.id as p_id, p.bezeichnung , " +
                "m.id as m_id, m.nachname, m.vorname " +
                "FROM leitung l " +
                "LEFT JOIN projekte p ON l.projekt_id = p.id " +
                "LEFT JOIN mitarbeiter m ON l.mitarbeiter_id = m.id ";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Projekt projekt_id = new Projekt(
                        rs.getInt("p_id"),
                        rs.getString("bezeichnung")
                );
                Mitarbeiter mitarbeiter_id = new Mitarbeiter(
                        rs.getInt("m_id"),
                        rs.getString("nachname"),
                        rs.getString("vorname")
                );
                Leitung l = new Leitung(
                        rs.getInt("id"),
                        projekt_id,
                        mitarbeiter_id,
                        rs.getString("von"),
                        rs.getString("bis")
                );
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM leitung WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Leitung l) {  // Einfügen
        String sql = "INSERT INTO leitung (projekt_id, mitarbeiter_id, von, bis) VALUES (?, ?, ?, ?)";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, l.getProjekt_id().getId());
            ps.setInt(2, l.getMitarbeiter_id().getId());
            ps.setString(3, l.getVon());
            ps.setString(4, l.getBis());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Leitung l) {  // Ändern
        String sql = "UPDATE leitung SET projekt_id=?, mitarbeiter_id=?, von=?, bis=? WHERE id=?";
        try (Connection con = DB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, l.getProjekt_id().getId());
            ps.setInt(2, l.getMitarbeiter_id().getId());
            ps.setString(3, l.getVon());
            ps.setString(4, l.getBis());
            ps.setInt(5, l.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }
}