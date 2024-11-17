package Service;

import Database.BookDAO;
import Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}