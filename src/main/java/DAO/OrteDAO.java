package DAO;

import Klassen.Ort;
import pvs.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrteDAO {


    public static List<Ort> getAll() {  // Suche
        List<Ort> list = new ArrayList<>();
        String sql = "SELECT * FROM orte";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ort o = new Ort(
                        rs.getInt("id"),
                        rs.getString("plz"),
                        rs.getString("ortsname")
                );
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM orte WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Ort o) {  // Einfügen
        String sql = "INSERT INTO orte (plz, ortsname) VALUES (?, ?)";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, o.getPlz());
            ps.setString(2, o.getOrtsname());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Ort o) {  // Ändern
        String sql = "UPDATE orte SET plz=?, ortsname=? WHERE id=?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, o.getPlz());
            ps.setString(2, o.getOrtsname());
            ps.setInt(3, o.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }


    public static Ort getOrtById(int id) {  // Ort suchen nach Id
        String sql = "SELECT * FROM orte WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Ort(
                        rs.getInt("id"),
                        rs.getString("plz"),
                        rs.getString("ortsname")
                );
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche anhand der ID: " + e.getMessage());
        }
        return null;
    }




}