package GUI.LayoutManagers.GridBagLayout;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class GridBagLayout_Sample {

	public static void createGridBagLayout() {
		// Create and set the JFrame
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);		// To center
		mainFrame.setBounds(200, 200, 600, 600);

		// Create the GridBagLayout and set it to the JFrame
		GridBagLayout gridBagLayout = new GridBagLayout();		// Create the layout
		mainFrame.getContentPane().setLayout(gridBagLayout);	// Set gridbaglayout as layout for mainframe ContentPane

		// Create and set the Constraints
		GridBagConstraints gridBagLayoutConstraints = new GridBagConstraints(); // Create constraints object

		// Call methods to add COMPONENTS
		// gridBagLayoutConstraints.gridx = 0;
		// gridBagLayoutConstraints.gridy = 0;
		// gridBagLayoutConstraints.gridwidth = 1;
		// gridBagLayoutConstraints.gridheight = 1;
		// addLabel("Grid 0x0", gridBagLayout, mainFrame, gridBagLayoutConstraints);

		 gridBagLayoutConstraints.gridx = 1;
		 gridBagLayoutConstraints.gridy = 0;
		// gridBagLayoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
		// gridBagLayoutConstraints.gridheight = GridBagConstraints.REMAINDER;
		// gridBagLayoutConstraints.ipadx=100;
		// gridBagLayoutConstraints.ipady=100;
		gridBagLayoutConstraints.fill = GridBagConstraints.BOTH;
		gridBagLayoutConstraints.weightx = 1.0;
		gridBagLayoutConstraints.weighty = 1.0;
		addJButton(displayGrid("Button 1", gridBagLayoutConstraints), gridBagLayout, mainFrame, gridBagLayoutConstraints);

		// gridBagLayoutConstraints.gridx = 0;
		// gridBagLayoutConstraints.gridy = 1;
		// gridBagLayoutConstraints.gridwidth=100;
		// gridBagLayoutConstraints.gridheight=100;
		// addLabel("Grid 0x1", gridBagLayout, mainFrame, gridBagLayoutConstraints);
		 gridBagLayoutConstraints.gridx = 0;
		 gridBagLayoutConstraints.gridy = 0;
		// gridBagLayoutConstraints.gridwidth=500;
		// gridBagLayoutConstraints.gridheight=500;
		gridBagLayoutConstraints.weightx = 5.0;
		gridBagLayoutConstraints.weighty = 5.0;
		addJButton(displayGrid("Button2", gridBagLayoutConstraints), gridBagLayout, mainFrame, gridBagLayoutConstraints);

		// gridBagLayoutConstraints.gridx = 2;
		// gridBagLayoutConstraints.gridy = 2;
		// addLabel("Third label", gridBagLayout, mainFrame, gridBagLayoutConstraints);
		// addJButton("Third Button", gridBagLayout, mainFrame, gridBagLayoutConstraints);

		// Must set AFTER all other
		// mainFrame.pack(); // To fill
		mainFrame.setVisible(true);					// To set visible

	}

	public static String displayGrid(String label, GridBagConstraints constraint) {
		String rtr = label+ " GridX:"+constraint.gridx + " | GridY: " + constraint.gridy;
		System.out.println(constraint.toString());
		return rtr;
	}

	public static void addLabel(String labelText, GridBagLayout addToThisLayout, JFrame addToThisFrame,
			GridBagConstraints useThisConstraint) {
		JLabel jLabel = new JLabel(labelText);
		addToThisLayout.setConstraints(jLabel, useThisConstraint);
		addToThisFrame.getContentPane().add(jLabel);
	}

	public static void addJButton(String buttonText, GridBagLayout addToThisLayout, JFrame addToThisFrame,
			GridBagConstraints useThisConstraint) {
		JButton newButton = new JButton(buttonText);
		newButton.setSize(300, 400);
		addToThisLayout.setConstraints(newButton, useThisConstraint);
		addToThisFrame.getContentPane().add(newButton);
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createGridBagLayout();
			}
		});
	}
}
