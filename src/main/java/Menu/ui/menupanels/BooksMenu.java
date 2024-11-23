package Menu.ui.menupanels;

import Menu.tables.BookTable;
import Service.BookService;
import Service.RentService;
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
    private RentService rentService;
    private int userId;
    private String userRole;

    public BooksMenu(CardLayout cardLayout, JPanel switchPanel, int userId, String userRole) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        this.userId = userId;
        this.userRole = userRole;
        bookService = new BookService();
        rentService = new RentService();
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

        rentBtn = new JButton("Rent Book");
        rentBtn.addActionListener(this);
        add(rentBtn, BorderLayout.SOUTH);

        if (userRole.equals("admin") || userRole.equals("librarian")){
            rentBtn.setVisible(false);
        }
    }

    /**
     * Sets the book records as rows for BookTable template that was created
     * Displays on panel
     */
    public void loadBooksData() {
        DefaultTableModel model = BTable.getModel();
        model.setRowCount(0);
        List<String[]> books = bookService.getAllBooks();
        if (books != null) {
            for (String[] book : books) {
                model.addRow(book);
            }
        }
    }

    /**
     * Checks whether there have not been more books borrowed than available before user can rent
     * When renting a limit of 14 days is set and record is added to database
     * @param bookId primary key of books to uniquely identify them as part of the library
     */
    private void rentBook(String bookId) {
        if (bookService.canRentBook(bookId)) {
            String[] bookDetails = bookService.getBookDetails(bookId);
            if (bookDetails != null) {
                String title = bookDetails[1];
                Timestamp borrowedDate = Timestamp.valueOf(LocalDateTime.now());
                Timestamp dueDate = Timestamp.valueOf(LocalDateTime.now().plusDays(14)); // Example due date
                Timestamp returnedDate = null;
                boolean overdue = false;
                int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to rent " + title, "Confirm Message", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    rentService.addRentRecord(userId, bookId, borrowedDate, dueDate, returnedDate, overdue);
                    bookService.updateBorrowedCount(bookId);
                    //Minor Bug: Borrowed not updating Live
                    for (Component comp : switchPanel.getComponents()) {
                        if (comp instanceof UserBooksMenu userBooksMenu) {
                            userBooksMenu.loadUserRentData();
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Rent Successful");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "This book is currently out of stock.");
        }
    }

    /**
     * Updates table so new changes to BookTable can be seen
     */
    public void refreshBookTable() {
        DefaultTableModel model = BTable.getModel();
        model.setRowCount(0);
        loadBooksData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rentBtn) {
            int selectedRow = BTable.getSelectedRow();
            if (selectedRow != -1) {
                String bookId = (String) BTable.getValueAt(selectedRow, 0);
                rentBook(bookId);
                //Minor Bug: Refreshing Issued not refreshing Live
                for (Component comp : switchPanel.getComponents()) {
                    if (comp instanceof Dashboard dashboard) {
                        dashboard.refreshIssuedCount();
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a book to rent");
            }
        }
    }
}
