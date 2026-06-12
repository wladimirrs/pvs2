package pvs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NutzerDAO {

    public static boolean register(String email_adresse, String passwort) {
        String sql = "INSERT INTO nutzer (email_adresse, passwort) VALUES (?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email_adresse);
                ps.setString(2, passwort);
                ps.executeUpdate();
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static boolean login(String email_adresse, String passwort) {
        String sql = "SELECT * FROM nutzer WHERE email_adresse = ? AND passwort = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email_adresse);
                ps.setString(2, passwort);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }








}
