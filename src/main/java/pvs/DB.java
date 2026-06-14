package pvs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static DB instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/pvs";    // DB-Anbindung zur Wiederverwendung
    private static final String USER = "root";
    private static final String PASS = "";

    private DB() throws SQLException {              // Konstruktor, privat, baut Verbindung
        connection = DriverManager.getConnection(URL, USER, PASS);
    }

    public static DB getInstance() throws SQLException {    // prüft, ob es eine DB-Instanz gibt, gibt eine zurück
        if (instance == null || instance.getConnection().isClosed()) {  // wenn da, gibt zurück, wenn nicht, erstellt
            instance = new DB();
        }
        return instance;
    }

    public Connection getConnection() { // Zugriff
        return connection;
    }



    /*public static Connection getConnection() throws SQLException {      // Spezifischere Exception
        return DriverManager.getConnection(URL, USER, PASS);
    }*/
}