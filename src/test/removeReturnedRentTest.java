import Database.DBConnection;
import Database.RentDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class removeReturnedRentTest {

    private RentDAO rentDAO;
    private final String dummyBookId = "0";

    @BeforeEach
    public void setUp() throws SQLException {
        rentDAO = new RentDAO();
        insertDummyBookRecord(dummyBookId);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        removeDummyBookRecord(dummyBookId);
        clearRentsTable();
    }

    /**
     * Clears all rows from the rents table.
     */
    private void clearRentsTable() throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM rents");
        }
    }

    /**
     * Inserts a dummy book record into the books table.
     * Adjust the columns and values as needed for your actual schema.
     */
    private void insertDummyBookRecord(String bookId) throws SQLException {
        String insertQuery = "INSERT INTO books (book_id, title, author, publisher, category, isbn, available, borrowed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {
            ps.setInt(1, Integer.parseInt(bookId));
            ps.setString(2, "Dummy Book");
            ps.setString(3, "Dummy Author");
            ps.setString(4, "Dummy Publisher");
            ps.setString(5, "Dummy Category");
            ps.setString(6, "2344385349");
            ps.setInt(7, 1);
            ps.setInt(8, 0);
            ps.executeUpdate();
        }
    }

    /**
     * Removes the dummy book record from the books table.
     */
    private void removeDummyBookRecord(String bookId) throws SQLException {
        String deleteQuery = "DELETE FROM books WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
            ps.setInt(1, Integer.parseInt(bookId));
            ps.executeUpdate();
        }
    }

    /**
     * Helper method to check if a rent record exists in the database.
     */
    private boolean recordExists(int userId, String bookId) throws SQLException {
        String query = "SELECT COUNT(*) FROM rents WHERE user_id = ? AND book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Test
    public void testRemoveReturnedRentRecord() throws SQLException {
        int userId = 1;
        String bookId = dummyBookId;
        Timestamp borrowedDate = Timestamp.valueOf(LocalDateTime.now().minusDays(2));
        Timestamp dueDate = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        Timestamp returnedDate = Timestamp.valueOf(LocalDateTime.now()); // Non-null indicates the book has been returned
        rentDAO.addRentRecord(userId, bookId, borrowedDate, dueDate, returnedDate, false);
        assertTrue(recordExists(userId, bookId), "Record should exist before removal.");
        rentDAO.removeReturnedRentRecord(userId, bookId);
        assertFalse(recordExists(userId, bookId), "Record should be removed after calling removeReturnedRentRecord.");
    }
}
