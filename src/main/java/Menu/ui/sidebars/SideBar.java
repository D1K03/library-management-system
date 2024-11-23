package Menu.ui.sidebars;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Authentication.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import com.formdev.flatlaf.FlatClientProperties;

/**
 * Abstract class so cannot be instantiated
 */
public abstract class SideBar extends JPanel implements ActionListener {
    protected JButton dashBtn, bookBtn, userBtn, issuedBtn, manageBtn, removeBtn, returnBtn, aboutBtn, logBtn, rentBtn, editRoleBtn;
    protected JLabel profileLabel;
    protected CardLayout cardLayout;
    protected JPanel switchPanel;
    protected Main main;


    public SideBar(CardLayout cardLayout, JPanel switchPanel, Main main) {
        this.main = main;
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createBar();
        createProfile();
        createButtons();

    }

    private void createBar() {
        setLayout(new MigLayout("insets 0", "0[grow]0", "0[]0"));
        putClientProperty(FlatClientProperties.STYLE,
                "[light]background:darken(@background, 5%);" +
                "[dark]background:lighten(@background, 5%)"
        );
        setPreferredSize(new Dimension(200, getHeight()));
    }

    /**
     * Protected and can only be accessed by child classes
     */
    protected abstract void createButtons();

    private void createProfile() {
        profileLabel = new JLabel();
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Create and set the FlatSVGIcon
        FlatSVGIcon profileIcon = new FlatSVGIcon("images/library-icon.svg", 75, 75);
        profileLabel.setIcon(profileIcon);

        add(profileLabel, "growx, wrap, height 75");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookBtn) {
            cardLayout.show(switchPanel, "books");
        }

        else if (e.getSource() == dashBtn) {
            cardLayout.show(switchPanel, "dashboard");
        }

        else if (e.getSource() == userBtn) {
            cardLayout.show(switchPanel, "members");
        }

        else if (e.getSource() == issuedBtn) {
            cardLayout.show(switchPanel, "issued");
        }

        else if (e.getSource() == rentBtn) {
            cardLayout.show(switchPanel, "userbooks");
        }

        else if (e.getSource() == manageBtn) {
            cardLayout.show(switchPanel, "manage");
        }

        else if (e.getSource() == returnBtn) {
            cardLayout.show(switchPanel, "return");
        }

        else if (e.getSource() == editRoleBtn) {
            cardLayout.show(switchPanel, "edit");
        }

        else if (e.getSource() == aboutBtn) {
            cardLayout.show(switchPanel, "about");
        }

        else if (e.getSource() == logBtn) {
            main.showPanel("login");
        }

    }
}
