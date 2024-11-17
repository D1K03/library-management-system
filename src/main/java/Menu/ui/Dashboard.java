package Menu.ui;


import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel, gridContainer;
    private JLabel tUsersLabel, tBooksLabel, issuedLabel, returnedLabel;


    public Dashboard(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createDash();
    }

    private void createDash() {
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        JLabel headerLabel = new JLabel("Dashboard", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        gridContainer = new JPanel(new MigLayout("wrap 4", "[grow, fill]", "[]10[]"));

        tUsersLabel = new JLabel("0");
        tBooksLabel = new JLabel("0");
        issuedLabel = new JLabel("0");
        returnedLabel = new JLabel("0");

        gridContainer.add(createBox("Total Users", tUsersLabel, "images/user-total-icon.svg"));
        gridContainer.add(createBox("Total Books", tBooksLabel, "images/books-over.svg"));
        gridContainer.add(createBox("Issued Books", issuedLabel, "images/user-out-icon.svg"));
        gridContainer.add(createBox("Returned Books", returnedLabel, "images/user-in-icon.svg"));

        add(gridContainer, BorderLayout.CENTER);
    }

    private JPanel createBox(String title, JLabel valueLabel, String iconPath) {
        JPanel box = new JPanel();
        box.setLayout(new BorderLayout());;
        box.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        FlatSVGIcon image = new FlatSVGIcon(iconPath, 200, 200);

        JLabel iconLabel = new JLabel(image);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        box.add(iconLabel, BorderLayout.NORTH);
        box.add(valueLabel, BorderLayout.CENTER);
        box.add(titleLabel, BorderLayout.SOUTH);

        return box;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}