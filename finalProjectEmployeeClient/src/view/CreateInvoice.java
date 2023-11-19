package view;

import javax.swing.JInternalFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateInvoice extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
    private JTable invoiceTable;

    public CreateInvoice() {
        setTitle("Create Invoice");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(600, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Item");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Price");
        invoiceTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(invoiceTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button to download
        JButton downloadButton = new JButton("Download Invoice");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to download the invoice
                // This could include generating a file or any specific action you want to perform
                // For simplicity, let's display a message
                JOptionPane.showMessageDialog(null, "Downloading Invoice...");
            }
        });
        mainPanel.add(downloadButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Invoice Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane, BorderLayout.CENTER);

            CreateInvoice createInvoice = new CreateInvoice();
            desktopPane.add(createInvoice);
            createInvoice.setVisible(true);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
