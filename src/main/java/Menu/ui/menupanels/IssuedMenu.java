package Menu.ui.menupanels;

import Menu.tables.RentDataTable;
import Service.BookService;
import Service.RentService;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class IssuedMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private RentDataTable allRentTable;
    private BookService bookService;
    private RentService rentService;

    public IssuedMenu(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        bookService = new BookService();
        rentService = new RentService();
        createIssued();
        loadRentData();
    }

    public void createIssued() {
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        JLabel headerLabel = new JLabel("Issued Books", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        allRentTable = new RentDataTable();
        JScrollPane scrollPane = new JScrollPane(allRentTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadRentData() {
        rentService.updateOverdueStatus();
        List<String[]> issuedRecords = rentService.getIssuedRecords();
        if (issuedRecords != null) {
            DefaultTableModel model = allRentTable.getModel();
            for (String[] rentRecord : issuedRecords) {
                rentRecord[7] = rentRecord[7].equals("0") ? "false" : "true";
                model.addRow(rentRecord);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
