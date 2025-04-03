import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import Database.DBConnection;
import org.junit.jupiter.api.Test;

public class DBConnectionTest {

    /**
     * Tested with a running local database
     * Verifying whether the database is running
     * @throws SQLException if database connection fails
     */
    @Test
    public void testisConnection() throws SQLException {
        Connection conn = DBConnection.getConnection();
        assertNotNull(conn, "Should not be null value.");
        conn.close();
    }

    /**
     * Tested with a running local database
     * Checks if the connection is still alive based on the constraint provided in the isValid method
     */
    @Test
    public void testConnectionValid() throws SQLException {
        Connection conn = DBConnection.getConnection();
        assertTrue(conn.isValid(2), "Database connection timed out.");
    }
}

