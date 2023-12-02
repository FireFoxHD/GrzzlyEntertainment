package view;

import javax.swing.*;

public class ViewRentalRequest extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea rentalRequestTextArea;

    public ViewRentalRequest() {
        // Create the internal frame with the given title and properties
        super("View Rental Requests", true, true, true, true);
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        // Initialize components
        rentalRequestTextArea = new JTextArea(20, 40);
        rentalRequestTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(rentalRequestTextArea);
        getContentPane().add(scrollPane); // Add text area to the content pane
    }

    private void setupLayout() {
        pack(); // Sizes the frame to fit the preferred size and layouts of its subcomponents
        setVisible(true); // Set the internal frame to be visible
    }

    public static void main(String[] args) {
        // Invoke GUI creation on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a desktop pane to hold internal frames
        JDesktopPane desktopPane = new JDesktopPane();
        frame.add(desktopPane);

        // Create the internal frame and add it to the desktop pane
        ViewRentalRequest internalFrame = new ViewRentalRequest();
        desktopPane.add(internalFrame);

        internalFrame.setSize(400, 300); // Set internal frame size
        internalFrame.setVisible(true); // Make internal frame visible

        frame.setSize(800, 600); // Set main frame size
        frame.setVisible(true); // Make main frame visible
    }
}
