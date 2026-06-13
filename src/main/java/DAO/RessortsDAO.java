package DAO;

import Klassen.Ressort;
import pvs.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RessortsDAO {


    public static List<Ressort> getAll() {  // Suche
        List<Ressort> list = new ArrayList<>();
        String sql = "SELECT * FROM ressorts";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ressort r = new Ressort(
                        rs.getInt("id"),
                        rs.getString("bezeichnung")
                );
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM ressorts WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Ressort r) {  // Einfügen
        String sql = "INSERT INTO ressorts (bezeichnung) VALUES (?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getBezeichnung());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Ressort r) {  // Ändern
        String sql = "UPDATE ressorts SET bezeichnung=? WHERE id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getBezeichnung());
            ps.setInt(2, r.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }


    public static Ressort getRessortById(int id) {  // Ressort suchen nach Id
        String sql = "SELECT * FROM ressorts WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Ressort(
                        rs.getInt("id"),
                        rs.getString("bezeichnung")
                );
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche anhand der ID: " + e.getMessage());
        }
        return null;
    }


}