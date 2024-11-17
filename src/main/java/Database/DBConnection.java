package Database;

import java.sql.*;

public class DBConnection {
    private static final String db_url = "jdbc:mysql://localhost:3306/librarydb";
    private static final String db_user = "root";
    private static final String db_pass = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(db_url, db_user, db_pass);
    }
}
