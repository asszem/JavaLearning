/*
 * This class has a nested class that extends to WindowAdapter, so that the main class does not have to implement all unused methods
 * 
 * */
package GUI.Sketcher.Sketcher_5_event_listener_using_AdapterClass;

// Sketching application
import java.awt.*;
import java.awt.event.*;

import javax.swing.SwingUtilities;

public class Sketcher {	// Class does not implement WindowListener!

	private static SketcherFrame window;                                  	// The application window
	private static Sketcher sketcherInstance;								// The application object

	public static void main(String[] args) {
		sketcherInstance = new Sketcher();
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				sketcherInstance.createGUI(); 	// call the method that creates the GUI
			}
		});
	}

	public void createGUI() {
		window = new SketcherFrame("Sketcher using WindowAdapter class in a Nested Class");           // Create the app window
		Toolkit theKit = window.getToolkit();                              			 	// Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       				// Get screen size

		// Set the position to screen center & size to half screen size
		window.setSize(wndSize.width / 2, wndSize.height / 2);            		    	// Set window size
		window.setLocationRelativeTo(null);                                				// Center window
		// window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.addWindowListener(new NestedWindowListener());													// The sketecherInstance as window listener
		window.setVisible(true);
	}

	class NestedWindowListener extends WindowAdapter {

		// Only those methods need to be declared here that I want to override. Other methods are in WindowAdapter
		@Override
		public void windowClosing(WindowEvent e) {
			window.dispose();				// Because NestedWindowListener class can access fields of Sketcher class, it can reach 'window'
			System.out.println("Window closed, system exits.");
			System.exit(0);
		}
	}

}