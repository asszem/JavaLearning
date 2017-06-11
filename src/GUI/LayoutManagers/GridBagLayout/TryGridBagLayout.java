package GUI.LayoutManagers.GridBagLayout;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class TryGridBagLayout {

	public static void createWindow() {
		JFrame aWindow = new JFrame("This is the Window Title");
		Toolkit theKit = aWindow.getToolkit();                             // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                        // Get screen size

		// Set the position to screen center & size to half screen size
		aWindow.setSize(wndSize.width / 2, wndSize.height / 2);             // Set window size
		aWindow.setLocationRelativeTo(null);                               	// Center window
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridbag = new GridBagLayout();                       	// Create a layout manager
		GridBagConstraints constraints = new GridBagConstraints();
		aWindow.getContentPane().setLayout(gridbag);                       	// Set the container layout mgr

		// Set constraints and add first button
		constraints.weightx = constraints.weighty = 10;					   	// Absolute value doesn't matter, could be 1, 100
		constraints.fill = GridBagConstraints.BOTH;                        	// Fill the space
		// GridBagLayout5
		constraints.fill = GridBagConstraints.NONE;					        // Do not fill the space, use padding
		constraints.ipadx = 30; 											// Pad 30 in x
		constraints.ipady = 10; 											// Pad 10 in y
		addButton(" Press ", constraints, gridbag, aWindow);                // Add the button

		// Set constraints and add second button
		// GridBagLayout1
		// constraints.gridwidth = GridBagConstraints.REMAINDER; // Rest of the row

		// GridBagLayout2
		constraints.weightx = 5.0; 											// Weight half of first
		// 5/(5+10)=0.3 -> one third of the size of first component
		constraints.insets = new java.awt.Insets(30, 30, 30, 30); 			// Left 30 & right 20
		constraints.gridwidth = GridBagConstraints.RELATIVE; 				// Rest of the row

		// GridBagLayout4
		constraints.gridheight = 2;											// Height 2x as the "press" button
		
		// GridBagLayout5
		constraints.fill = GridBagConstraints.BOTH;					        // Do not fill the space, use padding
		addButton("GO", constraints, gridbag, aWindow);                     // Create and add button

		// GridBagLayout3
		constraints.insets = new java.awt.Insets(0, 0, 0, 0); 					// No insets
		constraints.gridx = 0; 												// Begin new row
		constraints.gridwidth = 1; 											// Width as "Press"
		// GridBagLayout4
		constraints.gridheight = 1;
		// GridBagLayout5
		constraints.fill = GridBagConstraints.NONE;					        // Do not fill the space, use padding
		constraints.ipadx = 30; 											// Pad 30 in x
		constraints.ipady = 10; 											// Pad 10 in y
		addButton("Push", constraints, gridbag, aWindow); 					// Add button to content pane

		aWindow.setVisible(true);                                          // Display the window
	}

	static void addButton(String label, GridBagConstraints constraints, GridBagLayout layout, JFrame window) {
		// Create a Border object using a BorderFactory method
		Border edge = BorderFactory.createRaisedBevelBorder();

		JButton button = new JButton(label);                               // Create a button
		button.setBorder(edge);                                            // Add its border
		layout.setConstraints(button, constraints);                        // Set the constraints
		window.getContentPane().add(button);                               // Add button to content pane
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				createWindow();
			}
		});
	}
}