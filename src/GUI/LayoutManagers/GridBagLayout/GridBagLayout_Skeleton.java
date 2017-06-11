package GUI.LayoutManagers.GridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class GridBagLayout_Skeleton {

	public static void createGridBagLayout() {
		// Create and set the JFrame
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);		// To center
		mainFrame.setBounds(200, 200, 700, 400);

		// Create the GridBagLayout and set it to the JFrame
		GridBagLayout gridBagLayout = new GridBagLayout();		// Create the layout
		mainFrame.getContentPane().setLayout(gridBagLayout);	// Set gridbaglayout as layout for mainframe ContentPane

		// Create and set the Constraints
		GridBagConstraints gridBagLayoutConstraints = new GridBagConstraints(); // Create constraints object
		gridBagLayoutConstraints.fill = GridBagConstraints.BOTH;

		// Call methods to add COMPONENTS
		addLabel("First label", gridBagLayout, mainFrame, gridBagLayoutConstraints);
		addJButton("First Button", gridBagLayout, mainFrame, gridBagLayoutConstraints);

		// Must set AFTER all other
		mainFrame.pack();							// To fill
		mainFrame.setVisible(true);					// To set visible

	}

	public static void addLabel(String labelText, GridBagLayout addToThisLayout, JFrame addToThisFrame,
			GridBagConstraints useThisConstraint) {
		JLabel jLabel = new JLabel(labelText);
		addToThisLayout.setConstraints(jLabel, useThisConstraint);
		addToThisFrame.getContentPane().add(jLabel);
	}

	public static void addJButton(String buttonText, GridBagLayout addToThisLayout, JFrame addToThisFrame,
			GridBagConstraints useThisConstraint) {

		JButton newButton= new JButton(buttonText);
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
