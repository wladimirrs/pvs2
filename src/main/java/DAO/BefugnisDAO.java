package DAO;

import Controller.ProjekteController;
import Controller.RollenController;
import Klassen.*;
import pvs.DB;
import Controller.MitarbeiterController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BefugnisDAO {


    public static List<Befugnis> getAll() {  // Suche
        List<Befugnis> list = new ArrayList<>();
        String sql = "SELECT v.*, " +
                "m.id as m_id, m.nachname, m.vorname, " +
                "p.id as p_id, p.bezeichnung as p_bezeichnung, " +
                "r.id as r_id, r.bezeichnung as r_bezeichnung " +
                "FROM mitarbeiter_projekte v " +
                "LEFT JOIN mitarbeiter m ON v.mitarbeiter_id = m.id " +
                "LEFT JOIN projekte p ON v.projekt_id = p.id " +
                "LEFT JOIN rollen r ON v.rolle = r.id";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Mitarbeiter mitarbeiter_id = new Mitarbeiter(
                        rs.getInt("m_id"),
                        rs.getString("nachname"),
                        rs.getString("vorname")
                );
                Projekt projekt_id = new Projekt(
                        rs.getInt("p_id"),
                        rs.getString("p_bezeichnung")
                );
                Rolle rolle = new Rolle(
                        rs.getInt("r_id"),
                        rs.getString("r_bezeichnung")
                );
                Befugnis b = new Befugnis(
                        mitarbeiter_id,
                        projekt_id,
                        rolle
                );
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }



    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM mitarbeiter_projekte WHERE mitarbeiter_id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }

    public static void insert(Befugnis b) {  // Einfügen
        String sql = "INSERT INTO mitarbeiter_projekte (projekt_id, rolle) VALUES (?, ?)";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getProjekt_id().getId());
            ps.setInt(2, b.getRolle().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Befugnis b) {  // Ändern
        String sql = "UPDATE mitarbeiter_projekte SET projekt_id=?, rolle=? WHERE mitarbeiter_id=? AND projekt_id=?";;
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getProjekt_id().getId());
            ps.setInt(2, b.getRolle().getId());
            ps.setInt(3, b.getMitarbeiter_id().getId());
            ps.setInt(4, b.getProjekt_id().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }


}