package view;

import javax.swing.JInternalFrame;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ViewListOfRentalRequest extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable rentalRequestTable;

    public ViewListOfRentalRequest(Object[][] requestData, String[] columnNames) {
        setTitle("List of Rental Requests");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(600, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create table model with data and column names
        DefaultTableModel tableModel = new DefaultTableModel(requestData, columnNames);
        rentalRequestTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(rentalRequestTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rental Request Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane, BorderLayout.CENTER);

            // Example rental request data and column names
            Object[][] requestData = {
                    {"John Doe", "Projector", "2023-11-20", "Pending"},
                    {"Alice Smith", "Microphone", "2023-11-25", "Approved"},
                    {"Bob Johnson", "Laptop", "2023-11-18", "Rejected"},
                    // Add more rental request data as needed
            };

            String[] columnNames = {"Requester", "Equipment", "Date", "Status"};

            ViewListOfRentalRequest viewListOfRentalRequest = new ViewListOfRentalRequest(requestData, columnNames);
            desktopPane.add(viewListOfRentalRequest);
            viewListOfRentalRequest.setVisible(true);

            frame.setSize(700, 400);
            frame.setVisible(true);
        });
    }
}
