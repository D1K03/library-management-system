package Menu.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReturnDataTable extends JTable {
    private DefaultTableModel model;

    public ReturnDataTable() {
        createRDTable();
    }

    /**
     * Creates Template for ReturnDataTable
     * Headers are set to hold data from all three tables
     */
    private void createRDTable() {
        String[] columnHeader = {"User ID", "First Name", "Last Name", "Book ID", "Title", "Borrowed Date", "Due Date", "Returned Date"};
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
