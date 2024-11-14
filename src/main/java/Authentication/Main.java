package Authentication;

import Menu.*;
import com.formdev.flatlaf.fonts.roboto_mono.FlatRobotoMonoFont;
import net.miginfocom.swing.MigLayout;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel switchPanel;

    public Main() {
        createFrame();
    }

    /**
     * Creates centralised frame for the rest of the other panels
     */
    private void createFrame() {
        setTitle("Library Management");
        setSize(new Dimension(1200,700));
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(false);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        switchPanel = new JPanel(cardLayout);
        switchPanel.add(new Login(cardLayout, switchPanel),"login");
        switchPanel.add(new SignUp(cardLayout, switchPanel), "signup");
        switchPanel.add(new ResetPassword(cardLayout, switchPanel), "resetpassword");
        switchPanel.add(new Home(cardLayout, switchPanel, this), "home");

        add(switchPanel, "grow");
    }

    public void showPanel(String panelName) {
        cardLayout.show(switchPanel, panelName);
    }

    public static void main(String[] args) {
        FlatRobotoMonoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoMonoFont.FAMILY, Font.PLAIN, 12));
        FlatDarkLaf.setup();
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}