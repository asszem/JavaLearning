package GUI.JSkeletons;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JButton_Skeleton {

	public void createGUI() {
		// Setup the GUI
		JFrame jframe = new JFrame("JButton Skeleton");
		Toolkit theKit = jframe.getToolkit();                             // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       // Get screen size
		jframe.setSize(wndSize.width / 4, wndSize.height / 3);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Setup the Container and the Panel for the button
		Container content = jframe.getContentPane();
		JPanel jPanel = new JPanel();
		content.add(jPanel);

		// Create a JButton object
		JButtonSample jButton = new JButtonSample();
		jButton.setText("JButton Sample");
		// Add an ActionListener to the button
		jButton.addActionListener(jButton);

		// Add the button to the panel to be displayed
		jPanel.add(jButton);

		// Last command in CreateGUI
		jframe.setVisible(true);
	}

	public static void main(String[] args) {
		JButton_Skeleton appInstance = new JButton_Skeleton();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				appInstance.createGUI();
			}
		});
	}
}

class JButtonSample extends JButton implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Button was pressed");
	}

}