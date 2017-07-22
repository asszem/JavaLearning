package GUI.Events;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EventListeners_Sample {

	public static JFrame windowJFrame;
	public static JButton defaultButton1;
	public static JButton defaultButton2;

	public static void createGUI() {
		windowJFrame = new JFrame();
		setupJFrame(windowJFrame);
		windowJFrame.setVisible(true);
	}

	public static void setupJFrame(JFrame jFrame) {

		jFrame.setTitle("Event Listener Sample");

		// Window position and Size
		Toolkit theKit = jFrame.getToolkit();                             // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       // Get screen size
		jFrame.setSize(wndSize.width / 2, wndSize.height / 2);   	      // Set window size
		jFrame.setLocationRelativeTo(null); // Center window

		// Set Font
		// Set Cursor
		// Set LookAndFeel
		// Set Color
		// Add JMenuBar

		// Add Components
		CustomJButtonWithActionListener customJButton1 = new CustomJButtonWithActionListener("Button1");
		CustomJButtonWithActionListener customJButton2 = new CustomJButtonWithActionListener("Button 2");
		defaultButton1 = new JButton("Default button 1");
		defaultButton2 = new JButton("Default button 2");

		// Add Containers - Set LayoutManager
		Container contentPane = jFrame.getContentPane();
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(customJButton1);
		buttonPanel.add(customJButton2);
		buttonPanel.add(defaultButton1);
		buttonPanel.add(defaultButton2);
		contentPane.add(buttonPanel);

		// Add ActionListeners

		// The button has its own action listener
		customJButton1.addActionListener(customJButton1);
		customJButton2.addActionListener(customJButton2);
		CustomActionListener customActionListener = new CustomActionListener();
		defaultButton1.addActionListener(customActionListener);
		defaultButton2.addActionListener(customActionListener);

		// Create new object of external class that implements eventListener
		jFrame.addWindowListener(new CustomWindowListenerClass());

		// Create new object of external class that extends to AdapterClass
		jFrame.addMouseMotionListener(new CustomMouseAdapterClass());

		// Using an anonymous inner class for FocusListenere methods
		jFrame.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("Focus is lost");
			}

			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("Focus is gained");
			}
		});
		// Enable Events
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createGUI();
			}
		});
	}

}

// Class used by multiple buttons. Event source identified by .getSource method
class CustomActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if (eventSource == EventListeners_Sample.defaultButton1) {
			System.out.println("Default Button 1 pressed");

		}
		if (eventSource == EventListeners_Sample.defaultButton2) {
			System.out.println("Default Button 2 pressed");
		}
	}

}

// Button that can be used as its own listener. Event Source identified by 'buttonID' field
@SuppressWarnings("serial")
class CustomJButtonWithActionListener extends JButton implements ActionListener {

	private static int buttonCount;	// number of total buttons - ID will be based on this
	private int buttonID; // ID to identify buttons for assigning specific action events

	// Constructor to create the button
	public CustomJButtonWithActionListener(String title) {
		buttonCount = buttonCount == 0 ? 1 : ++buttonCount;	// Increment the counter with each new instance
		buttonID = buttonCount; // Add a new ID for each button
		setText(title + " ID:" + buttonID); // Method inherited from JButton
		Dimension jButtonSize = new Dimension(200, 100);
		setPreferredSize(jButtonSize);
		setSize(jButtonSize);
		setBackground(Color.BLUE);
		setForeground(Color.WHITE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Identify action by buttonID instance variable
		System.out.println("CustomJbutton " + buttonID + " was pressed");
	}

}

// Listener class must be implemented and all methods must be implemented even if they do nothing
class CustomWindowListenerClass implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("Window is opened");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Window is closing");
		EventListeners_Sample.windowJFrame.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("Window is closed");
		System.exit(0);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("Window is iconified");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("Window is deiconified");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("Window Activated");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("Window Deactivated");
	}

}

// Adapter class must be extended to. Only the overridden methods need to be implemented
class CustomMouseAdapterClass extends MouseMotionAdapter {

	int moveCounter = 0;

	/*
	 * It is very important to use the @Override annotation when you are using adapter classes. An easy mistake to make when you're using adapter classes is to misspell the name of the method that you
	 * are using to implement the event — typically by using the wrong case for a letter. If you have not specified the @Override annotation for your method, you won't get any indication of this. You
	 * aren't overriding the adapter class method at all; you're adding a new method. Your code should compile perfectly well but your program does not handle any events. They are all passed to the
	 * method in the adapter class with the name your method should have had — which does nothing, of course.
	 * 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (moveCounter++ < 10) {
			System.out.println("MouseMoved");
		}
	}
}
