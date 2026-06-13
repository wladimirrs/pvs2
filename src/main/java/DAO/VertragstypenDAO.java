package DAO;

import Klassen.Vertragstyp;
import pvs.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VertragstypenDAO {


    public static List<Vertragstyp> getAll() {  // Suche
        List<Vertragstyp> list = new ArrayList<>();
        String sql = "SELECT * FROM vertragstypen";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vertragstyp v = new Vertragstyp(
                        rs.getInt("id"),
                        rs.getString("bezeichnung")
                );
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM vertragstypen WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Vertragstyp v) {  // Einfügen
        String sql = "INSERT INTO vertragstypen (bezeichnung) VALUES (?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getBezeichnung());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Vertragstyp v) {  // Ändern
        String sql = "UPDATE vertragstypen SET bezeichnung=? WHERE id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getBezeichnung());
            ps.setInt(2, v.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }

    public static Vertragstyp getByVertragstyp(int id) {    // Vertragstyp suchen nach Id
        String sql = "SELECT * FROM vertragstypen WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Vertragstyp(
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