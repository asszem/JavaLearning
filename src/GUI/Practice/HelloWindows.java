package GUI.Practice;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class HelloWindows {

	public static void createWindow(String windowTitle) {
		JFrame aWindow = new JFrame(windowTitle);
		int windowWidth = 330;	 								// Window width in pixels
		int windowHeight = 100; 								// Window height in pixels
		aWindow.setBounds((int) (Math.random()*800), (int) (Math.random()*900), 								// Set position
				windowWidth, windowHeight); 					// and size
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.setVisible(true); // Display the window
	}

	public static void main(String[] args) {
		//Display the available look-and-feels
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo look:looks){
			System.out.println(look);
		}
		// Create new window with anonymous class
		SwingUtilities.invokeLater(new Runnable() { 			// anonymous runnable class object

			public void run() {
				createWindow("Anyablak");
			}													// end run method
		}														// end of anonymous runnable class object
		);														// end of invokeLater method argument;

		// Create another window with normal object reference
		SwingUtilities.invokeLater(new WindowRunner());
		WindowRunner yetAnotherWindow = new WindowRunner("Barnabás ablak ez!");
		SwingUtilities.invokeLater(yetAnotherWindow);
		WindowRunner yetAnotherWindow2 = new WindowRunner("Benő ablak ez!");
		SwingUtilities.invokeLater(yetAnotherWindow2);

	}// end of main
}// end of class

class WindowRunner implements Runnable {

	String windowTitle;

	WindowRunner() {
		windowTitle = "Apus Ablak";
	}

	WindowRunner(String customWindowTitle){
		windowTitle=customWindowTitle;
	}
	public void run() {
		HelloWindows.createWindow(windowTitle);
	}
}
