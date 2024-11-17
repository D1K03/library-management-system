package Service;

import Database.UserDAO;

import java.sql.Date;
import java.sql.SQLException;

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

    public void addUser(String firstName, String lastName, String email, String role, Date registrationDate, String password) {
        try {
            userData.addUser(firstName, lastName, email, role, registrationDate, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean emailExists(String email) {
        try {
            return userData.emailExists(email);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}