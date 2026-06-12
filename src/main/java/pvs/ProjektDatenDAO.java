package pvs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProjektDatenDAO {


    public static ObservableList<ProjektDaten> getAll() {
        ObservableList<ProjektDaten> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM projekte";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProjektDaten pp = new ProjektDaten(
                        rs.getInt("id"),
                        rs.getString("bezeichnung"),
                        rs.getString("beginn"),
                        rs.getString("abschluss"),
                        rs.getString("planabschluss")
                );
                list.add(pp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void delete(int id) {
        String sql = "DELETE FROM projekte WHERE id = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void insert(ProjektDaten pp) {
        String sql = "INSERT INTO projekte (bezeichnung, beginn, abschluss, planabschluss) VALUES (?, ?, ?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pp.getBezeichnung());
            ps.setString(2, pp.getBeginn());
            ps.setString(3, pp.getAbschluss());
            ps.setString(4, pp.getPlanabschluss());
            ps.executeUpdate();
            System.out.println("Datensatz geschrieben");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void update(ProjektDaten pp) {
        String sql = "UPDATE projekte SET bezeichnung=?, beginn=?, abschluss=?, planabschluss=? WHERE id=?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pp.getBezeichnung());
            ps.setString(2, pp.getBeginn());
            ps.setString(3, pp.getAbschluss());
            ps.setString(4, pp.getPlanabschluss());
            ps.setInt(5, pp.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}