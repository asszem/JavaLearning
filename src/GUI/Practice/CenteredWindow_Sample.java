package GUI.Practice;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CenteredWindow_Sample {

	public static Point manualCenterPosition(JFrame window) {
		Toolkit toolKit = window.getToolkit();
		Dimension screenSize = toolKit.getScreenSize();
		Point centerPosition = new Point();
		int halfWidth = window.getWidth() / 2;
		int halfHeight = window.getHeight() / 2;
		int screenHalfWidth = (int) (screenSize.getWidth() / 2);
		int screenHalfHeight = (int) (screenSize.getHeight() / 2);
		System.out.printf("Window w:%d, h:%d%nScreenHalf w:%d h:%d%n", halfWidth, halfHeight, screenHalfWidth,
				screenHalfHeight);
		centerPosition.setLocation(screenHalfWidth - halfWidth, screenHalfHeight - halfHeight);
		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		System.out.println("Center position coordinate:" + centerPoint);
		return centerPosition;
	}

	public static void main(String[] args) {
		Point point=new Point(100,100);
		System.out.println(point);
		point.move(200, 200);
		System.out.println(point);
		point.translate(10, 10);
		System.out.println(point);
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				JFrame aWindow = new JFrame("Centered window");
				aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				aWindow.setSize(400, 900);
				aWindow.setLocation(manualCenterPosition(aWindow));
				aWindow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				aWindow.setVisible(true);
			}
		});
	}

}
