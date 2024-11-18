package Menu.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserTable extends JTable {
    private DefaultTableModel model;

    public UserTable() {
        createUTable();
    }

    private void createUTable() {
        String[] columnHeader = {"User ID", "First Name", "Last Name", "Email Address", "Role", "Registration Date"};
        model = new DefaultTableModel(null, columnHeader);
        setModel(model);
    }

    public DefaultTableModel getModel() {
        return model;
    }
}
