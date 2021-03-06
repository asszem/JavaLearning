package GUI.Practice;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class LookAndFeel_Samples {

	public static int numberOfWindows = 0;

	public static JFrame createWindow(String windowTitle) {
		JFrame aWindow = new JFrame(windowTitle);
		int windowWidth = 930;
		int windowHeight = 100;
		// int windowXpos = (int) (Math.random() * 800);
		int windowXpos = 750;
		int windowYpos = 30 + 100 * (numberOfWindows++);
		aWindow.setBounds(windowXpos, windowYpos, windowWidth, windowHeight);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.setVisible(true); // Display the window
		return aWindow;
	}

	public static void createWindow(String windowTitle, LookAndFeelInfo look) {
		JFrame aWindow=createWindow(windowTitle);
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

	public static void createWindow(String windowTitle, String lookAndFeel) {
		JFrame aWindow=createWindow(windowTitle);
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
			createWindow("Default window");
			UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
			for (LookAndFeelInfo look : looks) {
				createWindow(look.getClassName(), look);
			}
			createWindow("Cross Platform Look and Feel", UIManager.getCrossPlatformLookAndFeelClassName());
			createWindow("System Look and Feel", UIManager.getSystemLookAndFeelClassName());
		}
	}
}// end of class
