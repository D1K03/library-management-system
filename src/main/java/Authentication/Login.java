package Authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

public class Login extends JPanel implements ActionListener {
    HashMap<String,String> LoginData = new HashMap<String,String>();
    private CardLayout cardLayout;
    private JPanel switchPanel, loginPanel;
    private JTextField userEmail;
    private JPasswordField userPassword;
    private JButton loginButton, resetPassword, newMember;

    public Login(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createPanel();

        LoginData.put("","");
        userEmail = new JTextField();
        userEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email Address");

        userPassword = new JPasswordField();
        userPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        userPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        resetPassword = new JButton("Forgot Password?");
        resetPassword.addActionListener(this);
        resetPassword.setContentAreaFilled(false);
        resetPassword.setBorderPainted(false);
        resetPassword.setOpaque(false);

        newMember = new JButton("Sign Up");
        newMember.addActionListener(this);
        newMember.setContentAreaFilled(false);
        newMember.setBorderPainted(false);
        newMember.setOpaque(false);

        JLabel title = new JLabel("Login");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold 24");

        JCheckBox rememberMe = new JCheckBox("Remember Me");

        JLabel askMember = new JLabel("Don't have an account?");

        loginPanel.add(title, "gapx 100");
        loginPanel.add(userEmail, "gapy 50, height 30");
        loginPanel.add(userPassword, "gapy 20, height 30");
        loginPanel.add(rememberMe, "gapy 10, split 2");
        loginPanel.add(resetPassword);
        loginPanel.add(loginButton, "gapy 10, height 30");
        loginPanel.add(askMember, "gapy 10, split 2");
        loginPanel.add(newMember);
    }

    private void createPanel() {
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        loginPanel = new JPanel(new MigLayout("wrap, insets 35 45", "fill, 250:280"));
        loginPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                        "[light]background:darken(@background, 5%);" +
                        "[dark]background:lighten(@background, 5%)"
        );
        add(loginPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userID = userEmail.getText();
            String password = String.valueOf(userPassword.getPassword());

            if (LoginData.containsKey(userID)) {
                if (LoginData.get(userID).equals(password)) {
                    //Sign In Successful
                    System.out.println("Sign in Successful");
                    cardLayout.show(switchPanel, "dashboard");
                } else {
                    //Incorrect Password
                    System.out.println("Incorrect Email or Password");
                }
            } else {
                //Not Signed Up
                System.out.println("Incorrect Email or Password");
            }
        }

        else if (e.getSource() == newMember) {
            cardLayout.show(switchPanel, "signup");
        }

        else if (e.getSource() == resetPassword) {
            cardLayout.show(switchPanel, "resetpassword");
        }
    }
}
