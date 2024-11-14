package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Members extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;

    public Members(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createUsers();
    }

    public void createUsers() {
        setBackground(Color.YELLOW);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
