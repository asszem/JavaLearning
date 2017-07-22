package GUI.JSamples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DrawInJComponent_Sample {

	JFrame window;

	// Inner class implementing JComponent's paint() and Observer's update() methods
	// Class is static so static methods can be used
	@SuppressWarnings("serial")
	class innerJComponentView extends JComponent implements Observer {

		public void setDrawColor(Graphics2D context, Color color) {
			context.setPaint(color);
		}

		public void drawString(Graphics2D context, String text, int x, int y) {
			context.drawString(text, x, y);
		}

		public void drawLine(Graphics2D context, int startX, int startY, int endX, int endY) {
			context.drawLine(startX, startY, endX, endY);
		}

		public void drawLine(Graphics2D context, Point2D.Float start, Point2D.Float end) {
			Line2D.Float line = new Line2D.Float(start, end);
			context.draw(line);
		}

		public void drawLine(Graphics2D context, Line2D.Float line) {
			context.draw(line);
		}

		public void drawRectangle(Graphics2D context, int startX, int startY, int width, int height, boolean raised) {
			context.draw3DRect(startX, startY, width, height, raised);
		}

		public void drawRectangle(Graphics2D context, Rectangle2D rectangle) {
			context.draw(rectangle);
		}

		// This method will do the actual drawing
		@Override
		public void paint(Graphics g) {
			Graphics2D g2DContext = (Graphics2D) g;
			setDrawColor(g2DContext, Color.RED);
			int startX = 200;
			int startY = 200;
			float startXF=(float) startX;
			float startYF=(float) startY;
			
			// Line
			drawString(g2DContext, "Line from start/end X,Y coordinates", startX, startY-100);
			drawLine(g2DContext, startX, startY-100, startX + 100, startY - 100);

			drawString(g2DContext, "Line from two Point2D objects", startX, startY-50);
			Point2D.Float startPoint = new Point2D.Float(startXF, startYF-50.0f);
			Point2D.Float endPoint = new Point2D.Float(startX+100.0f, startY-50.0f); //Auto cast
			drawLine(g2DContext, startPoint, endPoint);

			// Rectangle
			drawString(g2DContext, "Rectangle at "+startX+", "+startY, startX, startY);
			drawRectangle(g2DContext, startX, startY, 100, 100, true);
			
			Rectangle2D rectangle2D = new Rectangle2D.Float(startXF+200, startYF+200, 100, 100);
			drawString(g2DContext, "Rectangle at "+(startX+200)+", "+(startY+200), startX+200, startY+200);
			drawRectangle(g2DContext, rectangle2D);
			

		}

		@Override
		public void update(Observable o, Object arg) {
			// Actions to take when the Observable object changes
		}
	}

	// Factory method to instantiate a JComponent object
	public JComponent createJComponent() {
		return new innerJComponentView();
	}

	// Method to setup the JFrame
	public void setupJFrame() {
		window = new JFrame("Draw in JComponent Skeleton");
		window.setBounds(50, 50, 600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent viewJComponent = this.createJComponent(); // Create the JComponent on the AppInstance
		window.getContentPane().add(viewJComponent, BorderLayout.CENTER); // Add the jComponent to the CENTER
		window.setVisible(true);
	}

	// Main method to start Event Dispatcher thread and call createJFrame method
	public static void main(String[] args) {
		DrawInJComponent_Sample theAppInstance = new DrawInJComponent_Sample();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				theAppInstance.setupJFrame();
			}
		});
	}
}
