package Menu.ui.sidebars;

import Authentication.Main;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class LibrarianSideBar extends SideBar {
    private JButton[] arrBtn = {dashBtn, bookBtn, userBtn, issuedBtn, manageBtn, removeBtn, returnBtn, aboutBtn, logBtn, rentBtn, editRoleBtn};

    public LibrarianSideBar(CardLayout cardLayout, JPanel switchPanel, Main main) {
        super(cardLayout, switchPanel, main);
    }

    @Override
    protected void createButtons() {
        dashBtn = new JButton("Dashboard");
        bookBtn = new JButton("View Books");
        manageBtn = new JButton("Manage Books");
        issuedBtn = new JButton("Issued Books");
        returnBtn = new JButton("Returned Books");
        aboutBtn = new JButton("About Author");
        logBtn = new JButton("Log Out");

        JButton[] arrBtn = {dashBtn, bookBtn, manageBtn, issuedBtn, returnBtn, aboutBtn, logBtn};

        for (JButton btn : arrBtn) {
            btn.addActionListener(this);
            btn.setBorder(BorderFactory.createEmptyBorder());
            btn.setOpaque(false);
            add(btn, "growx, wrap, height 50");

        }

        add(logBtn, "growx, wrap, height 50, gapy 225");
    }
}