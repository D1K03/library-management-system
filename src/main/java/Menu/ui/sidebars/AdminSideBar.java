package Menu.ui.sidebars;

import Authentication.Main;
import javax.swing.*;
import java.awt.*;

public class AdminSideBar extends SideBar {

    public AdminSideBar(CardLayout cardLayout, JPanel switchPanel, Main main) {
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

        userBtn = new JButton("Members");
        userBtn.addActionListener(this);
        add(userBtn, "growx, wrap, height 50");

        issuedBtn = new JButton("Issued Books");
        issuedBtn.addActionListener(this);
        add(issuedBtn, "growx, wrap, height 50");

        addBtn = new JButton("Add Books");
        addBtn.addActionListener(this);
        add(addBtn, "growx, wrap, height 50");

        removeBtn = new JButton("Remove Books");
        removeBtn.addActionListener(this);
        add(removeBtn, "growx, wrap, height 50");

        JButton addLibrarianBtn = new JButton("Add Librarian");
        addLibrarianBtn.addActionListener(this);
        add(addLibrarianBtn, "growx, wrap, height 50");

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