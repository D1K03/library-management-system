package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

    private static final String db_url = "jdbc:mysql://localhost:3306/librarydb";
    private static final String db_user = "root";
    private static final String db_pass = "";

    public void retrieveData() {
        String query = "SELECT * FROM user";

        try (Connection connect = DriverManager.getConnection(db_url, db_user, db_pass);
             Statement createState = connect.createStatement();
             ResultSet dataSet = createState.executeQuery(query)) {

            while (dataSet.next()) {
                int id = dataSet.getInt("user_id");
                String name = dataSet.getString("name");
                System.out.println("User ID: " + id + ", Name: " + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBConnection users = new DBConnection();
        users.retrieveData();
    }
}
