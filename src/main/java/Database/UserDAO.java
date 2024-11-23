package Database;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    /**
     * Authentication when user logins in
     * @param email user's email provided on sign up
     * @param password hashed version of user's password
     * @return whether user's entered data matches one found in database
     * @throws SQLException in case of any connectivity issues or lack of access
     */
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

    /**
     * User's information provided from sign up to be stored into database
     * @param firstName user's forename
     * @param lastName user's surname
     * @param role user's role
     * @param registrationDate user's registration date
     */
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

    /**
     * To prevent duplicate emails
     * @return boolean representing whether email exists in database or not
     */
    public boolean emailExists(String email) throws SQLException {
        String query = "SELECT email FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkEmailExists = conn.prepareStatement(query)) {
            checkEmailExists.setString(1, email);
            ResultSet rs = checkEmailExists.executeQuery();
            return rs.next();
        }
    }

    /**
     * Used to allocate permissions on log in
     * @return the user's role
     */
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

    /**
     * So users count can be tracked
     * @return the total users
     * @throws SQLException in case of any connectivity issues or lack of access
     */
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

    /**
     * Used to populate UserTable JTable by accessing users from database
     * @return A list of all records
     */
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

    /**
     * Retrieves user id based on email as email is also unique
     * @return user id
     */
    public int getUserIdByEmail(String email) throws SQLException {
        String query = "SELECT user_id FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveId = conn.prepareStatement(query)) {
            retrieveId.setString(1, email);
            try (ResultSet rs = retrieveId.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }
        }
        return -1;
    }

    /**
     * Allows the dropdown menu to identify the user with their id
     * @return A list of user's id, first name and last name concatenations for each user
     */
    public List<String> getUserIdAndName() throws SQLException {
        List<String> userData = new ArrayList<>();
        String query = "SELECT user_id, forename, surname FROM users WHERE role != 'admin'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveUserIdName = conn.prepareStatement(query);
             ResultSet rs = retrieveUserIdName.executeQuery()) {
            while (rs.next()) {
                String user = rs.getInt("user_id") + " " + rs.getString("forename") + " " + rs.getString("surname");
                userData.add(user);
            }
        }
        return userData;
    }

    /**
     * Called by admin to update user roles by their id
     * @param userId primary key of user in database, used to uniquely identify them
     */
    public void updateUserRole(int userId, String userRole) throws SQLException {
        String query = "UPDATE users SET role = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
        PreparedStatement updateRole = conn.prepareStatement(query)) {
            updateRole.setString(1, userRole);
            updateRole.setInt(2, userId);
            updateRole.executeUpdate();
        }
    }
}
