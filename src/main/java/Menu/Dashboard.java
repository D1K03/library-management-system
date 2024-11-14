package Menu;


import Authentication.Login;
import Authentication.ResetPassword;
import Authentication.SignUp;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;


    public Dashboard(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createDash();
    }

    private void createDash() {
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}