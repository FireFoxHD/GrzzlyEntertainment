package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RespondToMessage extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea messageTextArea;
    private JTextArea responseTextArea;

    public RespondToMessage(String message) {
        setTitle("Respond to Message");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(400, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());

        messageTextArea = new JTextArea(message);
        messageTextArea.setEditable(false);
        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        mainPanel.add(messageScrollPane, BorderLayout.NORTH);

        JLabel responseLabel = new JLabel("Your Response:");
        mainPanel.add(responseLabel, BorderLayout.CENTER);

        responseTextArea = new JTextArea(10, 30);
        responseTextArea.setEditable(true);
        responseTextArea.setLineWrap(true);
        JScrollPane responseScrollPane = new JScrollPane(responseTextArea);
        mainPanel.add(responseScrollPane, BorderLayout.SOUTH);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = responseTextArea.getText();
                // Here you could process the response, send it, or perform any required action
                JOptionPane.showMessageDialog(null, "Response Sent: " + response);
                dispose(); // Close the internal frame after sending the response
            }
        });
        mainPanel.add(sendButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Message Response System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane, BorderLayout.CENTER);

            // Example message content
            String messageContent = "Hello there! This is a sample message.";

            RespondToMessage respondToMessage = new RespondToMessage(messageContent);
            desktopPane.add(respondToMessage);
            respondToMessage.setVisible(true);

            frame.setSize(500, 400);
            frame.setVisible(true);
        });
    }
}
