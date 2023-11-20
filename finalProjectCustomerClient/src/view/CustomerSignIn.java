package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerSignIn extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signInButton;

    public CustomerSignIn() {
        initializeComponents();
        setLayout();
        addComponents();
        setWindowsProperty();
        addSignInListener();
    }

    private void initializeComponents() {
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        signInButton = new JButton("Sign In");
    }

    private void setLayout() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(signInButton);
        add(panel, BorderLayout.CENTER);
    }

    private void addComponents() {
        // Add action listener for sign-in button if needed
    }

    private void setWindowsProperty() {
        setTitle("Customer Sign In");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addSignInListener() {
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                // For demo purposes, just check if fields are not empty
                if (!username.isEmpty() && passwordField.getPassword().length > 0) {
                    // Assuming successful sign-in; create and show CustomerDashboard
                    new CustomerDashboard();
                    dispose(); // Close sign-in page after successful sign-in
                } else {
                    JOptionPane.showMessageDialog(view.CustomerSignIn.this,
                            "Invalid username or password",
                            "Sign In Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerSignIn();
        });
    }
}
