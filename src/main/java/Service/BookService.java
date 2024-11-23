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

    public boolean canRentBook(String bookId) {
        try {
            return bookData.canRentBook(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addBook(String bookTitle, String bookAuthor, String bookPublisher, String bookCategory, String ISBN, int isAvailable, int isBorrowed) {
        try {
            bookData.addBook(bookTitle, bookAuthor, bookPublisher, bookCategory, ISBN, isAvailable, isBorrowed);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}