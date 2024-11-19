package Menu.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserBooksTable extends JTable {
    private DefaultTableModel model;

    public UserBooksTable() {
        createUBTable();
    }

    private void createUBTable() {
        String[] columnHeader = {"Book ID", "Title", "Borrowed Date", "Due Date", "Returned Date", "Overdue"};
        model = new DefaultTableModel(null, columnHeader);
        setModel(model);
    }

    public DefaultTableModel getModel() {
        return model;
    }
}
