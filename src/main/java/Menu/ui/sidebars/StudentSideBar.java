package Menu.ui.sidebars;

import Authentication.Main;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class StudentSideBar extends SideBar {

    public StudentSideBar(CardLayout cardLayout, JPanel switchPanel, Main main) {
        super(cardLayout, switchPanel, main);
    }

    @Override
    protected void createButtons() {
        dashBtn = new JButton("Dashboard");
        bookBtn = new JButton("View Books");
        rentBtn = new JButton("My Books");
        aboutBtn = new JButton("About Author");
        logBtn = new JButton("Log Out");

        JButton[] arrBtn = {dashBtn, bookBtn, rentBtn, aboutBtn, logBtn};

        for (JButton btn : arrBtn) {
            btn.addActionListener(this);
            btn.setBorder(BorderFactory.createEmptyBorder());
            btn.setOpaque(false);
            add(btn, "growx, wrap, height 50");
        }

        add(logBtn, "growx, wrap, height 50, gapy 325");
    }
}