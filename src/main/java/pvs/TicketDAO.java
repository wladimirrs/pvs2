package pvs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class TicketDAO {


    public static ObservableList<Ticket> getAll() {
        ObservableList<Ticket> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM tickets";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ticket t = new Ticket(
                        rs.getInt("id"),
                        rs.getString("grund"),
                        rs.getString("zeitpunkt"),
                        Aussteller1.fromId(rs.getInt("aussteller1")),
                        Aussteller2.fromId(rs.getInt("aussteller2")),
                        Schuldig.fromId(rs.getInt("schuldig"))
                );
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void delete(int id) {
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void insert(Ticket t) {
        String sql = "INSERT INTO tickets (grund, zeitpunkt, aussteller1, aussteller2, schuldig) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getGrund());
            ps.setString(2, t.getZeitpunkt());
            ps.setInt(3, t.getAussteller1().get().getId());
            ps.setInt(4, t.getAussteller2().get().getId());
            ps.setInt(5, t.getSchuldig().get().getId());
            ps.executeUpdate();
            System.out.println("Datensatz geschrieben");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void update(Ticket t) {
        String sql = "UPDATE tickets SET grund=?, zeitpunkt=?, aussteller1=?, aussteller2=?, schuldig=? WHERE id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getGrund());
            ps.setString(2, t.getZeitpunkt());
            ps.setInt(3, t.getAussteller1().get().getId());
            ps.setInt(4, t.getAussteller2().get().getId());
            ps.setInt(5, t.getSchuldig().get().getId());
            ps.setInt(6, t.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}