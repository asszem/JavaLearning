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
		Container mainContent = mainJFrame.getContentPane();

		// Create the Layout Object
		SpringLayout springLayout = new SpringLayout();

		// Set the layout manager for Container
		// mainJFrame.setLayout(springLayout); //Automatically sets the content for the ContentPane
		mainContent.setLayout(springLayout);

		// Create Button Components
		JButton button1 = createButton("B1 - ", new Dimension(300,200));
		JButton button2 = createButton("B2 - ", new Dimension(300,200));


		// Add the Component to the Container
		mainContent.add(button1);
		mainContent.add(button2);

		// Get the constraints for the component
		SpringLayout.Constraints button1Constraints = springLayout.getConstraints(button1);
		SpringLayout.Constraints button2Constraints = springLayout.getConstraints(button2);

		// Create a Spring object
		Spring springX = Spring.constant(10, 100, 200);			// min, pref, max
		Spring springY = Spring.constant(10, 100, 200);
		Spring springWidth = Spring.constant(100, 200, 300);
		Spring springHeight = Spring.constant(200);				// A strut. Can not change height

		displaySprings(springX, springY, springWidth, springHeight);

		// Assign the Spring object to the Component's Constraint
		button1Constraints.setX(springX);
		button1Constraints.setY(springY);
//		button1Constraints.setWidth(springWidth);
//		button1Constraints.setHeight(springHeight);
		//Assigning a new constraint will overwrite previously settings
//		button1Constraints.setWidth(Spring.width(button1)); //Automatically assign the width of the component
//		button1Constraints.setHeight(Spring.height(button1)); //Automatically assign the width of the component

		//Set the constraint directly - overwrites previous settings
//		button1Constraints.setConstraint(SpringLayout.NORTH, Spring.constant(20,200,300));
//		button2Constraints.setConstraint(SpringLayout.NORTH, Spring.constant(20,200,300));
		
		//Set the constraint relatively to each other
		Spring springBetween = Spring.constant(30,40,50);
		springLayout.putConstraint(SpringLayout.NORTH, button2, springBetween, SpringLayout.SOUTH, button1);
	
	
		
		
		// Set the jFrame visible
		mainJFrame.setVisible(true);
	}

	public static JButton createButton(String text, Dimension dimension) {
		String buttonText = text + (int) dimension.getWidth() + "x" + (int) dimension.getHeight();
		JButton button = new JButton(buttonText);
		button.setPreferredSize(dimension);
		return button;
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
