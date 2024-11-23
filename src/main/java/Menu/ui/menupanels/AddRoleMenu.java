package Menu.ui.menupanels;

import Service.UserService;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoleMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel, rolePanel, headerPanel;
    private JComboBox userDrop, roleDrop;
    private JButton updateBtn;
    private UserService userService;
    private MembersMenu membersMenu;

    public AddRoleMenu(CardLayout cardLayout, JPanel switchPanel, MembersMenu membersMenu) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        this.membersMenu = membersMenu;
        userService = new UserService();
        createRole();
    }

    /**
     * Drop-down menu being designed for admin to update user roles
     */
    public void createRole() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));

        rolePanel = new JPanel(new MigLayout("wrap, insets 35 45", "fill, 250:280"));
        rolePanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 5%);" +
                "[dark]background:lighten(@background, 5%)"
        );
        add(rolePanel);

        JLabel headerLabel = new JLabel("Role Manager", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.decode("#383838"));
        add(headerLabel, BorderLayout.NORTH);

        JLabel title = new JLabel("Edit Roles");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold 24");
        rolePanel.add(title, "gapx 65");

        userDrop = new JComboBox<>();
        userDrop.addItem("");
        for (String userData : userService.getUserIdAndName()) {
            userDrop.addItem(userData);
        }
        userDrop.addActionListener(this);
        rolePanel.add(userDrop, BorderLayout.CENTER);
        rolePanel.add(userDrop, "gapy 50, height 30");

        String[] roles = {"", "Librarian", "Student"};
        roleDrop = new JComboBox<>(roles);
        roleDrop.addActionListener(this);
        rolePanel.add(roleDrop, "gapy 20, height 30");

        updateBtn = new JButton("Update Role");
        updateBtn.addActionListener(this);
        rolePanel.add(updateBtn, "gapy 20, height 30");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateBtn) {
            if (userDrop.getSelectedItem().equals("") || roleDrop.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(this, "Please select a valid user and role to update.");
            } else {
                String selectedUser = (String) userDrop.getSelectedItem();
                int selectedUserId = Integer.parseInt(String.valueOf(selectedUser.charAt(0)));
                String selectedRole = (String) roleDrop.getSelectedItem();
                int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to update " + selectedUser.substring(2) + "'s role to " + selectedRole + "?", "Confirm Update", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    userService.updateUserRole(selectedUserId, selectedRole.toLowerCase());
                    JOptionPane.showMessageDialog(this, "User's role has been successfully reassigned.");
                    userDrop.setSelectedItem("");
                    roleDrop.setSelectedItem("");
                    membersMenu.refreshUserTable();
                }
            }
        }
    }
}
