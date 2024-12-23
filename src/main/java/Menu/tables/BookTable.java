package Menu.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookTable extends JTable {
    private DefaultTableModel model;

    public BookTable() {
        createBTable();
    }

    /**
     * Creates Template for BookTable
     * Headers are set for the book table database records
     */
    private void createBTable() {
        String[] columnHeader = {"Book ID", "Title", "Author", "Publisher", "Category", "ISBN", "Available", "Borrowed"};
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
