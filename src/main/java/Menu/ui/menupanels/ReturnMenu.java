package Menu.ui.menupanels;

import Menu.tables.ReturnDataTable;
import Service.RentService;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReturnMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel switchPanel;
    private ReturnDataTable returnDataTable;
    private RentService rentService;

    public ReturnMenu(CardLayout cardLayout, JPanel switchPanel) {
        this.cardLayout = cardLayout;
        this.switchPanel = switchPanel;
        createReturn();
        returnDataTable = new ReturnDataTable();
        rentService = new RentService();
        add(new JScrollPane(returnDataTable), BorderLayout.CENTER);
        loadReturnedData();
    }

    public void createReturn() {
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background, 10%);" +
                "[dark]background:lighten(@background, 10%)"
        );

        JLabel headerLabel = new JLabel("Return History", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

    }

    /**
     * Updates return history by adding records into the ReturnDataTable
     */
    public void addReturnRecord(String userId, String bookId, String title, String borrowedDate, String dueDate, String returnDate, boolean overdue) {
        returnDataTable.getModel().addRow(new Object[]{userId, bookId, title, borrowedDate, dueDate, returnDate, overdue});
    }

    public void loadReturnedData() {
        rentService.updateOverdueStatus();
        List<String[]> returnedRecords = rentService.getReturnedRecords();
        if (returnedRecords != null) {
            DefaultTableModel model = returnDataTable.getModel();
            for (String[] record : returnedRecords) {
                model.addRow(record);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
