package DAO;

import Klassen.Ticket;
import pvs.DB;
import Controller.MitarbeiterController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {


    public static List<Ticket> getAll() {   // Suche
        List<Ticket> list = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ticket t = new Ticket(
                        rs.getInt("id"),
                        rs.getString("grund"),
                        rs.getString("zeitpunkt"),
                        MitarbeiterController.uebergebeMitarbeiter(rs.getInt("aussteller1")),
                        MitarbeiterController.uebergebeMitarbeiter(rs.getInt("aussteller2")),
                        MitarbeiterController.uebergebeMitarbeiter(rs.getInt("schuldig"))
                );
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Ticket t) {   // Einfügen
        String sql = "INSERT INTO tickets (grund, zeitpunkt, aussteller1, aussteller2, schuldig) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getGrund());
            ps.setString(2, t.getZeitpunkt());
            ps.setObject(3, t.getAussteller1());
            ps.setObject(4, t.getAussteller2());
            ps.setObject(5, t.getSchuldig());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Ticket t) {   // Ändern
        String sql = "UPDATE tickets SET grund=?, zeitpunkt=?, aussteller1=?, aussteller2=?, schuldig=? WHERE id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getGrund());
            ps.setString(2, t.getZeitpunkt());
            ps.setObject(3, t.getAussteller1());
            ps.setObject(4, t.getAussteller2());
            ps.setObject(5, t.getSchuldig());
            ps.setInt(6, t.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }


}