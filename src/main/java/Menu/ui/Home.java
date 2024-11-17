package Menu.ui;


import Service.UserService;
import com.formdev.flatlaf.FlatClientProperties;
import Authentication.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private JButton test;
    private JLabel panelName;
    private SideBar sideBar;
    private Main main;
    private UserService userService = new UserService();
    private int userId;



    public Home(CardLayout cardLayout, JPanel switchPanel, Main main) {
        this.main = main;
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        this.userId = userId;
        createHome();
    }

    private void createHome() {
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        switchPanel = new JPanel(cardLayout);
        switchPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );
        switchPanel.add(new Dashboard(cardLayout, switchPanel),"dashboard");
        switchPanel.add(new BooksMenu(cardLayout, switchPanel), "books");
        switchPanel.add(new Members(cardLayout, switchPanel), "members");
        switchPanel.add(new Issued(cardLayout, switchPanel), "issued");
        switchPanel.add(new AddMenu(cardLayout, switchPanel), "add");
        switchPanel.add(new RemoveMenu(cardLayout, switchPanel), "remove");
        switchPanel.add(new ReturnMenu(cardLayout, switchPanel), "return");
        switchPanel.add(new AboutMenu(cardLayout, switchPanel), "about");

        sideBar = new SideBar(cardLayout, switchPanel, main);
        add(sideBar, BorderLayout.WEST);
        add(switchPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}