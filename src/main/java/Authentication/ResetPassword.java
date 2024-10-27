package Authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

public class ResetPassword extends JPanel implements ActionListener {
    HashMap<String,String> LoginData = new HashMap<String,String>();
    private CardLayout cardLayout;
    private JPanel switchPanel, resetPanel;
    private JTextField userEmail;
    private JButton resetButton, returnLogin;

    public ResetPassword(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createPanel();

        LoginData.put("","");
        JTextField userEmail = new JTextField();
        userEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email Address");

        resetButton = new JButton("Reset Password");
        resetButton.addActionListener(this);

        returnLogin = new JButton("Return to Login");
        returnLogin.addActionListener(this);
        returnLogin.setContentAreaFilled(false);
        returnLogin.setBorderPainted(false);
        returnLogin.setOpaque(false);

        JLabel title = new JLabel("Reset Password");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold 24");


        resetPanel.add(title, "gapx 35");
        resetPanel.add(userEmail, "gapy 50, height 30");
        resetPanel.add(resetButton, "gapy 20, height 30");
        resetPanel.add(returnLogin,"gapy 10");
    }

    private void createPanel() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        resetPanel = new JPanel(new MigLayout("wrap, insets 35 45", "fill, 250:280"));
        resetPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 5%);" +
                "[dark]background:lighten(@background, 5%)"
        );
        add(resetPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnLogin) {
            cardLayout.show(switchPanel, "login");
        }
    }
}
