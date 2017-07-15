package GUI.Sketcher.Sketcher_8_1_Drawing_Rectangle3D;

// Sketching application
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sketcher8 {

	private Sketcher8Model sketchModel;                                   // The data model for the sketchModel
	private Sketcher8View view;                                           // The view of the sketchModel
	private Sketcher8Frame window;                                        // The application window
	private static Sketcher8 theApp;                                      // The application object

	public static void main(String[] args) {
		theApp = new Sketcher8();                                          // Create the application object
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				theApp.createGUI();                          // Call GUI creator
			}
		});
	}

	// Method to create the application GUI
	private void createGUI() {
		window = new Sketcher8Frame("Sketcher 8", this);    // Create the app window, pass theApp object in arg.
		Toolkit theKit = window.getToolkit();                              // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                        // Get screen size

		// Set the position to screen center & size to half screen size
		window.setSize(wndSize.width / 2, wndSize.height / 2);                 // Set window size
		window.setLocationRelativeTo(null);                                // Center window

		window.addWindowListener(new WindowHandler());                     // Add window listener

		view = new Sketcher8View(this);                                    // Create the view
		sketchModel = new Sketcher8Model();                                // Create the model
		sketchModel.addObserver(view);                                     // Register view with the model
		
		//Add the VIEW to the content pane
		window.getContentPane().add(view, BorderLayout.CENTER);			

		window.setVisible(true);
	}

	// Return a reference to the application window
	public Sketcher8Frame getWindow() {
		return window;
	}

	// Return a reference to the model
	public Sketcher8Model getModel() {
		return sketchModel;
	}

	// Return a reference to the view
	public Sketcher8View getView() {
		return view;
	}

	// Handler class for window events
	class WindowHandler extends WindowAdapter {

		// Handler for window closing event
		@Override
		public void windowClosing(WindowEvent e) {
			// Code to be added here...
		}
	}

}