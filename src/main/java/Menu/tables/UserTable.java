package Menu.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserTable extends JTable {
    private DefaultTableModel model;

    public UserTable() {
        createUTable();
    }

    /**
     * Creates Template for UserTable
     * Headers are set for the user table database records
     */
    private void createUTable() {
        String[] columnHeader = {"User ID", "First Name", "Last Name", "Email Address", "Role", "Registration Date"};
        model = new DefaultTableModel(null, columnHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        setModel(model);
    }

    public DefaultTableModel getModel() {
        return model;
    }
}
