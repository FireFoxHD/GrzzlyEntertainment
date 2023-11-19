package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeSignIn extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;

    public EmployeeSignIn() {
        setTitle("Employee Sign In");
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(300, 150);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                // Dummy validation for demonstration
                if (username.equals("employee") && password.equals("password")) {
                    JOptionPane.showMessageDialog(EmployeeSignIn.this, "Login successful!");
                    // Perform actions after successful login
                } else {
                    JOptionPane.showMessageDialog(EmployeeSignIn.this, "Invalid username or password!");
                }
            }
        });
        panel.add(signInButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(cancelButton);

        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Employee Sign In");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            EmployeeSignIn employeeSignIn = new EmployeeSignIn();
            frame.add(employeeSignIn);
            employeeSignIn.setVisible(true);

            frame.setSize(300, 200);
            frame.setVisible(true);
        });
    }
}

