package DAO;

import Controller.ProjekteController;
import Controller.RollenController;
import Klassen.Befugnis;
import pvs.DB;
import pvs.MitarbeiterController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BefugnisDAO {


    public static List<Befugnis> getAll() {  // Suche
        List<Befugnis> list = new ArrayList<>();
        String sql = "SELECT * FROM mitarbeiter_projekte";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Befugnis b = new Befugnis(
                        MitarbeiterController.uebergebeMitarbeiter(rs.getInt("mitarbeiter_id")),
                        ProjekteController.uebergebeProjekt(rs.getInt("projekt_id")),
                        RollenController.uebergebeRolle(rs.getInt("rolle"))
                );
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int mitarbeiter_id) { // Löschen
        String sql = "DELETE FROM mitarbeiter_projekte WHERE mitarbeiter_id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, mitarbeiter_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Befugnis b) {  // Einfügen
        String sql = "INSERT INTO mitarbeiter_projekte (projekt_id, rolle) VALUES (?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, b.getProjekt_id());
            ps.setObject(2, b.getRolle());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Befugnis b) {  // Ändern
        String sql = "UPDATE mitarbeiter_projekte SET projekt_id=?, rolle=? WHERE mitarbeiter_id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, b.getProjekt_id());
            ps.setObject(2, b.getRolle());
            ps.setObject(3, b.getMitarbeiter_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }


}