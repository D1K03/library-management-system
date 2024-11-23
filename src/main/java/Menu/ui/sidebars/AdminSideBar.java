package Menu.ui.sidebars;

import Authentication.Main;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class AdminSideBar extends SideBar {
    private JButton[] arrBtn = {dashBtn, bookBtn, userBtn, issuedBtn, manageBtn, removeBtn, returnBtn, aboutBtn, logBtn, rentBtn, editRoleBtn};

    public AdminSideBar(CardLayout cardLayout, JPanel switchPanel, Main main) {
        super(cardLayout, switchPanel, main);
    }

    /**
     * Polymorphism of SideBar, createButtons method is changed to be tailored to users with admin permissions
     */
    @Override
    protected void createButtons() {
        dashBtn = new JButton("Dashboard");
        bookBtn = new JButton("View Books");
        userBtn = new JButton("Members");
        manageBtn = new JButton("Manage Books");
        issuedBtn = new JButton("Issued Books");
        returnBtn = new JButton("Returned Books");
        editRoleBtn = new JButton("Role Manager");
        aboutBtn = new JButton("About Author");
        logBtn = new JButton("Log Out");

        JButton[] arrBtn = {dashBtn, bookBtn, userBtn, manageBtn, issuedBtn, returnBtn, editRoleBtn, aboutBtn, logBtn};

        for (JButton btn : arrBtn) {
            btn.addActionListener(this);
            btn.setBorder(BorderFactory.createEmptyBorder());
            btn.setOpaque(false);
            add(btn, "growx, wrap, height 50");
        }

        add(logBtn, "growx, wrap, height 50, gapy 125");
    }
}