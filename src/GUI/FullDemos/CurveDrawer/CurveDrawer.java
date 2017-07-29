package GUI.FullDemos.CurveDrawer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class CurveDrawer {

	public static final int QUAD = 0; 			// Curve type constant
	public static final int CUBE = 1;				// Curve type constant
	private static CurveDrawerFrame window;
	private ArrayList<Curve> curves;
	private DrawingPane drawingPane;

	public ArrayList<Curve> getCurves() {
		return curves;
	}

	public DrawingPane getDrawingPane() {
		return drawingPane;
	}

	// Constructor to create a new app instance
	CurveDrawer() {
		curves = new ArrayList<>();
		drawingPane = new DrawingPane(this); // passing the reference of the CurveDrawer object
		window = new CurveDrawerFrame(this);
		createNewCurve();
	}

	// TEMP method to create one curve
	public Curve createNewCurve() {
		Point2D.Double startP = new Point2D.Double(100, 100);
		Point2D.Double endP = new Point2D.Double(200, 200);
		Point2D.Double controlP = new Point2D.Double(200, 130);
		Curve newCurve = Curve.createQuadCurve(this, startP, endP, controlP);
		return newCurve;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				CurveDrawer theAppInstance = new CurveDrawer();
			}
		});
	}// end main

}// end of CurveDrawer class
/*
 * class DrawingPane extends JComponent {
 * 
 * private MouseHandler mouseHandler = new MouseHandler(); // The MouseHandler object for the whole drawing pane private ArrayList<Curve> curves = new ArrayList<>(); // Reference to hold all curves //
 * Inner class to handle mouse events for the curve object - or this should go for the DrawingPane?
 * 
 * class MouseHandler extends MouseInputAdapter {
 * 
 * @Override public void mousePressed(MouseEvent m) { System.out.println("Mouse pressed"); // walk through the available curves and see if the pressed location is within the marker's location
 * System.out.println("curves lenght:" + curves.size()); for (Curve curve : curves) { System.out.println("Curve ControlOne: " + curve.controlOne); } } } // End of MouseHandler class
 * 
 * // Inner class for a curve object - so every button press will create a new object class Curve {
 * 
 * // Non-static nested classes (inner classes) have access to other members of the enclosing class // even if they are declared private private int curveType; // Type of the curve. 0=Quadratic,
 * 1=Cubic private Point2D.Double startP; // Start position of the curve private Point2D.Double endP; // End position of the curve private Point2D.Double controlOne; // Control position for Quad and
 * Cubic curve private Point2D.Double controlTwo; // Control position for Cubic curve private QuadCurve2D.Double quadCurve; // The quadCurve object itself private CubicCurve2D.Double cubicCurve; // The
 * cubicCurve object itself private Color curveColor = Color.BLUE; // The color of the Curve private int markerRadius = 5; // The radius for the marker circle private QuadMarker quadMarker; // Marker
 * for Quad and Cube curve // private QuadMarker markerEnd; // Marker for Cube curve
 * 
 * // Constructor to create a QUAD - one control points Curve(int curveType, Point2D.Double startP, Point2D.Double endP, Point2D.Double controlOne) { this.startP = startP; this.endP = endP;
 * this.controlOne = controlOne; this.curveType = curveType; if (curveType == QUAD) { // create the QUAD object only if the type is QUAD this.quadMarker = new QuadMarker(Color.RED); this.quadCurve =
 * new QuadCurve2D.Double(startP.x, startP.y, controlOne.x, controlOne.y, endP.x, endP.y); } }// End of QUAD constructor
 * 
 * // TODO complete constructor // Constructor to create a CUBE - two control points Curve(int curveType, Point2D.Double startP, Point2D.Double endP, Point2D.Double controlStart, Point2D.Double
 * controlEnd) { this(curveType, startP, endP, controlStart); // call the Quad constructor this.controlTwo = controlEnd; // define the end control // TODO create the cubic curve object // TODO create
 * the CUBE marker // this.markerEnd = new QuadMarker(controlOne, markerRadius, Color.RED); }
 * 
 * // Inner class to have all info for a QUAD marker - the circle and the lines class QuadMarker {
 * 
 * // The class should have access to the private fields of Curve to be able to draw the marker and the lines Color markerColor; Ellipse2D.Double markerEllipse; Line2D.Double lineMarkerToStart;
 * Line2D.Double lineMarkerToEnd;
 * 
 * // Constructor QuadMarker(Color markerColor) { this.markerColor = markerColor; this.markerEllipse = new Ellipse2D.Double(controlOne.x - markerRadius, controlOne.y - markerRadius, markerRadius * 2,
 * markerRadius * 2); this.lineMarkerToStart = new Line2D.Double(controlOne.x, controlOne.y, startP.x, startP.y); this.lineMarkerToEnd = new Line2D.Double(controlOne.x, controlOne.y, endP.x, endP.y); }
 * 
 * // Public method to set the QuadMarker coordinates public void setQuadMarker(Color markerColor) { this.markerColor = markerColor; this.markerEllipse.setFrame(controlOne.x - markerRadius,
 * controlOne.y - markerRadius, markerRadius * 2, markerRadius * 2); // this.lineMarkerToStart.setLine(controlOne.x, controlOne.y, startP.x, startP.y); // this.lineMarkerToEnd.setLine(controlOne.x,
 * controlOne.y, endP.x, endP.y); }
 * 
 * }// End of QuadMarker
 * 
 * } // End of Curve inner class
 * 
 * // Factory method to create a Curve object and add to the curves ArrayList public Curve createCurve() { Point2D.Double startP = new Point2D.Double(100, 100); Point2D.Double endP = new
 * Point2D.Double(200, 200); Point2D.Double controlStart = new Point2D.Double(200, 150); Curve newCurve = new Curve(QUAD, startP, endP, controlStart); // Register the new curve in the ArrayList curves
 * curves.add(newCurve); return newCurve; }
 * 
 * // The paint() method will call this to paint the Curve and the Marker with the given coordinates public void drawCurve(Graphics2D context, Curve curve) { context.draw(curve.quadCurve); // Draw the
 * curve itself context.setColor(curve.quadMarker.markerColor); context.draw(curve.quadMarker.markerEllipse); // Draw the marker // Draw the connecting lines
 * context.draw(curve.quadMarker.lineMarkerToStart); context.draw(curve.quadMarker.lineMarkerToEnd); context.setColor(curve.curveColor); }
 * 
 * // This method can update the coordinates of a curve - to be called from the mouse adapter public void updateCurve(Curve curve) { curve.startP.setLocation(1000, 500);
 * curve.controlOne.setLocation(600, 300); curve.quadCurve.setCurve(curve.startP, curve.controlOne, curve.endP); curve.quadMarker.setQuadMarker(Color.GREEN); }
 * 
 * @Override // overrides JComponent paint method public void paint(Graphics g) { // this will get the graphics System.out.println("curves at beginning of paint" + curves.size()); Graphics2D g2dcontext
 * = (Graphics2D) g; // casts the graphics to Graphics2D type. This will be the graphics context to draw upon // Draw all curves from for (Curve curve : curves) { updateCurve(curve);
 * drawCurve(g2dcontext, curve); }
 * 
 * }// End of paint
 * 
 * }// End of DrawingPane inner class
 */
