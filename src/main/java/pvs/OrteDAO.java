package pvs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class OrteDAO {


    public static ObservableList<Ort> getAll() {
        ObservableList<Ort> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM orte";
        try (Connection con = DB.getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void delete(int id) {
        String sql = "DELETE FROM orte WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void insert(Ort o) {
        String sql = "INSERT INTO orte (plz, ortsname) VALUES (?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, o.getPlz());
            ps.setString(2, o.getOrtsname());
            ps.executeUpdate();
            System.out.println("Datensatz geschrieben");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void update(Ort o) {
        String sql = "UPDATE orte SET plz=?, ortsname=? WHERE id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, o.getPlz());
            ps.setString(2, o.getOrtsname());
            ps.setInt(3, o.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}