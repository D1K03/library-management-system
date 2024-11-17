package Menu.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookTable extends JTable {
    private JTable books;
    private DefaultTableModel model;

    public BookTable() {
        String[][] data = {};
        createBTable();
    }

    private void createBTable() {
        String[] columnHeader = {"Book ID", "Title", "Author", "Publisher", "Category", "Available", "Borrowed"};
        model = new DefaultTableModel(null, columnHeader);
        setModel(model);

    }

    public DefaultTableModel getModel() {
        return model;
    }
}
