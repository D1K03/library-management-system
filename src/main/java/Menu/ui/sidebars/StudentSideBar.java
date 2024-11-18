package Menu.ui.sidebars;

import Authentication.Main;
import javax.swing.*;
import java.awt.*;

public class StudentSideBar extends SideBar {

    public StudentSideBar(CardLayout cardLayout, JPanel switchPanel, Main main) {
        super(cardLayout, switchPanel, main);
    }

    @Override
    protected void createButtons() {
        dashBtn = new JButton("Dashboard");
        dashBtn.addActionListener(this);
        add(dashBtn, "growx, wrap, height 50");

        bookBtn = new JButton("View Books");
        bookBtn.addActionListener(this);
        add(bookBtn, "growx, wrap, height 50");

        rentBtn = new JButton("My Books");
        rentBtn.addActionListener(this);
        add(rentBtn, "growx, wrap, height 50");

        aboutBtn = new JButton("About Author");
        aboutBtn.addActionListener(this);
        add(aboutBtn, "growx, wrap, height 50");

        logBtn = new JButton("Log Out");
        logBtn.addActionListener(this);
        add(logBtn, "growx, wrap, height 50, gapy 125");
    }
}