package GUI.Practice;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class RectangleOperations_Sample {

	public static void rectangleOperations(Rectangle r1, Rectangle r2) {
		System.out.println("Rectangle 1: " + r1);
		System.out.println("Rectangle 2: " + r2);
		System.out.println("R1 before update contains R2?"+ r1.contains(r2));
		System.out.println("Rectangle 1 is empty? "+ r1.isEmpty());
		System.out.println("R1 intersects R2?:" + r1.intersects(r2));
		System.out.println("Intersection rectangle:");
		System.out.println(r1.intersection(r2));
		System.out.println("R1 union with R2: " + r1.union(r2));
		System.out.println("Rectangle add - r1 to enclose r2");
		r1.add(r2); //void
		System.out.println("R1 now:"+ r1);
		System.out.println("Rectangel grow - r1 to grow to each direction");
		r1.grow(10, 10);
		System.out.println("R1 now:"+ r1);
		System.out.println("R1 afrer update contains R2?"+ r1.contains(r2));
		
	}

	static void createWindow(String title, Rectangle dimension) {
		JFrame window = new JFrame();
		window.setTitle(title);
		window.setBounds(dimension);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		Rectangle rectangle1 = new Rectangle(50, 50, 800, 300); // x, y, width, height
		Rectangle rectangle2 = new Rectangle(200, 200, 800, 300);
		Rectangle rectangle3 = new Rectangle(800, 600, 400, 400);
		rectangleOperations(rectangle1, rectangle2);
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				createWindow("Window 1", rectangle1);
				createWindow("Window 2", rectangle2);
				createWindow("Intersection", rectangle1.intersection(rectangle2));
				createWindow("Union", rectangle1.union(rectangle2));
				createWindow("Original", rectangle3);
				rectangle3.add(1400,600); 
				createWindow("Add", rectangle3);
				rectangle3.grow(10, 10);
				createWindow("Grow", rectangle3);


			}
		});
	}
}
