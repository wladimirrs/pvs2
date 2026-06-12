package pvs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProjektDAO {


    public static ObservableList<Projekt> getAll() {
        ObservableList<Projekt> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM projektleitung";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Projekt p = new Projekt(
                        rs.getInt("projektleitung_id"),
                        Pro.fromId(rs.getInt("projekt_id")),
                        Mit.fromId(rs.getInt("mitarbeiter_id")),
                        rs.getString("von"),
                        rs.getString("bis")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void delete(int id) {
        String sql = "DELETE FROM projektleitung WHERE projektleitung_id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void insert(Projekt p) {
        String sql = "INSERT INTO projektleitung (projekt_id, mitarbeiter_id, von, bis) VALUES (?, ?, ?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getPro().get().getId());
            ps.setInt(2, p.getMit().get().getId());
            ps.setString(3, p.getVon());
            ps.setString(4, p.getBis());
            ps.executeUpdate();
            System.out.println("Datensatz geschrieben");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void update(Projekt p) {
        String sql = "UPDATE projektleitung SET projekt_id=?, mitarbeiter_id=?, von=?, bis=? WHERE projektleitung_id=?";
        try (Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getPro().get().getId());
            ps.setInt(2, p.getMit().get().getId());
            ps.setString(3, p.getVon());
            ps.setString(4, p.getBis());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}