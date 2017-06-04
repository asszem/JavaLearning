package GUI.Practice;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GUI_Sample {

	public static JFrame createTestWindow(String windowTitle) {
		JFrame aWindow = new JFrame(windowTitle);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color myColor = new Color(000, 255, 000, 255);
		Rectangle rectangleTest= new Rectangle(900,300,800,200);
		aWindow.setBounds(rectangleTest);
		aWindow.getRootPane().setBackground(Color.RED);
		aWindow.getContentPane().setBackground(myColor);
		aWindow.setVisible(true); // Display the window
		System.out.println(myColor);
		System.out.println(myColor.getRGB());
		System.out.println(myColor.getGreen());
		return aWindow;
	}

	public static void main(String[] args) {
		SimpleJFrame jFrameWindow = new SimpleJFrame();
		// SwingUtilities.invokeLater(jFrameWindow);
		Dimension jFrameWindowDimension = jFrameWindow.simpleJFrame.getSize();
		System.out.println(jFrameWindowDimension.height);
		System.out.println(SystemColor.window);
		
		Cursor myCursor = new Cursor(Cursor.TEXT_CURSOR);	
		
		
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				JFrame window=createTestWindow("GUI_Sample");
			}
		});
	}

}
