package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewListOfPastTransactions extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Object[][] SAMPLE_DATA = {
            {"1", "Customer A", "Equipment X", "2023-11-01", "2023-11-03", "$200"},
            {"2", "Customer B", "Equipment Y", "2023-11-02", "2023-11-04", "$300"},
            {"3", "Customer C", "Equipment Z", "2023-11-03", "2023-11-05", "$400"},
    };

    private static final String[] COLUMN_NAMES = {"Transaction ID", "Customer Name", "Equipment", "Start Date", "End Date", "Amount"};

    public ViewListOfPastTransactions() {
        initializeComponents();
        setLayout();
        setDataToTable();
        setWindowProperties();
    }

    private void initializeComponents() {
        JTable transactionTable = new JTable(new DefaultTableModel(SAMPLE_DATA, COLUMN_NAMES));
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane);
    }

    private void setLayout() {
        setLayout(new BorderLayout());
    }

    private void setDataToTable() {
        // Here you could fetch actual transaction data from your database and populate the table dynamically
    }

    private void setWindowProperties() {
        setTitle("View List of Past Transactions");
        setClosable(true);
        setResizable(true);
        setSize(600, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewListOfPastTransactions view = new ViewListOfPastTransactions();
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //view.setLocationRelativeTo(null);
        });
    }
}
