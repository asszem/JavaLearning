package GUI.Practice;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class SpringLayout_Sample {

	public static void createWindow() {
		// Create a Dimension object for window width and height
		Dimension dimension = new Dimension(600, 600);

		// Create the jFrame object, and set its title, size and location
		JFrame mainJFrame = new JFrame();
		mainJFrame.setTitle("Spring Practice");
		mainJFrame.setSize(dimension);
		mainJFrame.setLocationRelativeTo(null);

		// Create a Container
		Container mainContentPane = mainJFrame.getContentPane();

		// Create the Layout Object
		SpringLayout springLayout = new SpringLayout();

		// Set the layout manager for Container
		// mainJFrame.setLayout(springLayout); //Automatically sets the content for the ContentPane
		mainContentPane.setLayout(springLayout);

		// Create Button Components
		JButton button1 = createButton("B1 - ", new Dimension(300, 200));
		JButton button2 = createButton("B2 - ", new Dimension(300, 200));

		// Add the Component to the Container
		mainContentPane.add(button1);
		mainContentPane.add(button2);

		// Get the constraints for the component
		SpringLayout.Constraints button1Constraints = springLayout.getConstraints(button1);
		SpringLayout.Constraints button2Constraints = springLayout.getConstraints(button2);
		SpringLayout.Constraints contentPaneConstraint = springLayout.getConstraints(mainContentPane);

		// Create a Spring object
		Spring springX = Spring.constant(10, 100, 200);			// min, pref, max
		Spring springY = Spring.constant(10, 100, 200);
		Spring springWidth = Spring.constant(100, 200, 300);
		Spring springHeight = Spring.constant(200);				// A strut. Can not change height

		displaySprings(springX, springY, springWidth, springHeight);

		// Assign the Spring object to the Component's Constraint
		button1Constraints.setX(springX);
		button1Constraints.setY(springY);
		 button1Constraints.setWidth(springWidth);
		 button1Constraints.setHeight(springHeight);

		// Assigning a new constraint will overwrite previously settings
		// button1Constraints.setWidth(Spring.width(button1)); //Automatically assign the width of the component
		// button1Constraints.setHeight(Spring.height(button1)); //Automatically assign the width of the component

		// Set the constraint directly - overwrites previous settings
		// button1Constraints.setConstraint(SpringLayout.NORTH, Spring.constant(20,200,300));
		// button2Constraints.setConstraint(SpringLayout.NORTH, Spring.constant(20,200,300));

		// Set the constraint relatively to each other of two components
		Spring springBetween = Spring.constant(50);
		// DEPENDENT component || Spring || REFERENCE component
		// Add a 50px strut between the BOTTOM of button1 and the TOP of button2
		springLayout.putConstraint(SpringLayout.NORTH, button2, springBetween, SpringLayout.SOUTH, button1);
		// Add a 50px strut between the RIGHT of button1 and the LEFT of button2
		springLayout.putConstraint(SpringLayout.WEST, button2, springBetween, SpringLayout.EAST, button1);

		// Set the constraints relative to the Content Pane
		displayConstraint(button2Constraints);
		// Set the constraint relative to the button1's WEST and North Edges
		int paddingSize=20;
		
		//Set the EAST (right) side of content pane relative to the button2
		Spring eastSpring=Spring.sum(button2Constraints.getConstraint(SpringLayout.EAST), Spring.constant(paddingSize));
		contentPaneConstraint.setConstraint(SpringLayout.EAST,eastSpring);

		//Set the NORTH (bottom) side of the content pane relative to button2
		Spring southSpring=Spring.sum(button2Constraints.getConstraint(SpringLayout.SOUTH), Spring.constant(paddingSize));
		contentPaneConstraint.setConstraint(SpringLayout.SOUTH, southSpring);
		System.out.println(button2Constraints.getConstraint(SpringLayout.SOUTH).getValue());
		// Sets the constraint of the pane, regardless of its components
		// contentPaneConstraint.setConstraint(SpringLayout.WEST, Spring.constant(20,200,300));
		// contentPaneConstraint.setConstraint(SpringLayout.SOUTH, Spring.constant(20,200,300));

		// Adjust the size of the frame to the content. SpringLayout does not set it automatically
		mainJFrame.pack();

		// Set the jFrame visible
		mainJFrame.setVisible(true);
	}

	public static JButton createButton(String text, Dimension dimension) {
		String buttonText = text + (int) dimension.getWidth() + "x" + (int) dimension.getHeight();
		JButton button = new JButton(buttonText);
		button.setPreferredSize(dimension);
		button.setMinimumSize(dimension);
		button.setMaximumSize(dimension);
		return button;
	}

	public static void displayConstraint(SpringLayout.Constraints constraint) {
		System.out.println("West: " + constraint.getConstraint(SpringLayout.WEST));
		System.out.println("East: " + constraint.getConstraint(SpringLayout.EAST));
		System.out.println("North: " + constraint.getConstraint(SpringLayout.NORTH));
		System.out.println("South: " + constraint.getConstraint(SpringLayout.SOUTH));
		// System.out.println(constraint.getConstraint(SpringLayout.WIDTH));
		// System.out.println(constraint.getConstraint(SpringLayout.HEIGHT));
	}

	public static void displaySprings(Spring... springs) {
		for (Spring spring : springs) {
			// toString() automatically displays min, pref, max values
			System.out.println(spring);
			System.out.println(spring.getMinimumValue());
			System.out.println(spring.getPreferredValue());
			System.out.println(spring.getMaximumValue());
		}
		// It can be obtained manually as well
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
