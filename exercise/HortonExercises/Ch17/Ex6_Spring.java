package HortonExercises.Ch17;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class Ex6_Spring {

	public static void createWindow() {
		JFrame mainJFrame = new JFrame();
		mainJFrame.setTitle("Exercise 6 - Spring");
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension jFrameSize = new Dimension(600, 600);
//		mainJFrame.setSize(jFrameSize);
		mainJFrame.setLocationRelativeTo(null);

		Container contentPane = mainJFrame.getContentPane();
		createSpringLayout2(jFrameSize, mainJFrame, contentPane);
		
		
		//Set the window size
		mainJFrame.pack();
		mainJFrame.setVisible(true);
	}
	// Attempt 2.

	public static void createSpringLayout2(Dimension jFrameSize, JFrame mainJFrame, Container contentPane) {
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		// Final constants for button index reference B6 = Button 6
		final int B6 = 0;
		final int B5 = 1;
		final int B4 = 2;
		final int B3 = 3;
		final int B2 = 4;
		final int B1 = 5;
		
		//Create 6 button objects
		JButton[] buttons = new JButton[6];
		SpringLayout.Constraints[] buttonConstraints = new SpringLayout.Constraints[buttons.length];
//		Dimension buttonSize = new Dimension(100, 25);
		for (int i = 0; i < 6; i++) {
			buttons[i] = new JButton("Press " + (6 - i));
//			buttons[i].setPreferredSize(buttonSize);				// Set size for button
			contentPane.add(buttons[i]);							// Add the button to content pane
			buttonConstraints[i] = layout.getConstraints(buttons[i]); // Get the constraints for the current button
		}

		int pref=30;
		Spring ySpring = Spring.constant(10, pref, 50);
		Spring xSpring = Spring.constant(10, pref, 100);
		Spring halfButtonWidth =Spring.constant(Spring.width(buttons[0]).getValue()/2);
		Spring halfSpringWidht = Spring.constant(halfButtonWidth.getValue()/2);
		
		
		//Set Horizontal Constraints
//		buttonConstraints[B6].setX(Spring.sum(xSpring, buttonWidth));
//		buttonConstraints[B4].setX(Spring.sum(Spring.sum(xSpring, halfSpringWidht), halfButtonWidth));
		buttonConstraints[B1].setX(xSpring);
		
		layout.putConstraint(SpringLayout.WEST, buttons[B2], xSpring, SpringLayout.EAST, buttons[B1]);
		layout.putConstraint(SpringLayout.WEST, buttons[B3], xSpring, SpringLayout.EAST, buttons[B2]);
		layout.putConstraint(SpringLayout.WEST, buttons[B5], xSpring, SpringLayout.EAST, buttons[B4]);
		
		layout.putConstraint(SpringLayout.WEST, buttons[B6], 0, SpringLayout.WEST, buttons[B2]);
		
		layout.putConstraint(SpringLayout.EAST, buttons[B4], Spring.minus(halfSpringWidht), SpringLayout.HORIZONTAL_CENTER, buttons[B2]);
		
		
		//Set Vertical Constraints
		buttonConstraints[B6].setY(ySpring);
		// Set the vertical constraints for 6-4-5
		layout.putConstraint(SpringLayout.NORTH, buttons[B4], ySpring, SpringLayout.SOUTH, buttons[B6]);
		layout.putConstraint(SpringLayout.NORTH, buttons[B5], ySpring, SpringLayout.SOUTH, buttons[B6]);
		// Set the vertical constraints for 1-2-3
		layout.putConstraint(SpringLayout.NORTH, buttons[B1], ySpring, SpringLayout.SOUTH, buttons[B4]);
		layout.putConstraint(SpringLayout.NORTH, buttons[B2], ySpring, SpringLayout.SOUTH, buttons[B4]);
		layout.putConstraint(SpringLayout.NORTH, buttons[B3], ySpring, SpringLayout.SOUTH, buttons[B4]);
		//Set the content pane constraint for SOUTH and EAST edge, relative to button 3
		SpringLayout.Constraints contentPaneConstraints = layout.getConstraints(contentPane);
		contentPaneConstraints.setConstraint(SpringLayout.EAST,
				Spring.sum(buttonConstraints[B3].getConstraint(SpringLayout.EAST), xSpring));
		contentPaneConstraints.setConstraint(SpringLayout.SOUTH,
				Spring.sum(buttonConstraints[B3].getConstraint(SpringLayout.SOUTH), ySpring));
	}

	// Attempt 1.
	public static void createSpringLayout1(Dimension jFrameSize, JFrame mainJFrame, Container contentPane) {

		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);

		// Create the button objects and their constraints
		// Final constants for button index reference B6 = Button 6
		final int B6 = 0;
		final int B5 = 1;
		final int B4 = 2;
		final int B3 = 3;
		final int B2 = 4;
		final int B1 = 5;

		JButton[] buttons = new JButton[6];
		SpringLayout.Constraints[] buttonConstraints = new SpringLayout.Constraints[buttons.length];
//		Dimension buttonSize = new Dimension(100, 25);
		for (int i = 0; i < 6; i++) {
			buttons[i] = new JButton("Press " + (6 - i));
//			buttons[i].setPreferredSize(buttonSize);				// Set size for button
			contentPane.add(buttons[i]);							// Add the button to content pane
			buttonConstraints[i] = layout.getConstraints(buttons[i]); // Get the constraints for the current button
		}

		// Create the Springs
		System.out.println(buttons[0].getPreferredSize());
		int xPos = (int) ((jFrameSize.getWidth() / 2) - (buttons[0].getPreferredSize().getWidth() / 2));
		// int yPos = (int) ((jFrameSize.getHeight() / 2) - (buttons[0].getPreferredSize().getHeight() / 2));
		Spring xSpring = Spring.constant(5, xPos, 25);                      // x constraint for first button
		Spring ySpring = Spring.constant(10, 30, 50);                       // y constraint for ALL buttons

		// Set the constraint for the first button ("Press 6");
		buttonConstraints[B6].setConstraint(SpringLayout.WEST, xSpring);
		buttonConstraints[B6].setConstraint(SpringLayout.NORTH, ySpring);
		buttonConstraints[B1].setX(ySpring);

		// Set the vertical constraints for 6-4-5
		layout.putConstraint(SpringLayout.NORTH, buttons[B4], ySpring, SpringLayout.SOUTH, buttons[B6]);
		layout.putConstraint(SpringLayout.NORTH, buttons[B5], ySpring, SpringLayout.SOUTH, buttons[B6]);

		// Set the vertical constraints for 1-2-3
		layout.putConstraint(SpringLayout.NORTH, buttons[B1], ySpring, SpringLayout.SOUTH, buttons[B4]);
		layout.putConstraint(SpringLayout.NORTH, buttons[B2], ySpring, SpringLayout.SOUTH, buttons[B4]);
		layout.putConstraint(SpringLayout.NORTH, buttons[B3], ySpring, SpringLayout.SOUTH, buttons[B4]);

		// Set the horizontal constraint between 4-5
		layout.putConstraint(SpringLayout.WEST, buttons[B5], ySpring, SpringLayout.EAST, buttons[B4]);

		// Set the horizontal constraint of 4 relative to 6
		layout.putConstraint(SpringLayout.EAST, buttons[B4], ySpring, SpringLayout.WEST, buttons[B6]);

		// Set the horizontal constraint between 1-2-3
		layout.putConstraint(SpringLayout.WEST, buttons[B2], ySpring, SpringLayout.EAST, buttons[B1]);
		layout.putConstraint(SpringLayout.WEST, buttons[B3], ySpring, SpringLayout.EAST, buttons[B2]);

		// Set the horizontal constraint between 1 and 4
		layout.putConstraint(SpringLayout.EAST, buttons[B1], ySpring, SpringLayout.WEST, buttons[B4]);
		System.out.println(buttonConstraints[B1].getConstraint(SpringLayout.WEST));

		// Set a constraint for the right side (EAST) of the contentPane of of current+15
		// This positions the right edge of the container 15 units to the right of the right edge of the last button
		SpringLayout.Constraints contentPaneConstraints = layout.getConstraints(contentPane);
		contentPaneConstraints.setConstraint(SpringLayout.EAST,
				Spring.sum(buttonConstraints[B3].getConstraint(SpringLayout.EAST), ySpring));
		contentPaneConstraints.setConstraint(SpringLayout.SOUTH,
				Spring.sum(buttonConstraints[B3].getConstraint(SpringLayout.SOUTH), ySpring));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createWindow();
			}
		});
	}
}
