package Menu.ui.menupanels;

import Menu.tables.UserBooksTable;
import Service.BookService;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserBooksMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private UserBooksTable userBooksTable;
    private BookService bookService;
    private int userId;

    public UserBooksMenu(CardLayout cardLayout, JPanel switchPanel, int userId) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        this.userId = userId;
        bookService = new BookService();
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

    }

    public void loadUserRentData() {
        List<String[]> rentRecords = bookService.getRentsByUserId(userId);
        if (rentRecords != null) {
            DefaultTableModel model = userBooksTable.getModel();
            for (String[] rentRecord : rentRecords) {
                rentRecord[5] = rentRecord[5].equals("0") ? "false" : "true";
                model.addRow(rentRecord);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
