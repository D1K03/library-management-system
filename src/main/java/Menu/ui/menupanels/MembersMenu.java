package Menu.ui.menupanels;

import Menu.tables.BookTable;
import Menu.tables.UserTable;
import Service.UserService;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MembersMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private UserTable UTable;
    private UserService userService;

    public MembersMenu(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        userService = new UserService();
        createUsers();
        loadUsersData();
    }

    public void createUsers() {
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        JLabel headerLabel = new JLabel("Member List", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        UTable = new UserTable();
        JScrollPane scrollPane = new JScrollPane(UTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadUsersData() {
        List<String[]> users = userService.getAllUsers();
        if (users != null) {
            DefaultTableModel model = UTable.getModel();
            for (String[] user : users) {
                model.addRow(user);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
