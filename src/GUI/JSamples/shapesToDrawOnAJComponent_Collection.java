package GUI.JSamples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class shapesToDrawOnAJComponent_Collection {

	JFrame window;

	// Inner class implementing JComponent's paint() and Observer's update() methods
	// Class is static so static methods can be used
	@SuppressWarnings("serial")
	class innerJComponentView extends JComponent implements Observer, MouseListener {

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

		public void drawEllipse(Graphics2D context, Point2D.Double start, int width, int height) {
			Ellipse2D ellipse = new Ellipse2D.Double(start.x, start.y, width, height);
			drawString(context, "Ellipse2D at x=" + start.x + " y=" + start.y + " w=" + width + " h=" + height,
					(int) start.x, (int) (start.y - 10));
			context.draw(ellipse);
		}

		// Note: Closure type is not included in parameter list.
		public void drawArc(Graphics2D context, Point2D.Double start, int width, int height, int startAngle,
				int extendAngle) {
			Arc2D.Double arch = new Arc2D.Double(start.x, start.y, width, height, startAngle, extendAngle, Arc2D.OPEN);
			Arc2D.Double arch2 = new Arc2D.Double(start.x, start.y + 100, width, height, startAngle, extendAngle,
					Arc2D.CHORD);
			Arc2D.Double arch3 = new Arc2D.Double(start.x, start.y + 200, width, height, startAngle, extendAngle,
					Arc2D.PIE);
			drawString(context, "Arch2D " + start.x + "," + start.y + " w=" + width + " h=" + height + " startAngle="
					+ startAngle + " extentAngle=" + extendAngle, (int) start.x, (int) (start.y - 60));
			drawString(context, "Arch2D.OPEN", (int) start.x, (int) (start.y - 30));
			context.draw(arch);
			drawString(context, "Arch2D.CHORD", (int) start.x, (int) (start.y + 70));
			context.draw(arch2);
			drawString(context, "Arch2D.PIE", (int) start.x, (int) (start.y + 170));
			context.draw(arch3);
		}

		public void drawCircle(Graphics2D context, Point2D.Double circleCenter, int radius) {
			int offsetY = 15;
			Point textPos = new Point((int) (circleCenter.x - radius), (int) (circleCenter.y - radius - offsetY));
			drawString(context,
					"Circle from radius=" + radius + " at center x=" + circleCenter.x + " y=" + circleCenter.y,
					textPos.x, textPos.y);

			Ellipse2D.Double circle = new Ellipse2D.Double(circleCenter.x - radius, circleCenter.y - radius, radius * 2,
					radius * 2);
			context.draw(circle);
		}

		public void drawQuadCurve(Graphics2D context, Point2D.Double startP, Point2D.Double endP,
				Point2D.Double tangent) {
			int offset = 25;
			Point textPos = new Point((int) startP.x, (int) startP.y - offset);
			String message = String.format("Quad curve startP(%.0f,%.0f), endP(%.0f,%.0f)", startP.x, startP.y,
					endP.x, endP.y);
			String message2 = String.format("TangentP(%.0f,%.0f)", tangent.x, tangent.y);
			drawString(context, message, textPos.x, textPos.y);
			drawString(context, message2, textPos.x, textPos.y+20);
			QuadCurve2D.Double qCurve = new QuadCurve2D.Double(startP.x, startP.y, endP.x, endP.y, tangent.x, tangent.y);
			context.draw(qCurve);
			
		}

		// This method will do the actual drawing by calling the specific methods
		@Override
		public void paint(Graphics g) {
			Graphics2D g2DContext = (Graphics2D) g;
			setDrawColor(g2DContext, Color.BLACK);
			int startX = 200;
			int startY = 200;
			float startXF = (float) startX;
			float startYF = (float) startY;
			float width = 100.0f;
			float height = 100.0f;

			// Line
			drawString(g2DContext, "Line from start/end X,Y coordinates at " + startX + "," + (startY - 100), startX,
					startY - 100);
			drawLine(g2DContext, startX, startY - 100, startX + (int) width, startY - 100);

			drawString(g2DContext, "Line from two Point2D objects at " + startX + "," + (startY - 50), startX,
					startY - 50);
			Point2D.Float startPoint = new Point2D.Float(startXF, startYF - 50.0f);
			Point2D.Float endPoint = new Point2D.Float(startX + width, startY - 50.0f); // Auto cast
			endPoint.setLocation(startX + width, startY - 50.0f);
			drawLine(g2DContext, startPoint, endPoint);

			// Rectangle
			drawString(g2DContext, "1st Rectangle at " + startX + ", " + startY, startX, startY);
			drawRectangle(g2DContext, startX, startY, (int) width, (int) height, true);

			int offsetX = 125;
			int offsetY = 140;
			float xF = startXF + offsetX;
			float yF = startYF + offsetY;
			Rectangle2D secondRectangle = new Rectangle2D.Float(xF, yF, width, height);
			drawString(g2DContext, "2nd Rectangle at " + xF + ", " + yF, (int) xF, (int) yF);
			drawRectangle(g2DContext, secondRectangle);

			xF = startXF + offsetX + 50;
			yF = startYF + offsetY + 50;
			Rectangle2D thirdRectangle = new Rectangle2D.Float(xF, yF, width, height);
			drawString(g2DContext, "3rd Rectangle at " + xF + ", " + yF, (int) xF, (int) yF);
			drawRectangle(g2DContext, thirdRectangle);

			setDrawColor(g2DContext, Color.RED);
			Rectangle2D rectangleIntersect = secondRectangle.createIntersection(thirdRectangle);
			drawRectangle(g2DContext, rectangleIntersect);

			setDrawColor(g2DContext, Color.GREEN);
			Rectangle2D rectangleUnion = secondRectangle.createUnion(thirdRectangle);
			drawRectangle(g2DContext, rectangleUnion);

			// Draw lines to connect Rectangle 1 and 2
			setDrawColor(g2DContext, Color.BLUE);
			drawLine(g2DContext, startX, startY, startX + offsetX, startY + offsetY);
			drawLine(g2DContext, startX, startY + (int) height, startX + offsetX, startY + offsetY + (int) height);
			drawLine(g2DContext, startX + (int) width, startY, startX + offsetX + (int) width, startY + offsetY);
			drawLine(g2DContext, startX + (int) width, startY + (int) height, startX + offsetX + (int) width,
					startY + offsetY + (int) height);

			// Contains method for Rectangle shape
			System.out.print("Second rectangle contains rectangleIntersect=");
			System.out.println(secondRectangle.contains(rectangleIntersect));
			System.out.print("RectangleIntersect contains startPoint=");
			System.out.println(rectangleIntersect.contains(startPoint));
			System.out.println("RectangleIntersect contains rectangleUnoion="
					+ rectangleIntersect.contains(rectangleUnion.getBounds2D()));

			// GetBounds2D method for Rectangle shape
			System.out.println("Second rectangle width=" + secondRectangle.getBounds2D().getWidth());
			System.out.println("Second rectangle height=" + secondRectangle.getBounds2D().getHeight());

			// Draw Ellipse
			Point2D.Double ellipseStart = new Point2D.Double(600, 50);
			drawEllipse(g2DContext, ellipseStart, 200, 100);

			// Draw a Circle (width and height are the same)
			Point2D.Double circleStart = new Point2D.Double(900, 50);
			drawEllipse(g2DContext, circleStart, 100, 100);

			// Draw a circle provided by Radius and CenterXY
			Point2D.Double circleCenter = new Point2D.Double(1000, 400);
			int radius = 100;
			drawCircle(g2DContext, circleCenter, radius);

			// Draw Arch
			Point2D.Double arcStart = new Point2D.Double(600, 300);
			drawArc(g2DContext, arcStart, 200, 100, 30, 100);
			
			// Draw Quad Curve
			Point2D.Double start= new Point2D.Double(900, 550);
			Point2D.Double end= new Point2D.Double(1300,  650);
			Point2D.Double tangent=new Point2D.Double(850,  600);
			drawQuadCurve(g2DContext, start, end, tangent);
			start.setLocation(1200, 550);
			end.setLocation(1400, 650);
			tangent.setLocation(1300, 600);
			drawQuadCurve(g2DContext, start, end, tangent);
		}

		// Mouse Listener impelmented objects - TODO remove from this subclass, create own
		@Override
		public void update(Observable o, Object arg) {
			// Actions to take when the Observable object changes
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("Mouse entered");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	// Factory method to instantiate a JComponent object
	public JComponent createJComponent() {
		return new innerJComponentView();
	}

	// Method to setup the JFrame
	public void setupJFrame() {
		window = new JFrame("Drawing Shapes to a JComponent: Line2D, Rectangle2D, Ellipse2D, Arc2D");
		window.setBounds(50, 50, 1500, 900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent viewJComponent = this.createJComponent(); // Create the JComponent on the AppInstance
		viewJComponent.addMouseListener((MouseListener) viewJComponent);
		window.getContentPane().add(viewJComponent, BorderLayout.CENTER); // Add the jComponent to the CENTER
		window.setVisible(true);
	}

	// Main method to start Event Dispatcher thread and call createJFrame method
	public static void main(String[] args) {
		shapesToDrawOnAJComponent_Collection theAppInstance = new shapesToDrawOnAJComponent_Collection();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				theAppInstance.setupJFrame();
			}
		});
	}
}
