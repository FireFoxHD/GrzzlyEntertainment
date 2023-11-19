package view;

import javax.swing.JInternalFrame;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class ViewEquipmentStock extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable equipmentTable;

    public ViewEquipmentStock(Object[][] equipmentData, String[] columnNames) {
        setTitle("Equipment Stock");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(500, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create table model with data and column names
        DefaultTableModel tableModel = new DefaultTableModel(equipmentData, columnNames);
        equipmentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(equipmentTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Equipment Stock Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane, BorderLayout.CENTER);

            // Example equipment data and column names
            Object[][] equipmentData = {
                    {"Equipment 1", 10},
                    {"Equipment 2", 15},
                    {"Equipment 3", 20},
                    // Add more equipment data as needed
            };

            String[] columnNames = {"Equipment Name", "Quantity"};

            ViewEquipmentStock viewEquipmentStock = new ViewEquipmentStock(equipmentData, columnNames);
            desktopPane.add(viewEquipmentStock);
            viewEquipmentStock.setVisible(true);

            frame.setSize(600, 400);
            frame.setVisible(true);
        });
    }
}

