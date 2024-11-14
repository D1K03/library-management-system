package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;

    public ReturnMenu(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createReturn();
    }

    public void createReturn() {
        setBackground(Color.ORANGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
