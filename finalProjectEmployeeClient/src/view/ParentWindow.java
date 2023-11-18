package view;

import javax.swing.*;

public class ParentWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;

    public ParentWindow() {
        setTitle("Rental Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();

        CustomerPanel customerPanel = new CustomerPanel();
        EmployeePanel employeePanel = new EmployeePanel();

        tabbedPane.addTab("Customer Management", customerPanel);
        tabbedPane.addTab("Employee Management", employeePanel);

        getContentPane().add(tabbedPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ParentWindow();
            }
        });
    }
}
