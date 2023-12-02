package view;

import javax.swing.*;
import java.awt.*;

public class ScheduleEquipmentForEvent extends JInternalFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField eventIdTextField;
    private JButton searchEventButton;
    private JButton scheduleEquipmentButton;

    public ScheduleEquipmentForEvent() {
        super("Schedule Equipment for Event", true, true, true, true);
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        eventIdTextField = new JTextField(20);
        searchEventButton = new JButton("Search Event");
        scheduleEquipmentButton = new JButton("Schedule Equipment");
    }

    private void setupLayout() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Event ID:"));
        panel.add(eventIdTextField);
        panel.add(searchEventButton);
        panel.add(scheduleEquipmentButton);

        add(panel);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane);

            ScheduleEquipmentForEvent internalFrame = new ScheduleEquipmentForEvent();
            desktopPane.add(internalFrame);
            internalFrame.setVisible(true);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}

