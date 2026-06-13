package pvs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/pvs";    // DB-Anbindung zur Wiederverwendung
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {      // Spezifischere Exception
        return DriverManager.getConnection(URL, USER, PASS);
    }
}