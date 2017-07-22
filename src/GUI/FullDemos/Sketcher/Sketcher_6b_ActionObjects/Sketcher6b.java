package GUI.FullDemos.Sketcher.Sketcher_6b_ActionObjects;

// Sketching application
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sketcher6b {

	private Sketcher6bFrame window;                                        // The application window
	private static Sketcher6b theApp;                                      // The application object

	public static void main(String[] args) {
		theApp = new Sketcher6b();                                        // Create the application object
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				theApp.createGUI();                          // Call GUI creator
			}
		});
	}

	// Method to create the application GUI
	private void createGUI() {
		window = new Sketcher6bFrame("Sketcher6b with Action Objects");    // Create the app window
		Toolkit theKit = window.getToolkit();                              // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                        // Get screen size

		// Set the position to screen center & size to half screen size
		window.setSize(wndSize.width / 2, wndSize.height / 2);                 // Set window size
		window.setLocationRelativeTo(null);                                // Center window

		window.addWindowListener(new WindowHandler());                     // Add window listener
		window.setVisible(true);
	}

	// Handler class for window events
	class WindowHandler extends WindowAdapter {

		@Override
		// Handler for window closing event
		public void windowClosing(WindowEvent e) {
			// Code to be added here...
		}
	}

}