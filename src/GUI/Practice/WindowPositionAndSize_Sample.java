package GUI.Practice;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import GUI.Window_Collection;

public class WindowPositionAndSize_Sample {

	JFrame window;

	public WindowPositionAndSize_Sample(String title) {
		window = Window_Collection.createJFrameWindow(title);
	}

	public void displayWindowPositionAndSize() {
		System.out.println("Window name:" + window.getName());
		System.out.println("Window title: "+ window.getTitle());
		Dimension windowSize = window.getSize();
		Dimension secondSize = window.getSize(windowSize);
		System.out.println("Dimensions - Dimension");
		System.out.printf("Width: %f, Height: %f%n", windowSize.getWidth(), windowSize.getHeight());
		System.out.printf("Width: %f, Height: %f%n", secondSize.getWidth(), secondSize.getHeight());
		System.out.println("Location - Point");
		Point windowPosition = window.getLocation();
		System.out.printf("PosX: %f, PosY: %f%n", windowPosition.getX(), windowPosition.getY());
		Rectangle windowPositionAndSize = window.getBounds();
		System.out.println("Position and dimension - Rectangle");
		System.out.printf("X:%f Y:%f W:%f H:%f", windowPositionAndSize.getX(), windowPositionAndSize.getY(),
				windowPositionAndSize.getWidth(), windowPositionAndSize.getHeight());
		Toolkit windowTk=window.getToolkit();
		Dimension screenSize=windowTk.getScreenSize();
		System.out.println("Window screen size: "+ screenSize);
	}

	public static void main(String[] args) {
		WindowPositionAndSize_Sample instance = new WindowPositionAndSize_Sample("Test Window 1");
		instance.displayWindowPositionAndSize();
	}
}
