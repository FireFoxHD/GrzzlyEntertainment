package view;

import javax.swing.*;
import java.awt.*;


public class RespondToMessage extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea messageTextArea;
    private JTextField customerIDField;
    private JButton sendButton;

    public RespondToMessage() {
        super("Respond to Message", true, true, true, true);

        // Create components
        messageTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        customerIDField = new JTextField(10);
        new JTextField(10);
        sendButton = new JButton("Send");
   

        // Set layout
        setLayout(new BorderLayout());

        // Panel to hold text fields
        JPanel fieldsPanel = new JPanel(new FlowLayout());
        fieldsPanel.add(new JLabel("Customer ID:"));
        fieldsPanel.add(customerIDField);

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
