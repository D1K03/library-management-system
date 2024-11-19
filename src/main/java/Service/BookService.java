package Service;

import Database.BookDAO;
import Database.DBConnection;

import java.sql.*;
import java.util.List;

public class BookService {
    private BookDAO bookData = new BookDAO();

    public List<String[]> getAllBooks() {
        try {
            return bookData.getAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int countBooks() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM books";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveBooks = conn.prepareStatement(query)) {
            ResultSet rs = retrieveBooks.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public void updateBorrowedCount(String bookId) {
        try {
            bookData.updateBorrowedCount(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getBookDetails(String bookId) {
        try {
            return bookData.getBookDetails(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addRentRecord(int userId, String bookId, Timestamp borrowedDate, Timestamp dueDate, Timestamp returnedDate, boolean overdue) {
        try {
            bookData.addRentRecord(userId, bookId, borrowedDate, dueDate, returnedDate, overdue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getAllRentRecords() {
        try {
            return bookData.getAllRentRecords();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String[]> getRentsByUserId(int userId) {
        try {
            return bookData.getRentsByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int countIssued() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM rents";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveRented = conn.prepareStatement(query)) {
            ResultSet rs = retrieveRented.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public boolean canRentBook(String bookId) {
        try {
            return bookData.canRentBook(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}