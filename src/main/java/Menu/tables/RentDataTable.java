package Menu.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RentDataTable extends JTable {
    private DefaultTableModel model;

    public RentDataTable() {
        createRDTable();
    }

    private void createRDTable() {
        String[] columnHeader = {"User ID", "First Name", "Last Name", "Book ID", "Title", "Borrowed Date", "Due Date", "Overdue"};
        model = new DefaultTableModel(null, columnHeader);
        setModel(model);
    }

    public DefaultTableModel getModel() {
        return model;
    }
}
