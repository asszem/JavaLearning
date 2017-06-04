package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Window_Collection {

	public static JFrame createJFrameWindow(String windowTitle) {
		JFrame aWindow = new JFrame(windowTitle);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return aWindow;
	}

	public static void createJFrameWindow(String windowTitle, int posX, int posY, int width, int height) {
		createJFrameWindow(windowTitle).setBounds(posX, posY, width, height);
	}

	public static void createJFrameWindow(String windowTitle, LookAndFeelInfo look) {
		JFrame aWindow = createJFrameWindow(windowTitle);
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
		JFrame aWindow = createJFrameWindow(windowTitle);
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

	public static void colors() {

		Color myColor = new Color(000, 255, 100);
		SystemColor defaultWindowColor = SystemColor.window;
		if (myColor.getRGB() == defaultWindowColor.getRGB()) {
			createJFrameWindow("SetBackgroundcolor").getContentPane().setBackground(myColor);
		}
	}

	public static void windowPositionAndSize() {
		JFrame window = createJFrameWindow("Dimensions");
		Dimension windowSize = window.getSize();
		Point windowPosition = window.getLocation();
		window.setMaximumSize(windowSize);
		Dimension minimumSize=new Dimension(100, 100);
		window.setMinimumSize(minimumSize);
		Dimension preferredSize=new Dimension(500,500);
		window.setPreferredSize(preferredSize);
		Toolkit windowToolkit = window.getToolkit();
		Dimension screenSiz=windowToolkit.getScreenSize();
	}

	public static Point centerPosition(JFrame window){
		//Simple way:
		window.setLocationRelativeTo(null);
		
		//Get the center point of the current screen
		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		
		//Manual center calculation based on window size (dimension):
		Toolkit toolKit= window.getToolkit();
		Dimension screenSize = toolKit.getScreenSize();
		Point centerPosition=new Point();
		int halfWidth = window.getWidth()/2;
		int halfHeight = window.getHeight()/2;
		int screenHalfWidth= (int) (screenSize.getWidth()/2);
		int screenHalfHeight= (int) (screenSize.getHeight()/2);
		centerPosition.setLocation(screenHalfWidth-halfWidth, screenHalfHeight-halfHeight);
		return centerPosition;
	}
	
	public static void pointOperations(){

		Point point = new Point(100,100);
		point.move(200, 200); //Set the new position to 200, 200
		point.translate(10, 10); //Translates to x+deltaX, y+deltaY, in this case 210, 210
	}

	
public 	static void rectangleOperations(){
	Rectangle rectangle1=new Rectangle(100, 100, 200, 200); //x, y, widht, height
	Rectangle rectangle2=new Rectangle(50, 50, 200,200);
}
	
	public static void main(String[] args) {
		displayAvailableLookAndFeels();
		InnerRunnable windowThread = new InnerRunnable();
		SwingUtilities.invokeLater(windowThread);
	}// end of main

	static class InnerRunnable implements Runnable {

		public void run() {
			JFrame window=createJFrameWindow("Default window");
			window.setVisible(true); // Display the window
		}
	}
}// end of class
