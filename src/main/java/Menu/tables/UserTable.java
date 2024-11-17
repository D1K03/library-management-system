package Menu.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserTable {
    private JTable users;
    private DefaultTableModel model;

    UserTable() {
        createUTable();
    }

    private void createUTable() {
        String[] columnHeader = {"User ID", "First Name", "Last Name", "Email Address", "Borrowed", "Returned", "Overdue"};
        model = new DefaultTableModel(null, columnHeader);
        users = new JTable(model);
    }
}
