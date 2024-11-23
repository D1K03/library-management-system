package Authentication;

import Menu.ui.Home;
import Menu.ui.menupanels.ReturnMenu;
import com.formdev.flatlaf.fonts.roboto_mono.FlatRobotoMonoFont;
import net.miginfocom.swing.MigLayout;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private ReturnMenu returnMenu;

    public Main() {
        createFrame();
    }

    /**
     * Creates centralised frame for the rest of the other authentication panels
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
        switchPanel.add(new Login(cardLayout, switchPanel, this),"login");
        switchPanel.add(new SignUp(cardLayout, switchPanel, this), "signup");
        switchPanel.add(new ResetPassword(cardLayout, switchPanel, this), "resetpassword");

        add(switchPanel, "grow");
    }

    /**
     * Opens the main application after authentication
     * @param userRole permissions of user
     * @param userId primary key of user to uniquely identify them
     */
    public void openHomePanel(String userRole, int userId) {
        returnMenu = new ReturnMenu(cardLayout, switchPanel);
        switchPanel.add(new Home(cardLayout, switchPanel, this, userRole, userId, returnMenu), "home");
    }

    /**
     * Method to display panels
     * @param panelName name of the panel
     */
    public void showPanel(String panelName) {
        cardLayout.show(switchPanel, panelName);
    }


    public static void main(String[] args) {
        FlatRobotoMonoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoMonoFont.FAMILY, Font.PLAIN, 12));
        //Specifies theme, will change later to be user adjusted
        FlatDarkLaf.setup();
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}