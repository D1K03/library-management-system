package Database;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        Dotenv dotenv = Dotenv.load();

        String db_url = dotenv.get("DB_URL");
        String db_user = dotenv.get("DB_USER");
        String db_password = dotenv.get("DB_PASSWORD");

        if (db_url == null || db_user == null || db_password == null) {
            throw new SQLException("Missing Database Credentials");
        }

        return DriverManager.getConnection(db_url, db_user, db_password);
    }
}
