package Menu.ui.menupanels;

import Menu.tables.BookTable;
import Service.BookService;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BooksMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private BookTable BTable;
    private BookService bookService;

    public BooksMenu(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        bookService = new BookService();
        createBorrow();
        loadBooksData();

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

        BTable = new BookTable();
        JScrollPane scrollPane = new JScrollPane(BTable);
        add(scrollPane, BorderLayout.CENTER);

    }

    private void loadBooksData() {
        List<String[]> books = bookService.getAllBooks();
        if (books != null) {
            DefaultTableModel model = BTable.getModel();
            for (String[] book : books) {
                model.addRow(book);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
