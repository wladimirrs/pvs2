package pvs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/pvs";    // DB-Anbindung zur Wiederverwendung
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}