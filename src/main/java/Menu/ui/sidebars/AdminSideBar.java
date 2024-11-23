package Menu.ui.sidebars;

import Authentication.Main;
import javax.swing.*;
import java.awt.*;

public class AdminSideBar extends SideBar {

    public AdminSideBar(CardLayout cardLayout, JPanel switchPanel, Main main) {
        super(cardLayout, switchPanel, main);
    }

    /**
     * Polymorphism of SideBar, createButtons method is changed to be tailored to users with admin permissions
     */
    @Override
    protected void createButtons() {
        dashBtn = new JButton("Dashboard");
        dashBtn.addActionListener(this);
        add(dashBtn, "growx, wrap, height 50");

        bookBtn = new JButton("View Books");
        bookBtn.addActionListener(this);
        add(bookBtn, "growx, wrap, height 50");

        userBtn = new JButton("Members");
        userBtn.addActionListener(this);
        add(userBtn, "growx, wrap, height 50");

        manageBtn = new JButton("Manage Books");
        manageBtn.addActionListener(this);
        add(manageBtn, "growx, wrap, height 50");

        issuedBtn = new JButton("Issued Books");
        issuedBtn.addActionListener(this);
        add(issuedBtn, "growx, wrap, height 50");

        returnBtn = new JButton("Returned Books");
        returnBtn.addActionListener(this);
        add(returnBtn, "growx, wrap, height 50");

        editRoleBtn = new JButton("Role Manager");
        editRoleBtn.addActionListener(this);
        add(editRoleBtn, "growx, wrap, height 50");

        aboutBtn = new JButton("About Author");
        aboutBtn.addActionListener(this);
        add(aboutBtn, "growx, wrap, height 50");

        logBtn = new JButton("Log Out");
        logBtn.addActionListener(this);
        add(logBtn, "growx, wrap, height 50, gapy 125");
    }
}