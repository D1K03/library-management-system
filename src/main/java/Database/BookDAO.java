package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    /**
     * Query to populate BookTable JTable by accessing users from database
     * @return list of all the book records in the database
     * @throws SQLException in case of any connectivity issues or lack of access
     */
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

    /**
     * Query to count the amount of books stored in database
     * @return amount of books
     */
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

    /**
     * Query to check whether library has specified book in stock
     * @param bookId used to specify and locate book
     * @return boolean representing availability
     */
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

    /**
     * Query retrieves data about a book with a specified id
     * @return record data as an array of strings
     */
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

    /**
     * Query to retrieve rented books that have not been returned
     * Updates borrowed to track amount of books outsourced
     */
    public void updateBorrowedCount(String bookId) throws SQLException {
        String countQuery = "SELECT COUNT(*) AS total FROM rents WHERE book_id = ? AND returned_date IS NULL";
        String updateQuery = "UPDATE books SET borrowed = ? WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement countBorrowed = conn.prepareStatement(countQuery);
             PreparedStatement updateBorrowed = conn.prepareStatement(updateQuery)) {

            countBorrowed.setString(1, bookId);
            ResultSet rs = countBorrowed.executeQuery();

            if (rs.next()) {
                int borrowedCount = rs.getInt("total");
                updateBorrowed.setInt(1, borrowedCount);
                updateBorrowed.setString(2, bookId);
                updateBorrowed.executeUpdate();
            }
        }
    }

    /**
     * Query to insert new book into database
     * @param bookTitle Book Title
     * @param bookAuthor Book Author
     * @param bookPublisher Book Publisher
     * @param bookCategory Genre of book
     * @param ISBN International Standard Book Number
     * @param isAvailable Amount of books available
     * @param isBorrowed Amount of books borrowed
     */
    public void addBook(String bookTitle, String bookAuthor, String bookPublisher, String bookCategory, String ISBN, int isAvailable, int isBorrowed) throws SQLException {
        String query = "INSERT INTO books (title, author, publisher, category, isbn, available, borrowed) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement insertBookData = conn.prepareStatement(query);
            insertBookData.setString(1,  bookTitle);
            insertBookData.setString(2, bookAuthor);
            insertBookData.setString(3, bookPublisher);
            insertBookData.setString(4, bookCategory);
            insertBookData.setString(5, ISBN);
            insertBookData.setInt(6, isAvailable);
            insertBookData.setInt(7, isBorrowed);
            insertBookData.executeUpdate();
        }
    }
}