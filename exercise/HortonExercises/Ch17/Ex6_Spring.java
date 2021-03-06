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
		// mainJFrame.setSize(jFrameSize);
		mainJFrame.setLocationRelativeTo(null);

		Container contentPane = mainJFrame.getContentPane();
		createSpringLayout2(jFrameSize, mainJFrame, contentPane);

		// Set the window size
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

		// Create 6 button objects
		JButton[] buttons = new JButton[6];
		SpringLayout.Constraints[] buttonConstraints = new SpringLayout.Constraints[buttons.length];
		// Dimension buttonSize = new Dimension(100, 25);
		for (int i = 0; i < 6; i++) {
			buttons[i] = new JButton("Press " + (6 - i));
			// buttons[i].setPreferredSize(buttonSize); // Set size for button
			contentPane.add(buttons[i]);							// Add the button to content pane
			buttonConstraints[i] = layout.getConstraints(buttons[i]); // Get the constraints for the current button
		}

		System.out.println("Button width: " + Spring.width(buttons[0]).getValue());
		int pref = 28;
		Spring ySpring = Spring.constant(10, pref, 50);
		Spring xSpring = Spring.constant(0, pref, 100);
		Spring halfButtonWidth = Spring.constant(0,Spring.width(buttons[0]).getValue() / 2, 100);
		Spring halfSpringWidth = Spring.constant(0,xSpring.getValue()/2, xSpring.getMaximumValue());
		Spring buttonWidth=Spring.width(buttons[B1]);

		// Set Horizontal Constraints

		// The X positioning of Button 4 is the key to success...
//		buttonConstraints[B4].setX(Spring.sum(
//												Spring.sum(xSpring, buttonWidth),
//												halfSpringWidth
//												)
//									);
//		buttonConstraints[B4].setX(Spring.sum(halfButtonWidth, xSpring));
		//TODO Find out why the Spring layout is not working and fix it
		System.out.println("xSpring value"+xSpring.getValue());
		layout.putConstraint(SpringLayout.WEST, buttons[B4], Spring.sum(halfSpringWidth, Spring.sum(halfButtonWidth, halfSpringWidth)), SpringLayout.WEST, 
				buttons[B1]);
		
//		Spring middleRowSpring=Spring.sum(xSpring,halfSpringWidth);

		// xspring from left edge (west) + half the button width + half of spring between B4 and B5
		// buttonConstraints[B4].setX(Spring.sum(Spring.sum(xSpring, halfSpringWidht), halfButtonWidth));

		buttonConstraints[B1].setX(xSpring);

		// Put horizontal constraints between buttons in row 2 and row 3
		layout.putConstraint(SpringLayout.WEST, buttons[B2], xSpring, SpringLayout.EAST, buttons[B1]);
		layout.putConstraint(SpringLayout.WEST, buttons[B3], xSpring, SpringLayout.EAST, buttons[B2]);
		layout.putConstraint(SpringLayout.WEST, buttons[B5], xSpring, SpringLayout.EAST, buttons[B4]);
		// Connect the button in 1st row to the exact position of the middle button (B2) in 3rd row
		layout.putConstraint(SpringLayout.WEST, buttons[B6], 0, SpringLayout.WEST, buttons[B2]);

		// Set Vertical Constraints
		buttonConstraints[B6].setY(ySpring);
		// Set the vertical constraints for 6-4-5
		layout.putConstraint(SpringLayout.NORTH, buttons[B4], ySpring, SpringLayout.SOUTH, buttons[B6]);
		layout.putConstraint(SpringLayout.NORTH, buttons[B5], ySpring, SpringLayout.SOUTH, buttons[B6]);
		// Set the vertical constraints for 1-2-3
		layout.putConstraint(SpringLayout.NORTH, buttons[B1], ySpring, SpringLayout.SOUTH, buttons[B4]);
		layout.putConstraint(SpringLayout.NORTH, buttons[B2], ySpring, SpringLayout.SOUTH, buttons[B4]);
		layout.putConstraint(SpringLayout.NORTH, buttons[B3], ySpring, SpringLayout.SOUTH, buttons[B4]);

		// Set the content pane constraint for SOUTH and EAST edge, relative to button 3
		SpringLayout.Constraints contentPaneConstraints = layout.getConstraints(contentPane);
		contentPaneConstraints.setConstraint(SpringLayout.EAST,
				Spring.sum(buttonConstraints[B3].getConstraint(SpringLayout.EAST), xSpring));
		contentPaneConstraints.setConstraint(SpringLayout.SOUTH,
				Spring.sum(buttonConstraints[B3].getConstraint(SpringLayout.SOUTH), ySpring));
		System.out.println(buttonConstraints[B3].getConstraint(SpringLayout.EAST));
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createWindow();
				System.out.println("Main method ended");
			}
		});
	}
}
