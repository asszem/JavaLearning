package GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JButton_Sample extends JButton implements ActionListener {

	private static JFrame jFrame;
	private static JButton_Sample button1;
	private static JButton_Sample button2;
	protected static JButton_Sample button3;
	protected static JButton_Sample button4;
	private static JButton buttonDefault;
	private static Dimension buttonSize = new Dimension(300, 100);
	private static int buttonCount = 0;
	protected int buttonID; // protected - so other class in file can access it

	public JButton_Sample() {
		buttonID = ++buttonCount;
	}

	public JButton_Sample(String buttonTitle) {
		this();// to get a buttonID assigned for each button
		this.setText(buttonTitle + " ID:" + buttonID);
		this.setPreferredSize(buttonSize);
		this.setSize(buttonSize);
		this.setToolTipText("Tooltip for "+buttonTitle);
	}

	public static void createGUI() {
		jFrame = new JFrame();
		jFrame.setBounds(200, 200, 400, 500);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create buttons
		button1 = new JButton_Sample("Custom button 1");
		button2 = new JButton_Sample("Custom button 2");
		button3 = new JButton_Sample("Custom button 3");
		setButton4(new JButton_Sample("Custom button 4"));
		buttonDefault = new JButton("Standard button");

		// Add buttons to the content
		Container content = jFrame.getContentPane();
		JPanel jPanel = new JPanel();
		jPanel.add(button1);
		jPanel.add(button2);
		jPanel.add(button3);
		jPanel.add(getButton4());
		jPanel.add(buttonDefault);
		content.add(jPanel);

		// Add action listeners

		// Using its own listener: button1 type is JButton_Sample which implements ActionListener interface
		button1.addActionListener(button1);
		button2.addActionListener(button2);

		// Using the same (custom) ActionListener object for multiple buttons
		JButton_Sample_CustomActionListener customActionListener = new JButton_Sample_CustomActionListener();
		button3.addActionListener(customActionListener);
		getButton4().addActionListener(customActionListener);

		// Using an anonymous inner class to define the action performed
		buttonDefault.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Standard button pressed");
			}
		});

		// jFrame.pack();
		jFrame.setVisible(true);
	}

	// Action Listener method for JButton_Sample class itself
	@Override
	public void actionPerformed(ActionEvent e) {
		// Event source identified by ID
		System.out.println("Custom Button " + buttonID + " pressed");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createGUI();
			}
		});
	}

	public static JButton_Sample getButton4() {
		return button4;
	}

	public static void setButton4(JButton_Sample button4) {
		JButton_Sample.button4 = button4;
	}

}

@SuppressWarnings("serial") // Extend to JButton_Sample so button3 and button2 fields are available
class JButton_Sample_CustomActionListener extends JButton_Sample implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Object sourceObject = e.getSource();	// Get the source object of the event to determine action
		if (sourceObject == button3) {			// Directly access a protected filed from JButton_Sample class
			System.out.println("Custom Button 3 pressed ID: " + button3.buttonID);
		}
		if (sourceObject == getButton4()) {	// Get a private field from JButton_Sample class
			System.out.println("Custom Button 4 pressed ID: " + getButton4().buttonID);
		}
	}

}