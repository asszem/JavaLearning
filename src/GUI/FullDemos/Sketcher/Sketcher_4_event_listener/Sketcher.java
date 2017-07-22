/*
 * Because this class implements WindowListener, it has to implement all of it's 7 methods even if they are empty
 * 
 * */
package GUI.FullDemos.Sketcher.Sketcher_4_event_listener;

// Sketching application
import java.awt.*;
import java.awt.event.*;

import javax.swing.SwingUtilities;

public class Sketcher implements WindowListener {	// 7 methods need to be implemented (implementation can be empty)

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
		window = new SketcherFrame("Sketcher with low level event listener");           // Create the app window
		Toolkit theKit = window.getToolkit();                              			 	// Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       				// Get screen size

		// Set the position to screen center & size to half screen size
		window.setSize(wndSize.width / 2, wndSize.height / 2);            		    	// Set window size
		window.setLocationRelativeTo(null);                                				// Center window
		// window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.addWindowListener(this);													// The sketecherInstance as window listener
		window.setVisible(true);
	}

	//All overridden methods need to be declared, but the implementation can be empty
	@Override
	public void windowClosing(WindowEvent e) {
		window.dispose();
		System.out.println("Window closed, system exits.");
		System.exit(0);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

}