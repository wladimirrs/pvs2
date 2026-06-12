package pvs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class VertragstypenDAO {


    public static ObservableList<Vertragstyp> getAll() {
        ObservableList<Vertragstyp> list = FXCollections.observableArrayList();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void delete(int id) {
        String sql = "DELETE FROM vertragstypen WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void insert(Vertragstyp v) {
        String sql = "INSERT INTO vertragstypen (bezeichnung) VALUES (?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getBezeichnung());
            ps.executeUpdate();
            System.out.println("Datensatz geschrieben");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void update(Vertragstyp v) {
        String sql = "UPDATE vertragstypen SET bezeichnung=? WHERE id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getBezeichnung());
            ps.setInt(2, v.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}