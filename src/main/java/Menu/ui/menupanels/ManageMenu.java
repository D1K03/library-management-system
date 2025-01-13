package Menu.ui.menupanels;

import Service.BookService;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ManageMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel, managePanel;
    private JTextField bookTitle, bookAuthor, bookPublish, bookCategory, bookISBN, bookAvailable;
    private JButton addBtn;
    private BookService bookService;
    private BooksMenu booksMenu;


    public ManageMenu(CardLayout cardLayout, JPanel switchPanel, BooksMenu booksMenu) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        this.booksMenu = booksMenu;
        bookService = new BookService();
        createManage();

        bookTitle = new JTextField();
        bookTitle.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Title");

        bookAuthor = new JTextField();
        bookAuthor.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Author");

        bookPublish = new JTextField();
        bookPublish.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Publisher");

        bookCategory = new JTextField();
        bookCategory.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Category");

        bookISBN = new JTextField();
        bookISBN.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "International Standard Book Number");

        bookAvailable = new JTextField();
        bookAvailable.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Stock");

        addBtn = new JButton("Add");
        addBtn.addActionListener(this);

        JLabel title = new JLabel("Add Book");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold 24");

        managePanel.add(title, "gapx 85");
        managePanel.add(bookTitle, "gapy 50, height 30");
        managePanel.add(bookAuthor, "gapy 20, height 30");
        managePanel.add(bookPublish, "gapy 20, height 30");
        managePanel.add(bookCategory, "gapy 20, height 30");
        managePanel.add(bookISBN, "gapy 20, height 30");
        managePanel.add(bookAvailable, "gapy 20, height 30");
        managePanel.add(addBtn, "gapy 20, height 30");
    }

    public void createManage() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        managePanel = new JPanel(new MigLayout("wrap, insets 35 45", "fill, 250:280"));
        managePanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 5%);" +
                "[dark]background:lighten(@background, 5%)"
        );
        add(managePanel);

        JLabel headerLabel = new JLabel("Manage Books", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.decode("#383838"));
        add(headerLabel, BorderLayout.NORTH);
    }

    private void resetBookField() {
        bookTitle.setText("");
        bookAuthor.setText("");
        bookPublish.setText("");
        bookCategory.setText("");
        bookISBN.setText("");
        bookAvailable.setText("");
    }

    /**
     * New book is added to the database
     * Dashboard is then refreshed to reflect new changes
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            String isbnName = bookISBN.getText();
            String apiURL = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbnName + "&format=json&jscmd=data";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiURL))
                    .GET()
                    .build();

            System.out.println(apiURL);

            String titleName = bookTitle.getText();
            String authorName = bookAuthor.getText();
            String publishName = bookPublish.getText();
            String categoryName = bookCategory.getText();
            int stockValue = Integer.parseInt(bookAvailable.getText());
            int borrowedValue = 0;

            bookService.addBook(titleName, authorName, publishName, categoryName, isbnName, stockValue, borrowedValue);
            resetBookField();
            JOptionPane.showMessageDialog(this, "New Book has been added!");
            booksMenu.loadBooksData();
            booksMenu.refreshBookTable();

            for (Component comp : switchPanel.getComponents()) {
                if (comp instanceof Dashboard dashboard) {
                    dashboard.refreshBookCount();
                    break;
                }
            }
        }
    }
}
