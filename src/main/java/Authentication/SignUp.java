package Authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;


public class SignUp extends JPanel implements ActionListener {
    HashMap<String,String> LoginData = new HashMap<String,String>();
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private JPanel registerPanel;
    private JTextField userEmail;
    private JPasswordField userPassword, confirmPassword;
    private JButton loginButton, resetPassword, newMember, oldMember;

    public SignUp(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createPanel();

        JTextField firstName = new JTextField();
        firstName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First name");

        JTextField  lastName = new JTextField();
        lastName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last name");

        userEmail = new JTextField();
        userEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email Address");

        JTextField phoneNumber = new JTextField();
        phoneNumber.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Phone number");

        userPassword = new JPasswordField();
        userPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        userPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");

        confirmPassword = new JPasswordField();
        confirmPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        confirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Confirm Password");

        JLabel askMember = new JLabel("Already have an account?");

        oldMember = new JButton("Login");
        oldMember.addActionListener(this);
        oldMember.setContentAreaFilled(false);
        oldMember.setBorderPainted(false);
        oldMember.setOpaque(false);

        loginButton = new JButton("Sign Up");
        loginButton.addActionListener(this);

        JLabel title = new JLabel("Sign Up");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold 24");

        registerPanel.add(title, "gapx 85");
        registerPanel.add(firstName, "gapy 50, height 30");
        registerPanel.add(lastName, "gapy 20, height 30");
        registerPanel.add(userEmail, "gapy 20, height 30");
        registerPanel.add(userPassword, "gapy 20, height 30");
        registerPanel.add(confirmPassword, "gapy 20, height 30");
        registerPanel.add(loginButton, "gapy 20, height 30");
        registerPanel.add(askMember, "gapy 10, split 2");
        registerPanel.add(oldMember);
    }

    private void createPanel() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        registerPanel = new JPanel(new MigLayout("wrap, insets 35 45", "fill, 250:280"));
        registerPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 5%);" +
                "[dark]background:lighten(@background, 5%)"
        );
        add(registerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == oldMember) {
            cardLayout.show(switchPanel, "login");
        }
    }
}