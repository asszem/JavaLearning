package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CreateJFrame_Skeleton {

	public static void createGUI() {
		CustomJFrame customJFrame = new CustomJFrame("Custom JFrame");
		JFrame defaultJFrame = new JFrame("Default JFrame");
		setupJFrame(defaultJFrame);
		customJFrame.setVisible(true);
		defaultJFrame.setVisible(true);
	}

	public static void setupJFrame(JFrame jFrame) {

		jFrame.setTitle("Default JFrame title");

		// Window position and Size
		Toolkit theKit = jFrame.getToolkit();                             // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       // Get screen size
		jFrame.setSize(wndSize.width / 2, wndSize.height / 2);   	      // Set window size
		// jFrame.setLocationRelativeTo(null); // Center window

		// Set Font
		// Set Cursor
		// Set LookAndFeel
		// Set Color
		// Add JMenuBar
		// Add Containers - Set LayoutManager
		// Add Components
		// Add ActionListeners
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

} // end of CreateJFrame_Skeleton class

class CustomJFrame extends JFrame {

	// private static JFrame window; //no need to create

	public CustomJFrame(String windowTitle) {
		this.setTitle(windowTitle); // The class extends to JFrame, so it has the method

		// Set Window Position and Size
		Toolkit theKit = this.getToolkit();                               // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       // Get screen size
		setSize(wndSize.width / 2, wndSize.height / 2);   	              // Set window size
		setLocationRelativeTo(null); 		                              // Center window

		// Set Font
		// Set Cursor
		// Set LookAndFeel
		// Set Color
		// Add JMenuBar
		// Add Containers - Set LayoutManager
		// Add Components
		// Add ActionListeners
		// Enable Events
	}
}