package view;

import javax.swing.JInternalFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateQuotation extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
    private JTable quotationTable;

    public CreateQuotation() {
        setTitle("Create Quotation");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(600, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Item");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Unit Price");
        tableModel.addColumn("Total Price");
        quotationTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(quotationTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button to download
        JButton downloadButton = new JButton("Download Quotation");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to download the quotation
                // This could include generating a file or any specific action you want to perform
                // For simplicity, let's display a message
                JOptionPane.showMessageDialog(null, "Downloading Quotation...");
            }
        });
        mainPanel.add(downloadButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quotation Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane, BorderLayout.CENTER);

            CreateQuotation createQuotation = new CreateQuotation();
            desktopPane.add(createQuotation);
            createQuotation.setVisible(true);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
