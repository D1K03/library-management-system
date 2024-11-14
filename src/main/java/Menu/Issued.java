package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Issued extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;

    public Issued(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createIssued();
    }

    public void createIssued() {
        setBackground(Color.GREEN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
