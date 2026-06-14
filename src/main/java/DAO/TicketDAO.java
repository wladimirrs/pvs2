package DAO;

import Klassen.Leitung;
import Klassen.Mitarbeiter;
import Klassen.Projekt;
import Klassen.Ticket;
import pvs.DB;
import Controller.MitarbeiterController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {


    public static List<Ticket> getAll() {   // Suche
        List<Ticket> list = new ArrayList<>();
        String sql = "SELECT t.*, " +
                "m1.id as m_id1, m1.nachname as m_nachname1, m1.vorname as m_vorname1, " +
                "m2.id as m_id2, m2.nachname as m_nachname2, m2.vorname as m_vorname2, " +
                "m3.id as m_id3, m3.nachname as m_nachname3, m3.vorname as m_vorname3 " +
                "FROM tickets t " +
                "LEFT JOIN mitarbeiter m1 ON t.aussteller1 = m1.id " +
                "LEFT JOIN mitarbeiter m2 ON t.aussteller2 = m2.id " +
                "LEFT JOIN mitarbeiter m3 ON t.schuldig = m3.id ";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Mitarbeiter aussteller1 = new Mitarbeiter(
                        rs.getInt("m_id1"),
                        rs.getString("m_nachname1"),
                        rs.getString("m_vorname1")
                );
                Mitarbeiter aussteller2 = new Mitarbeiter(
                        rs.getInt("m_id2"),
                        rs.getString("m_nachname2"),
                        rs.getString("m_vorname2")
                );
                Mitarbeiter schuldig = new Mitarbeiter(
                        rs.getInt("m_id3"),
                        rs.getString("m_nachname3"),
                        rs.getString("m_vorname3")
                );
                Ticket t = new Ticket(
                        rs.getInt("id"),
                        rs.getString("grund"),
                        rs.getString("zeitpunkt"),
                        aussteller1,
                        aussteller2,
                        schuldig
                );
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Ticket t) {   // Einfügen
        String sql = "INSERT INTO tickets (grund, zeitpunkt, aussteller1, aussteller2, schuldig) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getGrund());
            ps.setString(2, t.getZeitpunkt());
            ps.setInt(3, t.getAussteller1().getId());
            ps.setInt(4, t.getAussteller2().getId());
            ps.setInt(5, t.getSchuldig().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Ticket t) {   // Ändern
        String sql = "UPDATE tickets SET grund=?, zeitpunkt=?, aussteller1=?, aussteller2=?, schuldig=? WHERE id=?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getGrund());
            ps.setString(2, t.getZeitpunkt());
            ps.setInt(3, t.getAussteller1().getId());
            ps.setInt(4, t.getAussteller2().getId());
            ps.setInt(5, t.getSchuldig().getId());
            ps.setInt(6, t.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }


}