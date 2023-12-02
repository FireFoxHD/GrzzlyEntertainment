package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeSignIn extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;

    public EmployeeSignIn() {
        setTitle("Employee Sign In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                // For demo purposes, just check if fields are not empty
                if (!username.isEmpty() && passwordField.getPassword().length > 0) {
                    // Assuming successful sign-in; create and show ParentWindow
                    new ParentWindow();
                    //dispose(); // Close sign-in page after successful sign-in
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid username or password",
                            "Sign In Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(signInButton);

        add(panel);
        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeSignIn());
    }
}
