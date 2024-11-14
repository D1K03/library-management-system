package Menu;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Authentication.Main;
import net.miginfocom.swing.MigLayout;
import com.formdev.flatlaf.FlatClientProperties;

public class SideBar extends JPanel implements ActionListener {
    private JButton dashBtn, bookBtn, userBtn, issuedBtn, addBtn, removeBtn, returnBtn, aboutBtn, logBtn;
    private JLabel profileLabel;
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private Main main;



    SideBar(CardLayout cardLayout, JPanel switchPanel, Main main) {
        this.main = main;
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createBar();
        createProfile();
        createButtons();

    }

    private void createBar() {
        setLayout(new MigLayout("insets 0", "0[grow]0", "0[]0"));
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 5%);" +
                "[dark]background:lighten(@background, 5%)"
        );
        setPreferredSize(new Dimension(200, getHeight()));
    }

    private void createButtons() {
        dashBtn = new JButton("Dashboard");
        dashBtn.setBorder(BorderFactory.createEmptyBorder());
        dashBtn.addActionListener(this);
        add(dashBtn, "growx, wrap, height 50");

        bookBtn = new JButton("View Books");
        bookBtn.setBorder(BorderFactory.createEmptyBorder());
        bookBtn.addActionListener(this);
        add(bookBtn, "growx, wrap, height 50");

        userBtn = new JButton("Members");
        userBtn.setBorder(BorderFactory.createEmptyBorder());
        userBtn.addActionListener(this);
        add(userBtn, "growx, wrap, height 50");

        issuedBtn = new JButton("Issued Books");
        issuedBtn.setBorder(BorderFactory.createEmptyBorder());
        issuedBtn.addActionListener(this);
        add(issuedBtn, "growx, wrap, height 50");

        addBtn = new JButton("Add Books");
        addBtn.setBorder(BorderFactory.createEmptyBorder());
        addBtn.addActionListener(this);
        add(addBtn, "growx, wrap, height 50");

        removeBtn = new JButton("Remove Books");
        removeBtn.setBorder(BorderFactory.createEmptyBorder());
        removeBtn.addActionListener(this);
        add(removeBtn, "growx, wrap, height 50");

        returnBtn = new JButton("Return Books");
        returnBtn.setBorder(BorderFactory.createEmptyBorder());
        returnBtn.addActionListener(this);
        add(returnBtn, "growx, wrap, height 50");

        aboutBtn = new JButton("About Author");
        aboutBtn.setBorder(BorderFactory.createEmptyBorder());
        aboutBtn.addActionListener(this);
        add(aboutBtn, "growx, wrap, height 50");

        logBtn = new JButton("Log Out");
        logBtn.setBorder(BorderFactory.createEmptyBorder());
        logBtn.addActionListener(this);
        add(logBtn, "growx, wrap, height 50, gapy 125");
    }

    private void createProfile() {
        profileLabel = new JLabel();
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        profileLabel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );
        add(profileLabel, "growx, wrap, height 75");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookBtn) {
            cardLayout.show(switchPanel, "books");
        }

        else if (e.getSource() == dashBtn) {
            cardLayout.show(switchPanel, "dashboard");
        }

        else if (e.getSource() == userBtn) {
            cardLayout.show(switchPanel, "members");
        }

        else if (e.getSource() == issuedBtn) {
            cardLayout.show(switchPanel, "issued");
        }

        else if (e.getSource() == addBtn) {
            cardLayout.show(switchPanel, "add");
        }

        else if (e.getSource() == removeBtn) {
            cardLayout.show(switchPanel, "remove");
        }

        else if (e.getSource() == returnBtn) {
            cardLayout.show(switchPanel, "return");
        }

        else if (e.getSource() == aboutBtn) {
            cardLayout.show(switchPanel, "about");
        }

        else if (e.getSource() == logBtn) {
            main.showPanel("login");
        }

    }
}
