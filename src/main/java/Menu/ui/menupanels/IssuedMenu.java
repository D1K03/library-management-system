package Menu.ui.menupanels;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IssuedMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;

    public IssuedMenu(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createIssued();
    }

    public void createIssued() {
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        JLabel headerLabel = new JLabel("Issued Books", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
