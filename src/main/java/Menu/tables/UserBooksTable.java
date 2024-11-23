package Menu.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserBooksTable extends JTable {
    private DefaultTableModel model;

    public UserBooksTable() {
        createUBTable();
    }


    /**
     * Creates Template for JTable
     * Headers are set for the rent table database records
    */
    private void createUBTable() {
        String[] columnHeader = {"Book ID", "Title", "Borrowed Date", "Due Date", "Returned Date", "Overdue"};
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
