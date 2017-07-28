package GUI.JSamples;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class JComponentCurveMouseHandler {

	JFrame applicationWindow;
	private final int QUAD = 0; 			// Curve type constant
	private final int CUBE = 1;				// Curve type constant

	// Inner class for the drawing pane
	@SuppressWarnings("serial")
	class DrawingPane extends JComponent {

		// Inner class for a curve object - so every button press will create a new object
		class Curve {

			// Non-static nested classes (inner classes) have access to other members of the enclosing class
			// even if they are declared private
			private int curveType;					// Type of the curve. 0=Quadratic, 1=Cubic
			private Point2D.Double startP;			// Start position of the curve
			private Point2D.Double endP;			// End position of the curve
			private Point2D.Double controlStart;	// Control position for Quad and Cubic curve
			private Point2D.Double controlEnd;		// Control position for Cubic curve
			private CircleMarker markerStart;		// Marker for Quad and Cube curve
			private CircleMarker markerEnd;			// Marker for Cube curve
			private int markerRadius = 5;			// The radius for the marker circle
			private QuadCurve2D quadCurve;			// The quadCurve object itself
			private CubicCurve2D cubicCurve;		// The cubicCurve object itself

			// Constructor to create the curve - this will be called by pressing the JButton
			Curve(int curveType, Point2D.Double startP, Point2D.Double endP, Point2D.Double controlStart,
					Point2D.Double controlEnd) {
				this.startP = startP;
				this.endP = endP;
				this.controlStart = controlStart;
				this.controlEnd = controlEnd;
				this.curveType = curveType;
				// Create the marker object
				this.markerStart = new CircleMarker();
				this.markerEnd = new CircleMarker();
				this.quadCurve = createQuadCurve();
			}// End of constructor

			// Method to be called to create a curve - or just use the constructor?
			public QuadCurve2D.Double createQuadCurve() {
				return new QuadCurve2D.Double(startP.x, endP.x, controlStart.x, controlEnd.y, endP.x, endP.y);
			}

			// Method to call to redraw the curve
			public void redrawCurve() {

			}

			// Inner class to draw circle for the curve
			class CircleMarker {
				// The class should have access to the private fields of Curve to be able to draw the marker and the lines
			}// End of CircleMarker

			// Inner class to handle mouse events for the curve object - or this should go for the DrawingPane?
			class MouseHandler extends MouseInputAdapter {

			}
		} // End of Curve inner class

		// To draw the curve
		public void drawCurve() {

		}

		@Override // overrides JComponent paint method
		public void paint(Graphics g) { // this will get the graphics
			Graphics2D g2dcontext = (Graphics2D) g; // casts the graphics to Graphics2D type. This will be the graphics context to draw upon

			// TEMP
			// Create a curve object
			Point2D.Double startP = new Point2D.Double(100, 100);
			Point2D.Double endP = new Point2D.Double(200, 200);
			Point2D.Double controlStart = new Point2D.Double(150, 150);
			Point2D.Double controlEnd = new Point2D.Double(100, 100);
			Curve testCurve = new Curve(QUAD, startP, endP, controlStart, controlEnd);
			g2dcontext.draw(testCurve.quadCurve);
			// Draw it

		}// End of paint
	}// End of DrawingPane inner class

	public void createFrame() {
		applicationWindow = new JFrame("Curve with dragabble markers");
		applicationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		applicationWindow.setBounds(50, 50, 1200, 1000);
		// Create and add the drawing pane to the main content pane
		DrawingPane drawingPane = new DrawingPane();
		applicationWindow.getContentPane().add(drawingPane, BorderLayout.CENTER);
		applicationWindow.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JComponentCurveMouseHandler theAppInstance = new JComponentCurveMouseHandler();
				theAppInstance.createFrame();
			}
		});
	}// end main

}// end of JComponentCurveMouseHandler class
