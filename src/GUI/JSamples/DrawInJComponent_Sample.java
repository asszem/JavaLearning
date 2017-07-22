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
			setDrawColor(g2DContext, Color.BLACK);
			int startX = 200;
			int startY = 200;
			float startXF=(float) startX;
			float startYF=(float) startY;
			float width=100.0f;
			float height=100.0f;
			
			// Line
			drawString(g2DContext, "Line from start/end X,Y coordinates at "+startX+","+(startY-100), startX, startY-100);
			drawLine(g2DContext, startX, startY-100, startX + (int)width, startY - 100);

			drawString(g2DContext, "Line from two Point2D objects at " + startX+","+(startY-50), startX, startY-50);
			Point2D.Float startPoint = new Point2D.Float(startXF, startYF-50.0f);
			Point2D.Float endPoint = new Point2D.Float(startX+width, startY-50.0f); //Auto cast
			drawLine(g2DContext, startPoint, endPoint);

			// Rectangle
			drawString(g2DContext, "1st Rectangle at "+ startX+", "+startY, startX, startY);
			drawRectangle(g2DContext, startX, startY, (int)width, (int)height, true);
		
			int offsetX=125;
			int offsetY=140;
			float xF=startXF+offsetX;
			float yF=startYF+offsetY;
			Rectangle2D secondRectangle = new Rectangle2D.Float(xF, yF, width, height);
			drawString(g2DContext, "2nd Rectangle at "+xF+", "+yF, (int)xF, (int)yF);
			drawRectangle(g2DContext, secondRectangle);
		
			xF=startXF+offsetX+50;
			yF=startYF+offsetY+50;
			Rectangle2D thirdRectangle = new Rectangle2D.Float(xF, yF, width, height);
			drawString(g2DContext, "3rd Rectangle at "+xF+", "+yF, (int)xF, (int)yF);
			drawRectangle(g2DContext, thirdRectangle);

			setDrawColor(g2DContext, Color.RED);
			Rectangle2D rectangleIntersect=secondRectangle.createIntersection(thirdRectangle);
			drawRectangle(g2DContext, rectangleIntersect);

			setDrawColor(g2DContext, Color.GREEN);
			Rectangle2D rectangleUnion=secondRectangle.createUnion(thirdRectangle);
			drawRectangle(g2DContext, rectangleUnion);
			
			//Draw lines to connect Rectangle 1 and 2
			setDrawColor(g2DContext, Color.BLUE);
			drawLine(g2DContext, startX, startY, startX+offsetX, startY+offsetY);
			drawLine(g2DContext, startX, startY+(int) height, startX+offsetX, startY+offsetY+(int)height);
			drawLine(g2DContext, startX+(int) width, startY, startX+offsetX+(int)width, startY+offsetY);
			drawLine(g2DContext, startX+(int) width, startY+(int) height, startX+offsetX+(int) width, startY+offsetY+(int)height);
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
		window = new JFrame("Draw in JComponent Sample");
		window.setBounds(50, 50, 800, 800);
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
