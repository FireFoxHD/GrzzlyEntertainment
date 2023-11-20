package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

public class WelcomeParentWindow extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktopPane;

    public WelcomeParentWindow() {
        setTitle("Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem createQuotationMenuItem = new JMenuItem("Create Quotation");
        createQuotationMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInternalFrame(new CreateQuotation());
            }
        });
        

        JMenuItem createInvoiceMenuItem = new JMenuItem("Create Invoice");
        createInvoiceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInternalFrame(new CreateInvoice());
            }
        });
        

        JMenuItem createReceiptMenuItem = new JMenuItem("Create Receipt");
        createReceiptMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInternalFrame(new CreateReceipt());
            }
        });
        

        JMenu employeeSignInMenu = new JMenu("Employee Sign In");
        employeeSignInMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInternalFrame(new EmployeeSignIn());
            }
        });
        

        JMenu respondToMessageMenu = new JMenu("Respond To Message");
        respondToMessageMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInternalFrame(new RespondToMessage("Sample message"));
            }
        });
        

        JMenu viewAllMessagesMenu = new JMenu("View All Messages");
        viewAllMessagesMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] messages = {"Message 1", "Message 2", "Message 3"}; // Replace with actual messages
                openInternalFrame(new ViewAllMessages(messages));
            }
        });
       

        JMenu viewEquipmentStockMenu = new JMenu("View Equipment Stock");
        viewEquipmentStockMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] equipmentData = {{"Equipment 1", 10}, {"Equipment 2", 15}, {"Equipment 3", 20}}; // Replace with actual data
                String[] columnNames = {"Equipment Name", "Quantity"}; // Replace with actual column names
                openInternalFrame(new ViewEquipmentStock(equipmentData, columnNames));
            }
        });
       

        JMenu viewListOfRentalRequestMenu = new JMenu("View List Of Rental Requests");
        viewListOfRentalRequestMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] requestData = {{"John Doe", "Projector", "2023-11-20", "Pending"}, {"Alice Smith", "Microphone", "2023-11-25", "Approved"}, {"Bob Johnson", "Laptop", "2023-11-18", "Rejected"}}; // Replace with actual data
                String[] columnNames = {"Requester", "Equipment", "Date", "Status"}; // Replace with actual column names
                openInternalFrame(new ViewListOfRentalRequest(requestData, columnNames));
            }
        });
        fileMenu.add(createInvoiceMenuItem);
        fileMenu.add(createQuotationMenuItem);
        fileMenu.add(createReceiptMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(employeeSignInMenu);
        menuBar.add(respondToMessageMenu);
        menuBar.add(viewAllMessagesMenu);
        menuBar.add(viewEquipmentStockMenu);
        menuBar.add(viewListOfRentalRequestMenu);
        setJMenuBar(menuBar);

        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private <T extends JInternalFrame> void openInternalFrame(T internalFrame) {
        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WelcomeParentWindow();
        });
    }
}
