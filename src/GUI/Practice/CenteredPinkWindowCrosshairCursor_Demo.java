package GUI.Practice;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;

public class CenteredPinkWindowCrosshairCursor_Demo {

	public static void createWindow() {
		JFrame aWindow = new JFrame("This is the Window Title");
		Toolkit theKit = aWindow.getToolkit(); // Get the window toolkit
		Dimension screenSize = theKit.getScreenSize(); // Get screen size

		// Set the position to screen center & size to half screen size
		aWindow.setSize(screenSize.width / 2, screenSize.height / 2); // Set window size
		aWindow.setLocationRelativeTo(null); // Center window
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		aWindow.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		aWindow.getContentPane().setBackground(Color.PINK);

		aWindow.setVisible(true); // Display the window
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				createWindow();
			}
		});
	}
}
