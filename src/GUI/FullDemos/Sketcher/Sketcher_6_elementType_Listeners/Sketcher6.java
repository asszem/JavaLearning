package GUI.FullDemos.Sketcher.Sketcher_6_elementType_Listeners;

// Sketching application
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sketcher6 {

	public static void main(String[] args) {
		theApp = new Sketcher6();                                          // Create the application object
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				theApp.createGUI();                          // Call GUI creator
			}
		});
	}

	// Method to create the application GUI
	private void createGUI() {
		window = new Sketcher6Frame("Sketcher6 - Element Type Listeners"); // Create the app window
		Toolkit theKit = window.getToolkit();                              // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                        // Get screen size

		// Set the position to screen center & size to half screen size
		window.setSize(wndSize.width / 2, wndSize.height / 2);                 // Set window size
		window.setLocationRelativeTo(null);                                // Center window

		window.addWindowListener(new WindowHandler());                     // Add window listener
		window.setVisible(true);
	}

	// Handler class for window events - used as addWindowListener method's argument
	class WindowHandler extends WindowAdapter {

		// Handler for window closing event
		@Override
		public void windowClosing(WindowEvent e) {
			window.dispose();                                                // Release the window resources
			System.exit(0);                                                  // End the application
		}
	}

	private Sketcher6Frame window;                                        // The application window
	private static Sketcher6 theApp;                                      // The application object
}