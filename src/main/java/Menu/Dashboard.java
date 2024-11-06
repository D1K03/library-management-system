package Menu;


import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JPanel implements ActionListener {
    private JPanel dashPanel;

    public Dashboard() {
        createDash();
    }

    private void createDash() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        dashPanel = new JPanel(new MigLayout("wrap, insets 35 45", "fill, 250:280"));
        dashPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 5%);" +
                "[dark]background:lighten(@background, 5%)"
        );
        add(dashPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}