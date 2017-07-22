package GUI.Events;

import static java.awt.AWTEvent.MOUSE_EVENT_MASK;
import static java.awt.AWTEvent.WINDOW_EVENT_MASK;
import static java.awt.AWTEvent.WINDOW_FOCUS_EVENT_MASK;
import static java.awt.AWTEvent.WINDOW_STATE_EVENT_MASK;

import java.awt.AWTEvent;

import static java.awt.AWTEvent.KEY_EVENT_MASK;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class EnableEvents_Sample {

	public static void createGUI() {
		EnableEvents_JFrame guiWindow = new EnableEvents_JFrame("JFrame event handling sample");
		guiWindow.setVisible(true);
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

class EnableEvents_JFrame extends JFrame {

	// Constructor to create the JFrame
	public EnableEvents_JFrame(String windowTitle) {
		this.setTitle(windowTitle); // The class extends to JFrame, so it has the method

		// Enable the events for the JFrame object instance in the constructor
		this.enableEvents(WINDOW_EVENT_MASK | WINDOW_STATE_EVENT_MASK | WINDOW_FOCUS_EVENT_MASK | MOUSE_EVENT_MASK);
		this.enableEvents(KEY_EVENT_MASK);
	}

	// Override methods for selected event IDs of enabled events and make sure super is called for the rest

	@Override
	protected void processEvent(AWTEvent e) {	// must use AWTEvent
		// This is called fi rst for any events that are enabled for the component.
		// If you implement this and fail to call the base class method, none of the methods for specifi c groups of events are called.
		System.out.println("An event occured!");
		super.processEvent(e);
	}

	@Override
	protected void processKeyEvent(KeyEvent k) {
		int id = k.getID();
		switch (id) {
		case KeyEvent.KEY_TYPED:
			System.out.println("Key typed");
			break;
		case KeyEvent.KEY_PRESSED:
			System.out.println("Key pressed");
			break;
		case KeyEvent.KEY_RELEASED:
			System.out.println("Key released");
			break;
		}
		super.processKeyEvent(k);
	}

	@Override
	protected void processMouseEvent(MouseEvent m) {
		if (m.getID() == MouseEvent.MOUSE_CLICKED) {
			System.out.println("Mouse clicked");
		}
		super.processMouseEvent(m);
	}

	@Override
	protected void processWindowFocusEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_LOST_FOCUS) {
			System.out.println("Window lost focus");
		}
		if (e.getID() == WindowEvent.WINDOW_GAINED_FOCUS) {
			System.out.println("Window gained focus");
		}
		super.processWindowFocusEvent(e);

	}

	@Override
	protected void processWindowStateEvent(WindowEvent e) {

		if (e.getID() == WindowEvent.WINDOW_STATE_CHANGED) {
			System.out.println("Window state changed");
		}
		super.processWindowStateEvent(e); 							// if the event is NOT exit, pass it to superclass (JFRAME) so
	}

	@Override
	protected void processWindowEvent(WindowEvent e) { 						// Overrides processWindowEvent in JFrame
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.out.println("Window is closing");
			dispose(); // Release resources - inherited from java.awt.Window
		}
		if (e.getID() == WindowEvent.WINDOW_CLOSED) {
			System.out.println("Window is CLOSED");
			System.exit(0); // Exit the program
		}
		super.processWindowEvent(e); // if the event is NOT exit, pass it to superclass (JFRAME) so
		// this allows the event to be passed on to any listeners that have been registered for these events
	}

}