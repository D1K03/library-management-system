package Database;

import Menu.ui.menupanels.BooksMenu;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public List<String[]> getAllBooks() throws SQLException {
        List<String[]> books = new ArrayList<>();
        String query = "SELECT book_id, title, author, publisher, category, isbn, available, borrowed FROM books";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveBooks = conn.prepareStatement(query);
             ResultSet rs = retrieveBooks.executeQuery()) {
            while (rs.next()) {
                String[] book = {
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("publisher"),
                        rs.getString("category"),
                        rs.getString("isbn"),
                        rs.getString("available"),
                        rs.getString("borrowed")
                };
                books.add(book);
            }
        }
        return books;
    }

    public int countBooks() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM books";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement getBookCount = conn.prepareStatement(query)) {
            ResultSet rs = getBookCount.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public boolean canRentBook(String bookId) throws SQLException {
        String query = "SELECT borrowed, available FROM books WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkAvailability = conn.prepareStatement(query)) {
            checkAvailability.setString(1, bookId);
            try (ResultSet rs = checkAvailability.executeQuery()) {
                if (rs.next()) {
                    int borrowed = rs.getInt("borrowed");
                    int available = rs.getInt("available");
                    return borrowed < available;
                }
            }
        }
        return false;
    }

    public String[] getBookDetails(String bookId) throws SQLException {
        String query = "SELECT book_id, title, author, publisher, category, isbn, available, borrowed FROM books WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveBook = conn.prepareStatement(query)) {
            retrieveBook.setString(1, bookId);
            try (ResultSet rs = retrieveBook.executeQuery()) {
                if (rs.next()) {
                    return new String[]{
                            rs.getString("book_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("publisher"),
                            rs.getString("category"),
                            rs.getString("isbn"),
                            rs.getString("available"),
                            rs.getString("borrowed")
                    };
                }
            }
        }
        return null;
    }

    public void addRentRecord(int userId, String bookId, Timestamp borrowedDate, Timestamp dueDate, Timestamp returnedDate, boolean overdue) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM rents WHERE user_id = ? AND book_id = ?";
        String insertQuery = "INSERT INTO rents (user_id, book_id, borrowed_date, due_date, returned_date, overdue) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            // Check if the record already exists
            checkStmt.setInt(1, userId);
            checkStmt.setString(2, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("User has already rented this book!");
            }

            // Insert the new rent record
            insertStmt.setInt(1, userId);
            insertStmt.setString(2, bookId);
            insertStmt.setTimestamp(3, borrowedDate);
            insertStmt.setTimestamp(4, dueDate);
            insertStmt.setTimestamp(5, returnedDate);
            insertStmt.setBoolean(6, overdue);
            insertStmt.executeUpdate();
        }
    }

    public List<String[]> getAllRentRecords() throws SQLException {
        List<String[]> rentRecords = new ArrayList<>();
        String query = "SELECT r.user_id, u.forename, u.surname, r.book_id, b.title, r.borrowed_date, r.due_date, r.overdue " +
                "FROM rents r " + "JOIN users u ON r.user_id = u.user_id " + "JOIN books b ON r.book_id = b.book_id";
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

    public List<String[]> getRentsByUserId(int userId) throws SQLException {
        List<String[]> rentRecords = new ArrayList<>();
        String query = "SELECT r.book_id, b.title, r.borrowed_date, r.due_date, r.returned_date, r.overdue " +
                "FROM rents r " + "JOIN books b ON r.book_id = b.book_id " + "WHERE r.user_id = ?";
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

    public void updateBorrowedCount(String bookId) throws SQLException {
        String countQuery = "SELECT COUNT(*) AS total FROM rents WHERE book_id = ?";
        String updateQuery = "UPDATE books SET borrowed = ? WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement countStmt = conn.prepareStatement(countQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            countStmt.setString(1, bookId);
            ResultSet rs = countStmt.executeQuery();

            if (rs.next()) {
                int borrowedCount = rs.getInt("total");

                updateStmt.setInt(1, borrowedCount);
                updateStmt.setString(2, bookId);
                updateStmt.executeUpdate();
            }
        }
    }
}