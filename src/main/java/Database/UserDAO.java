package Database;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean checkUserCredentials(String email, String password) throws SQLException {
        String query = "SELECT password_hash FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkUserData = conn.prepareStatement(query)) {
            checkUserData.setString(1, email);
            ResultSet rs = checkUserData.executeQuery();
            if (rs.next()) {
                String passwordHash = rs.getString("password_hash");
                return BCrypt.checkpw(password, passwordHash);
            }
        }
        return false;
    }

    public void addUser(String firstName, String lastName, String email, String role, Timestamp registrationDate, String password) throws SQLException {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        String query = "INSERT INTO users (forename, surname, email, role, reg_date, password_hash) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement addUserData = conn.prepareStatement(query)) {
            addUserData.setString(1, firstName);
            addUserData.setString(2, lastName);
            addUserData.setString(3, email);
            addUserData.setString(4, role);
            addUserData.setTimestamp(5, registrationDate);
            addUserData.setString(6, passwordHash);
            addUserData.executeUpdate();
        }
    }

    public boolean emailExists(String email) throws SQLException {
        String query = "SELECT email FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkEmailExists = conn.prepareStatement(query)) {
            checkEmailExists.setString(1, email);
            ResultSet rs = checkEmailExists.executeQuery();
            return rs.next();
        }
    }

    public String getUserRole(String email) throws SQLException {
        String query = "SELECT role FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement getUserRole = conn.prepareStatement(query)) {
            getUserRole.setString(1, email);
            ResultSet rs = getUserRole.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        }
        return null;
    }

    public int countUsers() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement getUserCount = conn.prepareStatement(query)) {
            ResultSet rs = getUserCount.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public List<String[]> getAllUsers() throws SQLException {
        List<String[]> users = new ArrayList<>();
        String query = "SELECT user_id, forename, surname, email, role, reg_date FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveUsers = conn.prepareStatement(query);
             ResultSet rs = retrieveUsers.executeQuery()) {
            while (rs.next()) {
                String[] user = {
                        rs.getString("user_id"),
                        rs.getString("forename"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("reg_date"),
                };
                users.add(user);
            }
        }
        return users;
    }
}
