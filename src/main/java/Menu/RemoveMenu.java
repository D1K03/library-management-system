package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;

    public RemoveMenu(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createRemove();
    }

    public void createRemove() {
        setBackground(Color.CYAN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
