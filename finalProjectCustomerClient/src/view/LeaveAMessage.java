package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaveAMessage extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea messageTextArea;
    private JButton submitButton;

    public LeaveAMessage() {
        initializeComponents();
        setLayout();
        addComponents();
        setWindowProperties();
        addSubmitListener();
    }

    private void initializeComponents() {
        messageTextArea = new JTextArea(10, 20);
        submitButton = new JButton("Submit");
    }

    private void setLayout() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Leave a Message");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JScrollPane(messageTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void addComponents() {
        // Add action listener for submit button if needed
    }

    private void setWindowProperties() {
        setTitle("Leave a Message");
        setClosable(true);
        setResizable(true);
        setSize(400, 300);
        setVisible(true);
    }

    private void addSubmitListener() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle the submitted message
                String message = messageTextArea.getText();
                JOptionPane.showMessageDialog(LeaveAMessage.this,
                        "Message Submitted:\n" + message,
                        "Message Submitted", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the internal frame after submission
            }
        });
    }
}
