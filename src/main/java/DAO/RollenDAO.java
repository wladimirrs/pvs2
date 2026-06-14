package DAO;

import Klassen.Rolle;
import pvs.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RollenDAO {


    public static List<Rolle> getAll() {  // Suche
        List<Rolle> list = new ArrayList<>();
        String sql = "SELECT * FROM rollen";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Rolle r = new Rolle(
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
        String sql = "DELETE FROM rollen WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Rolle r) {  // Einfügen
        String sql = "INSERT INTO rollen (bezeichnung) VALUES (?)";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getBezeichnung());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Rolle r) {  // Ändern
        String sql = "UPDATE rollen SET bezeichnung=? WHERE id=?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getBezeichnung());
            ps.setInt(2, r.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }


    public static Rolle getRolleById(int id) {  // Ressort suchen nach Id
        String sql = "SELECT * FROM rollen WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Rolle(
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