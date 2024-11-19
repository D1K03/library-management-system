package Menu.ui.menupanels;

import Menu.tables.BookTable;
import Service.BookService;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BooksMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private BookTable BTable;
    private JButton rentBtn;
    private BookService bookService;
    private int userId;

    public BooksMenu(CardLayout cardLayout, JPanel switchPanel, int userId) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        this.userId = userId;
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

        rentBtn = new JButton("Rent");
        rentBtn.addActionListener(this);
        add(rentBtn, BorderLayout.SOUTH);
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

    private void rentBook(String bookId) {
        if (bookService.canRentBook(bookId)) {
            String[] bookDetails = bookService.getBookDetails(bookId);
            if (bookDetails != null) {
                String title = bookDetails[1];
                Timestamp borrowedDate = Timestamp.valueOf(LocalDateTime.now());
                Timestamp dueDate = Timestamp.valueOf(LocalDateTime.now().plusDays(14)); // Example due date
                Timestamp returnedDate = null;
                boolean overdue = false;

                bookService.addRentRecord(userId, bookId, borrowedDate, dueDate, returnedDate, overdue);
                bookService.updateBorrowedCount(bookId);

                for (Component comp : switchPanel.getComponents()) {
                    if (comp instanceof UserBooksMenu) {
                        UserBooksMenu userBooksMenu = (UserBooksMenu) comp;
                        userBooksMenu.loadUserRentData();
                        break;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "This book is currently out of stock.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rentBtn) {
            int selectedRow = BTable.getSelectedRow();
            if (selectedRow != -1) {
                String bookId = (String) BTable.getValueAt(selectedRow, 0);
                rentBook(bookId);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to rent");
            }
        }
    }
}
