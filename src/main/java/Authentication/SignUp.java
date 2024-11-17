package Authentication;

import javax.swing.*;
import java.awt.*;
import Authentication.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

import Menu.ui.Home;
import Service.UserService;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;


public class SignUp extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private JPanel registerPanel;
    private JTextField userEmail, firstName, lastName;
    private JPasswordField userPassword, confirmPassword;
    private JButton signupButton, resetPassword, newMember, oldMember;
    private UserService userService;
    private Main main;

    public SignUp(CardLayout cardLayout, JPanel switchPanel, Main main) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        this.main = main;
        this.userService = new UserService();
        createPanel();

        firstName = new JTextField();
        firstName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First name");

        lastName = new JTextField();
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

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);

        JLabel title = new JLabel("Sign Up");
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold 24");

        registerPanel.add(title, "gapx 85");
        registerPanel.add(firstName, "gapy 50, height 30");
        registerPanel.add(lastName, "gapy 20, height 30");
        registerPanel.add(userEmail, "gapy 20, height 30");
        registerPanel.add(userPassword, "gapy 20, height 30");
        registerPanel.add(confirmPassword, "gapy 20, height 30");
        registerPanel.add(signupButton, "gapy 20, height 30");
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

    private void resetSignUpData() {
        firstName.setText("");
        lastName.setText("");
        userEmail.setText("");
        userPassword.setText("");
        confirmPassword.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton && userPassword.getText().equals(confirmPassword.getText())) {
            String forename = firstName.getText();
            String surname = lastName.getText();
            String email = userEmail.getText();
            String password = String.valueOf(userPassword.getPassword());
            String role = "student"; // Default role
            Date registrationDate = Date.valueOf(LocalDate.now());

            if (userService.emailExists(email)) {
                JOptionPane.showMessageDialog(this, "User with Email is already Registered");
            } else {
                userService.addUser(forename, surname, email, role, registrationDate, password);
                resetSignUpData();
                main.openHomePanel(role);
                cardLayout.show(switchPanel, "home");
            }
        } else if (e.getSource() == oldMember) {
            resetSignUpData();
            cardLayout.show(switchPanel, "login");
        }
    }
}