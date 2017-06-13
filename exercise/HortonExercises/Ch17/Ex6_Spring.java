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
		Dimension jFrameSize = new Dimension(800, 800);
		mainJFrame.setSize(jFrameSize);
		mainJFrame.setLocationRelativeTo(null);

		SpringLayout layout = new SpringLayout();
		Container contentPane = mainJFrame.getContentPane();
		contentPane.setLayout(layout);

		JButton[] buttons = new JButton[6];
		for (int i = 6; i > 0; i--) {
			buttons[6 - i] = new JButton("Press " + (i));
			buttons[6 - i].setSize(100, 100);
			contentPane.add(buttons[6 - i]);
		}
		SpringLayout.Constraints constraints;

		// Set the value of springs to be used
		int xPos = (int) ((jFrameSize.getWidth() / 2) - (buttons[0].getSize().getWidth() / 2));
		int yPos = (int) ((jFrameSize.getHeight() / 2) - (buttons[0].getSize().getHeight() / 2));
		Spring xSpring = Spring.constant(5, xPos, 25);                         // x constraint for first button
		Spring ySpring = Spring.constant(10, 30, 50);                       // y constraint for first button

		// Set the constraint for the first button ("Press 6");
		constraints = layout.getConstraints(buttons[0]);
		constraints.setConstraint(SpringLayout.WEST, xSpring);
		constraints.setConstraint(SpringLayout.NORTH, ySpring);

		// Set a constraint for the right side (EAST) of the contentPane of of current+15
		// This positions the right edge of the container 15 units to the right of the right edge of the last button
		SpringLayout.Constraints contentPaneConstraints = layout.getConstraints(contentPane);
		contentPaneConstraints.setConstraint(SpringLayout.EAST,
				Spring.sum(constraints.getConstraint(SpringLayout.EAST), Spring.constant(15)));
		contentPaneConstraints.setConstraint(SpringLayout.SOUTH,
				Spring.sum(constraints.getConstraint(SpringLayout.SOUTH), Spring.constant(10)));
		mainJFrame.pack();
		mainJFrame.setVisible(true);
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
