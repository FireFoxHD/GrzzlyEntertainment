package view;

import javax.swing.*;

import client.com.Client;
import models.com.Message;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RespondToMessage extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea messageTextArea;
    private JTextField customerIDField;
    private JTextField employeeIDField;
    private JButton sendButton;

    public RespondToMessage() {
        super("Respond to Message", true, true, true, true);

        // Create components
        messageTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        customerIDField = new JTextField(10);
        employeeIDField = new JTextField(10);
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from text fields
                String customerID = customerIDField.getText();
                String employeeID = employeeIDField.getText();
                
                 Client client = new Client();
                 Message message = new Message();
                 message.setCustomerID(customerIDField.getText());
                 message.setEmployeeID(employeeIDField.getText());
                 message.setMessageContent(messageTextArea.getText());
                 client.sendAction("Add Message");
                 client.sendMessage(message);
            }
        });

        // Set layout
        setLayout(new BorderLayout());

        // Panel to hold text fields
        JPanel fieldsPanel = new JPanel(new FlowLayout());
        fieldsPanel.add(new JLabel("Customer ID:"));
        fieldsPanel.add(customerIDField);
        fieldsPanel.add(new JLabel("Employee ID:"));
        fieldsPanel.add(employeeIDField);

        // Add components to the internal frame
        add(fieldsPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);

        // Set size and make the internal frame visible
        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane);

            RespondToMessage internalFrame = new RespondToMessage();
            desktopPane.add(internalFrame);
            internalFrame.setVisible(true);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
