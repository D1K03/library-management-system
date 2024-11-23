package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentDAO {

    /**
     * Query to insert new rent tracker into database
     * @param borrowedDate date that user borrowed book
     * @param dueDate deadline for return of book
     * @param returnedDate date user returned book
     * @param overdue boolean specifying whether user has met deadline
     * @throws SQLException
     */
    public void addRentRecord(int userId, String bookId, Timestamp borrowedDate, Timestamp dueDate, Timestamp returnedDate, boolean overdue) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM rents WHERE user_id = ? AND book_id = ?";
        String insertQuery = "INSERT INTO rents (user_id, book_id, borrowed_date, due_date, returned_date, overdue) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkRent = conn.prepareStatement(checkQuery);
             PreparedStatement insertRent = conn.prepareStatement(insertQuery)) {

            checkRent.setInt(1, userId);
            checkRent.setString(2, bookId);
            ResultSet rs = checkRent.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("User has already rented this book!");
            }

            insertRent.setInt(1, userId);
            insertRent.setString(2, bookId);
            insertRent.setTimestamp(3, borrowedDate);
            insertRent.setTimestamp(4, dueDate);
            insertRent.setTimestamp(5, returnedDate);
            insertRent.setBoolean(6, overdue);
            insertRent.executeUpdate();
        }
    }

    /**
     * Query to get all the users who have rented a book but have not returned it
     * @return list of records for rented books
     */
    public List<String[]> getIssuedRecords() throws SQLException {
        List<String[]> rentRecords = new ArrayList<>();
        String query = "SELECT r.user_id, u.forename, u.surname, r.book_id, b.title, r.borrowed_date, r.due_date, r.overdue " +
                "FROM rents r " +
                "JOIN users u ON r.user_id = u.user_id " +
                "JOIN books b ON r.book_id = b.book_id " +
                "WHERE r.returned_date IS NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveRents = conn.prepareStatement(query);
             ResultSet rs = retrieveRents.executeQuery()) {
            while (rs.next()) {
                String[] rentRecord = {
                        rs.getString("user_id"),
                        rs.getString("forename"),
                        rs.getString("surname"),
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("borrowed_date"),
                        rs.getString("due_date"),
                        rs.getString("overdue")
                };
                rentRecords.add(rentRecord);
            }
        }
        return rentRecords;
    }

    /**
     * Query to get a specific rent tracker by user id
     * @return record of the rent found with matching user id
     */
    public List<String[]> getRentsByUserId(int userId) throws SQLException {
        List<String[]> rentRecords = new ArrayList<>();
        String query = "SELECT r.book_id, b.title, r.borrowed_date, r.due_date, r.returned_date, r.overdue FROM rents r " +
                "JOIN books b ON r.book_id = b.book_id WHERE r.user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveRents = conn.prepareStatement(query)) {
            retrieveRents.setInt(1, userId);
            try (ResultSet rs = retrieveRents.executeQuery()) {
                while (rs.next()) {
                    String[] rentRecord = {
                            rs.getString("book_id"),
                            rs.getString("title"),
                            rs.getString("borrowed_date"),
                            rs.getString("due_date"),
                            rs.getString("returned_date"),
                            rs.getString("overdue")
                    };
                    rentRecords.add(rentRecord);
                }
            }
        }
        return rentRecords;
    }

    /**
     * Query to count get the amount of users who have not returned their book
     * @return amount of rents
     */
    public int countIssued() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM rents";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement getRentCount = conn.prepareStatement(query)) {
            ResultSet rs = getRentCount.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    /**
     * Query to returned date after successfully returning book
     */
    public void updateRentRecord(int userId, String bookId, Timestamp returnDate) throws SQLException {
        String updateQuery = "UPDATE rents SET returned_date = ? WHERE user_id = ? AND book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement updateRent = conn.prepareStatement(updateQuery)) {
            updateRent.setTimestamp(1, returnDate);
            updateRent.setInt(2, userId);
            updateRent.setString(3, bookId);
            updateRent.executeUpdate();
        }
    }

    /**
     * Query to retrieve records of users who have returned their books
     * @return list of all returned books
     */
    public List<String[]> getReturnedRecords() throws SQLException {
        List<String[]> returnedRecords = new ArrayList<>();
        String query = "SELECT r.user_id, u.forename, u.surname, r.book_id, b.title, r.borrowed_date, r.due_date, r.returned_date, r.overdue " +
                "FROM rents r " +
                "JOIN users u ON r.user_id = u.user_id " +
                "JOIN books b ON r.book_id = b.book_id " +
                "WHERE r.returned_date IS NOT NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveReturned = conn.prepareStatement(query);
             ResultSet rs = retrieveReturned.executeQuery()) {
            while (rs.next()) {
                String[] record = {
                        rs.getString("user_id"),
                        rs.getString("forename"),
                        rs.getString("surname"),
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("borrowed_date"),
                        rs.getString("due_date"),
                        rs.getString("returned_date"),
                        rs.getString("overdue")
                };
                returnedRecords.add(record);
            }
        }
        return returnedRecords;
    }

    /**
     * Query to update overdue status for a user's rented book
     * @param overdue boolean whether current date is greater than due date of book
     */
    public void updateOverdueStatus(int userId, String bookId, boolean overdue) throws SQLException {
        String updateQuery = "UPDATE rents SET overdue = ? WHERE user_id = ? AND book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement updateOverdue = conn.prepareStatement(updateQuery)) {
            updateOverdue.setBoolean(1, overdue);
            updateOverdue.setInt(2, userId);
            updateOverdue.setString(3, bookId);
            updateOverdue.executeUpdate();
        }
    }
}