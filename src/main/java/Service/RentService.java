package Service;

import Database.RentDAO;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class RentService {
    private RentDAO rentData;

    public RentService() {
        this.rentData = new RentDAO();
    }

    public void addRentRecord(int userId, String bookId, Timestamp borrowedDate, Timestamp dueDate, Timestamp returnedDate, boolean overdue) {
        try {
            rentData.addRentRecord(userId, bookId, borrowedDate, dueDate, returnedDate, overdue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getIssuedRecords() {
        try {
            return rentData.getIssuedRecords();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String[]> getRentsByUserId(int userId) {
        try {
            return rentData.getRentsByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int countIssued() throws SQLException {
            return rentData.countIssued();
    }

    public void updateRentRecord(int userId, String bookId, Timestamp returnDate) {
        try {
            rentData.updateRentRecord(userId, bookId, returnDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getReturnedRecords() {
        try {
            return rentData.getReturnedRecords();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateOverdueStatus() {
        try {
            List<String[]> rentRecords = rentData.getIssuedRecords();
            Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());
            for (String[] record : rentRecords) {
                Timestamp dueDate = Timestamp.valueOf(record[6]);
                Timestamp returnedDate = null;
                if (record.length > 8 && record[8] != null && !record[8].isEmpty() && !record[8].equals("0")) {
                    try {
                        returnedDate = Timestamp.valueOf(record[8]);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid timestamp format for returned date: " + record[8]);
                        continue;
                    }
                }
                if (returnedDate == null && currentDate.after(dueDate)) {
                    rentData.updateOverdueStatus(Integer.parseInt(record[0]), record[3], true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}