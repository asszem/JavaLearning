package GUI.JSamples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
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
			private int curveType;						// Type of the curve. 0=Quadratic, 1=Cubic
			private Point2D.Double startP;				// Start position of the curve
			private Point2D.Double endP;				// End position of the curve
			private Point2D.Double controlOne;			// Control position for Quad and Cubic curve
			private Point2D.Double controlTwo;			// Control position for Cubic curve
			private QuadCurve2D.Double quadCurve;		// The quadCurve object itself
			private CubicCurve2D.Double cubicCurve;		// The cubicCurve object itself
			private Color curveColor=Color.BLUE;		// The color of the Curve
			private int markerRadius = 5;				// The radius for the marker circle
			private QuadMarker quadMarker;				// Marker for Quad and Cube curve
			// private QuadMarker markerEnd; // Marker for Cube curve

			// Constructor to create a QUAD - one control points
			Curve(int curveType, Point2D.Double startP, Point2D.Double endP, Point2D.Double controlOne) {
				this.startP = startP;
				this.endP = endP;
				this.controlOne = controlOne;
				this.curveType = curveType;
				if (curveType == QUAD) {				// create the QUAD object only if the type is QUAD
					this.quadMarker = new QuadMarker(Color.RED);
					this.quadCurve = new QuadCurve2D.Double(startP.x, startP.y, controlOne.x, controlOne.y, endP.x,
							endP.y);
				}
			}// End of QUAD constructor

			// Constructor to create a CUBE - two control points
			Curve(int curveType, Point2D.Double startP, Point2D.Double endP, Point2D.Double controlStart,
					Point2D.Double controlEnd) {
				this(curveType, startP, endP, controlStart);  // call the Quad constructor
				this.controlTwo = controlEnd;					// define the end control
				// TODO create the cubic curve object
				// TODO create the CUBE marker
				// this.markerEnd = new QuadMarker(controlOne, markerRadius, Color.RED);
			}

			// Inner class to have all info for a QUAD marker - the circle and the lines
			class QuadMarker {

				// The class should have access to the private fields of Curve to be able to draw the marker and the lines
				Color markerColor;
				Ellipse2D.Double markerEllipse;
				Line2D.Double lineMarkerToStart;
				Line2D.Double lineMarkerToEnd;

				// Constructor
				QuadMarker(Color markerColor) {
					this.markerColor = markerColor;
					this.markerEllipse = new Ellipse2D.Double(controlOne.x - markerRadius, controlOne.y - markerRadius,
							markerRadius * 2, markerRadius * 2);
					this.lineMarkerToStart = new Line2D.Double(controlOne.x, controlOne.y, startP.x, startP.y);
					this.lineMarkerToEnd = new Line2D.Double(controlOne.x, controlOne.y, endP.x, endP.y);
				}

			}// End of QuadMarker

			// Inner class to handle mouse events for the curve object - or this should go for the DrawingPane?
			class MouseHandler extends MouseInputAdapter {

			}
		} // End of Curve inner class

		// Factory method to create a Curve object
		public Curve createCurve() {
			Point2D.Double startP = new Point2D.Double(100, 100);
			Point2D.Double endP = new Point2D.Double(200, 200);
			Point2D.Double controlStart = new Point2D.Double(200, 150);
			Curve testCurve = new Curve(QUAD, startP, endP, controlStart);
			return testCurve;
		}

		public void drawCurve(Graphics2D context, Curve curve) {
			context.draw(curve.quadCurve);						//Draw the curve itself
			context.setColor(curve.quadMarker.markerColor);
			context.draw(curve.quadMarker.markerEllipse);		//Draw the marker
																//Draw the connecting lines
			context.draw(curve.quadMarker.lineMarkerToStart);
			context.draw(curve.quadMarker.lineMarkerToEnd);
			context.setColor(curve.curveColor);
		}

		@Override // overrides JComponent paint method
		public void paint(Graphics g) { // this will get the graphics
			Graphics2D g2dcontext = (Graphics2D) g; // casts the graphics to Graphics2D type. This will be the graphics context to draw upon
			Curve testCurve = createCurve();
			drawCurve(g2dcontext, testCurve);

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
