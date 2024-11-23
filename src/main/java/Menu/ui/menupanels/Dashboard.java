package Menu.ui.menupanels;


import Service.BookService;
import Service.RentService;
import Service.UserService;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Dashboard extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel, gridContainer;
    private JLabel tUsersLabel, tBooksLabel, issuedLabel, returnedLabel;
    private UserService userService;
    private BookService bookService;
    private RentService rentService;


    public Dashboard(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        userService = new UserService();
        bookService = new BookService();
        rentService = new RentService();
        createDash();
        getUserCount();
        getBookCount();
        getIssuedCount();
        getReturnCount();
    }

    private void createDash() {
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        JLabel headerLabel = new JLabel("Dashboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        gridContainer = new JPanel(new MigLayout("wrap 4", "[grow, fill]", "[]10[]"));

        tUsersLabel = new JLabel("0");
        tBooksLabel = new JLabel("0");
        issuedLabel = new JLabel("0");
        returnedLabel = new JLabel("0");

        gridContainer.add(createBox("Total Users", tUsersLabel, "images/user-total-icon.svg"));
        gridContainer.add(createBox("Total Unique Books", tBooksLabel, "images/books-over.svg"));
        gridContainer.add(createBox("Issued Books", issuedLabel, "images/user-out-icon.svg"));
        gridContainer.add(createBox("Returned Books (Last 30 Days)", returnedLabel, "images/user-in-icon.svg"));

        add(gridContainer, BorderLayout.CENTER);
    }

    /**
     * Total amount of unique books in library, aka copies not included
     */
    private void getBookCount() {
        try {
            int totalBooks = bookService.countBooks();
            tBooksLabel.setText(String.valueOf(totalBooks));
        } catch (SQLException e) {
            e.printStackTrace();
            tBooksLabel.setText("Error");
        }
    }

    /**
     * Retrieves updated book count after new book is added
     */
    public void refreshBookCount() {
        getBookCount();
    }

    private void getUserCount() {
        try {
            int totalUsers = userService.countUsers();
            tUsersLabel.setText(String.valueOf(totalUsers));
        } catch (SQLException e) {
            e.printStackTrace();
            tUsersLabel.setText("Error");
        }
    }

    public void refreshUserCount() {
        getUserCount();
    }


    private void getReturnCount() {
        List<String[]> returnedRecords = rentService.getReturnedRecords();
        int count = 0;
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());
        for (String[] record : returnedRecords) {
            Timestamp returnedDate = Timestamp.valueOf(record[6]);
            if (returnedDate != null && returnedDate.after(Timestamp.valueOf(currentDate.toLocalDateTime().minusDays(30)))) {
                count++;
            }
        }
        returnedLabel.setText(Integer.toString(count));
    }

    public void refreshReturnCount() {
        getReturnCount();
    }

    private void getIssuedCount() {
        List<String[]> issuedRecords = rentService.getIssuedRecords();
        int count = 0;
        for (String[] record : issuedRecords) {
                count++;
        }
        issuedLabel.setText(Integer.toString(count));
    }

    public void refreshIssuedCount() {
        getIssuedCount();
    }

    /**
     * Groups analytics about database to be displayed on the dashboard
     * @param title header
     * @param valueLabel value associated
     * @param iconPath SVG image
     * @return panel to display analytical data
     */
    private JPanel createBox(String title, JLabel valueLabel, String iconPath) {
        JPanel box = new JPanel();
        box.setLayout(new BorderLayout());;
        box.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        FlatSVGIcon image = new FlatSVGIcon(iconPath, 200, 200);

        JLabel iconLabel = new JLabel(image);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        box.add(iconLabel, BorderLayout.NORTH);
        box.add(valueLabel, BorderLayout.CENTER);
        box.add(titleLabel, BorderLayout.SOUTH);

        return box;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}