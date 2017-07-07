package GUI.Sketcher.Sketcher_7_WithToolbar;

// Sketching application
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sketcher7 {

	public static void main(String[] args) {
		theApp = new Sketcher7();                                          // Create the application object
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				theApp.createGUI();                          // Call GUI creator
			}
		});
	}

	// Method to create the application GUI
	private void createGUI() {
		window = new Sketcher7Frame("Sketcher7");                            // Create the app window
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

		// Handler for window closing event
		@Override
		public void windowClosing(WindowEvent e) {
			// Code to be added here...
		}
	}

	private Sketcher7Frame window;                                        // The application window
	private static Sketcher7 theApp;                                      // The application object
}