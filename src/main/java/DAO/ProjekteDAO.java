package DAO;

import Klassen.Projekt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pvs.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjekteDAO {


    public static List<Projekt> getAll() {  // Suche
        List<Projekt> list = new ArrayList<>();
        String sql = "SELECT * FROM projekte";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Projekt pp = new Projekt(
                        rs.getInt("id"),
                        rs.getString("bezeichnung"),
                        rs.getString("beginn"),
                        rs.getString("abschluss"),
                        rs.getString("planabschluss")
                );
                list.add(pp);
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Suche: " + e.getMessage());
        }
        return list;
    }

    public static void delete(int id) { // Löschen
        String sql = "DELETE FROM projekte WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Löschen: " + e.getMessage());
        }
    }


    public static void insert(Projekt pp) { // Einfügen
        String sql = "INSERT INTO projekte (bezeichnung, beginn, abschluss, planabschluss) VALUES (?, ?, ?, ?)";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pp.getBezeichnung());
            ps.setString(2, pp.getBeginn());
            ps.setString(3, pp.getAbschluss());
            ps.setString(4, pp.getPlanabschluss());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen: " + e.getMessage());
        }
    }


    public static void update(Projekt pp) { // Ändern
        String sql = "UPDATE projekte SET bezeichnung=?, beginn=?, abschluss=?, planabschluss=? WHERE id=?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pp.getBezeichnung());
            ps.setString(2, pp.getBeginn());
            ps.setString(3, pp.getAbschluss());
            ps.setString(4, pp.getPlanabschluss());
            ps.setInt(5, pp.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim Ändern: " + e.getMessage());
        }
    }


    public static Projekt getProjektById(int id) {       // Projekt suchen nach Id
        String sql = "SELECT * FROM projekte WHERE id = ?";
        try (Connection con = DB.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Projekt(
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