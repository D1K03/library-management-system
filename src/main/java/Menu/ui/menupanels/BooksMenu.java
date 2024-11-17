package Menu.ui.menupanels;

import Menu.tables.BookTable;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BooksMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private BookTable table;

    public BooksMenu(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createBorrow();
        addSampleData();
    }

    public void createBorrow() {
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        JLabel headerLabel = new JLabel("Book Catalogue", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        table = new BookTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

    }

    private void addSampleData() {
        // Access the DefaultTableModel and add rows
        DefaultTableModel model = table.getModel();

        // Add sample data (rows) to the table
        model.addRow(new Object[]{"1", "To Kill a Mockingbird", "Harper Lee", "J.B. Lippincott & Co.", "Fiction", "Yes", "No"});
        model.addRow(new Object[]{"2", "1984", "George Orwell", "Secker & Warburg", "Dystopian", "No", "Yes"});
        model.addRow(new Object[]{"3", "Moby Dick", "Herman Melville", "Harper & Brothers", "Adventure", "Yes", "No"});
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
