package view;

import javax.swing.JInternalFrame;

import javax.swing.*;
import java.awt.*;

public class ViewAllMessages extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea messageTextArea;

    public ViewAllMessages(String[] messages) {
        setTitle("View All Messages");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(400, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());

        messageTextArea = new JTextArea(20, 30);
        messageTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Display all messages in the text area
        for (String message : messages) {
            messageTextArea.append(message + "\n\n");
        }

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("All Messages Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane, BorderLayout.CENTER);

            // Example messages
            String[] messages = {
                    // Add more messages as needed
            };

            ViewAllMessages viewAllMessages = new ViewAllMessages(messages);
            desktopPane.add(viewAllMessages);
            viewAllMessages.setVisible(true);

            frame.setSize(500, 400);
            frame.setVisible(true);
        });
    }
}

