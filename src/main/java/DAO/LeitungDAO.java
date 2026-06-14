package DAO;

import Controller.ProjekteController;
import Klassen.Leitung;
import pvs.DB;
import Controller.MitarbeiterController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeitungDAO {


    public static List<Leitung> getAll() {  // Suche
        List<Leitung> list = new ArrayList<>();
        String sql = "SELECT * FROM leitung";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Leitung l = new Leitung(
                        rs.getInt("id"),
                        ProjekteController.uebergebeProjekt(rs.getInt("projekt_id")),
                        MitarbeiterController.uebergebeMitarbeiter(rs.getInt("mitarbeiter_id")),
                        rs.getString("von"),
                        rs.getString("bis")
                );
                list.add(l);
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM leitung WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Leitung l) {  // Einfügen
        String sql = "INSERT INTO leitung (projekt_id, mitarbeiter_id, von, bis) VALUES (?, ?, ?, ?)";
        try (Connection con = DB.getConnection();
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
        try (Connection con = DB.getConnection();
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