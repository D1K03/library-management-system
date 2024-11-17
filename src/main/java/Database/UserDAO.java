package Database;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

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

    public void addUser(String firstName, String lastName, String email, String role, Date registrationDate, String password) throws SQLException {
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        String query = "INSERT INTO users (forename, surname, email, role, reg_date, password_hash) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement addUserData = conn.prepareStatement(query)) {
            addUserData.setString(1, firstName);
            addUserData.setString(2, lastName);
            addUserData.setString(3, email);
            addUserData.setString(4, role);
            addUserData.setDate(5, registrationDate);
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
}