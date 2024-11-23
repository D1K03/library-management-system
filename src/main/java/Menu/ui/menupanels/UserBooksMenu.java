package Menu.ui.menupanels;

import Menu.tables.RentDataTable;
import Menu.tables.UserBooksTable;
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

public class UserBooksMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private UserBooksTable userBooksTable;
    private RentDataTable RDTable;
    private BookService bookService;
    private RentService rentService;
    private JButton returnBtn;
    private int userId;
    private ReturnMenu returnMenu;
    private MembersMenu membersMenu;

    public UserBooksMenu(CardLayout cardLayout, JPanel switchPanel, int userId, ReturnMenu returnMenu, MembersMenu membersMenu) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        this.userId = userId;
        this.returnMenu = returnMenu;
        this.membersMenu = membersMenu;
        bookService = new BookService();
        rentService = new RentService();
        createUBooks();
        loadUserRentData();
    }

    public void createUBooks() {
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        JLabel headerLabel = new JLabel("My Books", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        userBooksTable = new UserBooksTable();
        JScrollPane scrollPane = new JScrollPane(userBooksTable);
        add(scrollPane, BorderLayout.CENTER);

        returnBtn = new JButton("Return Book");
        returnBtn.addActionListener(this);
        add(returnBtn, BorderLayout.SOUTH);
    }

    public void loadUserRentData() {
        rentService.updateOverdueStatus();
        List<String[]> rentRecords = rentService.getRentsByUserId(userId);
        if (rentRecords != null) {
            DefaultTableModel model = userBooksTable.getModel();
            for (String[] rentRecord : rentRecords) {
                rentRecord[5] = rentRecord[5].equals("0") ? "false" : "true";
                boolean exists = false;
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(rentRecord[0])) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    model.addRow(rentRecord);
                }
            }
        }
    }

    /**
     * Updates Return Panel if a book is returned
     */
    private void returnBook() {
        int selectedRow = userBooksTable.getSelectedRow();
        if (selectedRow != -1) {
            String returnedDate = (String) userBooksTable.getValueAt(selectedRow, 4);
            if (returnedDate != null && !returnedDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "This book has already been returned.");
            }
            else {
                String bookId = (String) userBooksTable.getValueAt(selectedRow, 0);
                String title = (String) userBooksTable.getValueAt(selectedRow, 1);
                String borrowedDate = (String) userBooksTable.getValueAt(selectedRow, 2);
                String dueDate = (String) userBooksTable.getValueAt(selectedRow, 3);
                Timestamp returnDate = Timestamp.valueOf(LocalDateTime.now());
                boolean overdue = Boolean.parseBoolean((String) userBooksTable.getValueAt(selectedRow, 5));

                int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to return " + title, "Confirm Message", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    userBooksTable.setValueAt(returnDate.toString(), selectedRow, 4);
                    returnMenu.addReturnRecord(String.valueOf(userId), bookId, title, borrowedDate, dueDate, returnDate.toString(), overdue);
                    rentService.updateRentRecord(userId, bookId, returnDate);
                    bookService.updateBorrowedCount(bookId);
                    membersMenu.refreshUserTable();
                    JOptionPane.showMessageDialog(this, "Return Successful");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a book to return.");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnBtn) {
            returnBook();
            for (Component comp : switchPanel.getComponents()) {
                if (comp instanceof Dashboard dashboard) {
                    dashboard.refreshReturnCount();
                    break;
                }
            }
        }
    }
}
