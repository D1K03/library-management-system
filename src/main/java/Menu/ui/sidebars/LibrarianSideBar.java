package Menu.ui.sidebars;

import Authentication.Main;
import javax.swing.*;
import java.awt.*;

public class LibrarianSideBar extends SideBar {

    public LibrarianSideBar(CardLayout cardLayout, JPanel switchPanel, Main main) {
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

        issuedBtn = new JButton("Issued Books");
        issuedBtn.addActionListener(this);
        add(issuedBtn, "growx, wrap, height 50");

        manageBtn = new JButton("Manage Books");
        manageBtn.addActionListener(this);
        add(manageBtn, "growx, wrap, height 50");

        returnBtn = new JButton("Return Books");
        returnBtn.addActionListener(this);
        add(returnBtn, "growx, wrap, height 50");

        aboutBtn = new JButton("About Author");
        aboutBtn.addActionListener(this);
        add(aboutBtn, "growx, wrap, height 50");

        logBtn = new JButton("Log Out");
        logBtn.addActionListener(this);
        add(logBtn, "growx, wrap, height 50, gapy 125");
    }
}