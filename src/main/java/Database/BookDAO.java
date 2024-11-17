package Database;

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
}