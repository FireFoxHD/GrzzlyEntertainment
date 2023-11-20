package view;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;

public class CreateReceipt extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea receiptArea;

    public CreateReceipt() {
        setTitle("Create Receipt");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(400, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Text area to display receipt
        receiptArea = new JTextArea();
        receiptArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        receiptArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(receiptArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button to print receipt
        JButton printButton = new JButton("Print Receipt");
        printButton.addActionListener(e -> {
            try {
                receiptArea.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        });
        mainPanel.add(printButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public void generateReceipt(String receiptContent) {
        receiptArea.setText(receiptContent);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Receipt Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane, BorderLayout.CENTER);

            CreateReceipt createReceipt = new CreateReceipt();
            desktopPane.add(createReceipt);
            createReceipt.setVisible(true);

            // Sample receipt content
            String receiptContent = "-------------------\n" +
                                     "   RECEIPT\n" +
                                     "-------------------\n" +
                                     "Stage A        $10.00\n" +
                                     "Stage Light    $20.00\n" +
                                     "Sound Sys.     $15.00\n" +
                                     "-------------------\n" +
                                     "Total        $45.00\n" +
                                     "-------------------\n" +
                                     "Thank you for shopping!";
            createReceipt.generateReceipt(receiptContent);

            frame.setSize(400, 600);
            frame.setVisible(true);
        });
    }
}

