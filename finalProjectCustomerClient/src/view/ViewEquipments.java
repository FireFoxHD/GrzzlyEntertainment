package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ViewEquipments extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> equipmentComboBox;
    private JTextField dateTextField;
    private JButton checkAvailabilityButton;
    private JLabel availabilityLabel;
    private JLabel quotationLabel;

    public ViewEquipments() {
        initializeComponents();
        setLayout();
        addComponents();
        setWindowProperties();
        addCheckAvailabilityListener();
    }

    private void initializeComponents() {
        equipmentComboBox = new JComboBox<>(new String[]{"Staging", "Lighting", "Sound", "Power"});
        dateTextField = new JTextField(10);
        checkAvailabilityButton = new JButton("Check Availability");
        availabilityLabel = new JLabel("Availability:");
        quotationLabel = new JLabel("Quotation:");
    }

    private void setLayout() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Select Equipment:"));
        panel.add(equipmentComboBox);
        panel.add(new JLabel("Enter Date (yyyy-MM-dd):"));
        panel.add(dateTextField);
        panel.add(new JLabel(""));
        panel.add(checkAvailabilityButton);
        panel.add(availabilityLabel);
        panel.add(quotationLabel);

        add(panel, BorderLayout.CENTER);
    }

    private void addComponents() {
        // Add action listener for Check Availability button if needed
    }

    private void setWindowProperties() {
        setTitle("View Equipments");
        setClosable(true);
        setResizable(true);
        setSize(400, 250);
        setVisible(true);
    }

    private void addCheckAvailabilityListener() {
        checkAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic for availability and quotation check based on equipment and date
                String selectedEquipment = (String) equipmentComboBox.getSelectedItem();
                LocalDate selectedDate = LocalDate.parse(dateTextField.getText()); // Assuming valid date input

                // Perform availability check and quotation based on selected equipment and date
                boolean isAvailable = checkAvailability(selectedEquipment, selectedDate);
                double quotation = generateQuotation(selectedEquipment);

                if (isAvailable) {
                    availabilityLabel.setText("Availability: Available");
                    quotationLabel.setText("Quotation: $" + quotation);
                } else {
                    availabilityLabel.setText("Availability: Not Available");
                    quotationLabel.setText("Quotation: Not Applicable");
                }
            }
        });
    }

    // Simulated availability check
    private boolean checkAvailability(String equipment, LocalDate date) {
        // Implement your availability check logic here based on equipment and date
        // Return true if available, false otherwise (for demonstration purposes)
        return true;
    }

    // Simulated quotation generation
    private double generateQuotation(String equipment) {
        // Implement your quotation generation logic based on selected equipment
        // Return a quotation value (for demonstration purposes)
        if (equipment.equals("Staging")) {
            return 500.0; // Example quotation for staging equipment
        } else if (equipment.equals("Lighting")) {
            return 300.0; // Example quotation for lighting equipment
        } else if (equipment.equals("Sound")) {
            return 400.0; // Example quotation for sound equipment
        } else if (equipment.equals("Power")) {
            return 200.0; // Example quotation for power equipment
        } else {
            return 0.0; // Default value if equipment not recognized
        }
    }
}
