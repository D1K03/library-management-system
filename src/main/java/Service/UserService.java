package Service;

import Database.DBConnection;
import Database.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDAO userData = new UserDAO();


    public boolean checkUserCredentials(String email, String password) {
        try {
            return userData.checkUserCredentials(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int addUser(String firstName, String lastName, String email, String role, Timestamp registrationDate, String password) {
        try {
            userData.addUser(firstName, lastName, email, role, registrationDate, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean emailExists(String email) {
        try {
            return userData.emailExists(email);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserRole(String email) {
        try {
            return userData.getUserRole(email);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int countUsers() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement retrieveUsers = conn.prepareStatement(query)) {
            ResultSet rs = retrieveUsers.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public List<String[]> getAllUsers() {
        try {
            return userData.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getUserIdByEmail(String email) {
        try {
            return userData.getUserIdByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<String> getUserIdAndName() {
        try {
            return userData.getUserIdAndName();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void updateUserRole(int userId, String role) {
        try {
            userData.updateUserRole(userId, role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}