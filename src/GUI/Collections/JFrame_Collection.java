package GUI.Collections;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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

public class JFrame_Collection {

	// Start an Event Dispatching Thread by using SwingUtilities.invokeLater
	public static void main(String[] args) {
		// displayAvailableLookAndFeels();
		InnerRunnable windowThread = new InnerRunnable();
		SwingUtilities.invokeLater(windowThread);
	}

	// Create a new JFrame object
	public static JFrame createJFrameWindow(String windowTitle) {
		JFrame aWindow = new JFrame(windowTitle);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return aWindow;
	}

	// Set window properties
	public static void setWindowBounds(JFrame window, int posX, int posY, int width, int height) {
		window.setBounds(posX, posY, width, height);
	}

	public static void setWindowMinMaxPreferredDimensions(JFrame window, Dimension minD, Dimension maxD,
			Dimension prefD) {
		Dimension windowSize = window.getSize();
		window.setMaximumSize(maxD);
		window.setMinimumSize(minD);
		window.setPreferredSize(prefD);
		//
		Toolkit windowToolkit = window.getToolkit();
		Dimension screenSiz = windowToolkit.getScreenSize();

	}

	public static void setWindowColor(JFrame window, Color bgColor, Color fgColor) {
		Color myColor = new Color(000, 255, 100);
		Color blueIsTheColour = Color.BLUE;
		window.getContentPane().setBackground(bgColor);
		window.getContentPane().setForeground(fgColor);

		// Verify if color is system color
		SystemColor defaultWindowColor = SystemColor.window;
		if (myColor.getRGB() == defaultWindowColor.getRGB()) {
			window.getContentPane().setBackground(myColor);
		}
	}

	public static void setCursor(JFrame window, Cursor cursor) {
		Cursor myCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		Cursor defaultCusor = Cursor.getDefaultCursor();
		Cursor predefinedCursor = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR);
		window.setCursor(cursor);
	}

	//Set font properties
	public static void setFont(JFrame window, Font font) {
		// Set the font both italic AND bold
		Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 12);
		Font myFont2 = new Font(getAllFontNames()[0], Font.PLAIN, 10);
		Font myFont3 = myFont2.deriveFont(Font.ITALIC, 12);
	}

	public static Font[] getAllFonts() {
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = e.getAllFonts(); // Get the font
		return fonts;
	}
	

	public static String[] getAllFontNames() {
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontnames = e.getAvailableFontFamilyNames();
		return fontnames;
	}

	public static Toolkit getToolkitForWindow(JFrame window) {
		Toolkit toolkit = window.getToolkit();
		toolkit.getScreenSize();
		// toolkit.createCustomCursor(cursor, hotSpot, name)
		// and many more methods
		return toolkit;
	}

	// Set Look and Feel
	public static void setWindowLookAndFeel(JFrame aWindow, LookAndFeelInfo look) {
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

	public static void setWindowLookAndFeel(JFrame aWindow, String lookAndFeel) {
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

	// Methods to manipulate Dimension objects - used for size
	public static void dimensionOperations() {
		Dimension aDimension = new Dimension(100, 200);
		aDimension.getHeight();
		aDimension.getWidth();
		aDimension.setSize(aDimension);
	}

	// Methods to manipulate Point objects - used for location
	public static void pointOperations() {
		Point aPoint = new Point(100, 100);
		aPoint.getX();
		aPoint.getY();
		aPoint.setLocation(200, 200); // Set the new position to 200,200
		aPoint.getLocation(); // Returns the location of this point
		aPoint.move(200, 200); // Identical to setLocaation
		aPoint.translate(10, 10); // Translates to x+deltaX, y+deltaY, in this case 210, 210
	}

	// Calculate center position for a given size window
	public static Point centerPosition(JFrame window) {
		// Simple way:
		window.setLocationRelativeTo(null);

		// Get the center point of the current screen
		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

		// Manual center calculation based on window size (dimension):
		Toolkit toolKit = window.getToolkit();
		Dimension screenSize = toolKit.getScreenSize();
		Point centerPosition = new Point();
		int halfWidth = window.getWidth() / 2;
		int halfHeight = window.getHeight() / 2;
		int screenHalfWidth = (int) (screenSize.getWidth() / 2);
		int screenHalfHeight = (int) (screenSize.getHeight() / 2);
		centerPosition.setLocation(screenHalfWidth - halfWidth, screenHalfHeight - halfHeight);
		return centerPosition;
	}

	// Methods to manipulate Rectangle objects - used for both location and size
	public static void rectangleOperations() {
		// Top Left x, y, widht, height
		Rectangle r1 = new Rectangle(100, 100, 200, 200);
		Rectangle r2 = new Rectangle(50, 50, 200, 200);
		boolean areIntersecting = r1.intersects(r2);
		Rectangle intersection = r1.intersection(r2);
		Rectangle union = r1.union(r2);
		r1.grow(10, 10);
		r1.add(new Point(100, 100));
	}

	// Runnable class to provide for the event dispatcher. This will display the JFrame
	static class InnerRunnable implements Runnable {

		public void run() {
			JFrame window = createJFrameWindow("Test window");
			setWindowBounds(window, 300, 300, 400, 400);
			setWindowColor(window, Color.BLUE, Color.RED);
			Dimension minD = new Dimension(200, 200);
			Dimension prefD = new Dimension(400, 400);
			Dimension maxD = new Dimension(600, 600);
			setWindowMinMaxPreferredDimensions(window, minD, maxD, prefD);
			setCursor(window, new Cursor(Cursor.HAND_CURSOR));
			window.setVisible(true); // Display the window
		}
	}
}// end of class
