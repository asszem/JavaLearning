package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Window_Collection {

	public static JFrame createJFrameWindow(String windowTitle) {
		JFrame aWindow = new JFrame(windowTitle);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.setVisible(true); // Display the window
		return aWindow;
	}

	public static void createJFrameWindow(String windowTitle, int posX, int posY, int width, int height) {
		createJFrameWindow(windowTitle).setBounds(posX, posY, width, height);
	}

	public static void createJFrameWindow(String windowTitle, LookAndFeelInfo look) {
		JFrame aWindow=createJFrameWindow(windowTitle);
		try {
			// Set the look and feel
			UIManager.setLookAndFeel(look.getClassName());
			// causes the frame window to be updated with the look-and-feel that is currently set.
			SwingUtilities.updateComponentTreeUI(aWindow);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void createJFrameWindow(String windowTitle, String lookAndFeel) {
		JFrame aWindow=createJFrameWindow(windowTitle);
		try {
			UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(aWindow);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void displayAvailableLookAndFeels() {
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo look : looks) {
			System.out.println(look.getClassName());
		}
	}

	public static void main(String[] args) {
		displayAvailableLookAndFeels();
		InnerRunnable windowThread = new InnerRunnable();
		SwingUtilities.invokeLater(windowThread);
	}// end of main

	static class InnerRunnable implements Runnable {

		public void run() {
			createJFrameWindow("Default window");
		}
	}
}// end of class
