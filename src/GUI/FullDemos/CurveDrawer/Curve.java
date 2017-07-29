package GUI.FullDemos.CurveDrawer;

import java.awt.Color;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import static GUI.FullDemos.CurveDrawer.CurveDrawer.QUAD;
import static GUI.FullDemos.CurveDrawer.CurveDrawer.CUBE;

public class Curve {

	private CurveDrawer appInstance;
	private int curveType;						// Type of the curve. 0=Quadratic, 1=Cubic
	private Point2D.Double startP;				// Start position of the curve
	private Point2D.Double endP;				// End position of the curve
	private Point2D.Double controlOne;			// Control position for Quad and Cubic curve
	private Point2D.Double controlTwo;			// Control position for Cubic curve
	private QuadCurve2D.Double quadCurve;		// The quadCurve object itself
	private CubicCurve2D.Double cubicCurve;		// The cubicCurve object itself
	private Color curveColor = Color.BLUE;		// The color of the Curve
	private int markerRadius = 5;				// The radius for the marker circle
	private QuadMarker quadMarker;				// Marker for Quad and Cube curve
	// private QuadMarker markerEnd; // Marker for Cube curve

	// Constructor to create a QUAD - one control point
	private Curve(CurveDrawer appInstance, int curveType, Point2D.Double startP, Point2D.Double endP,
			Point2D.Double controlOne) {
		this.appInstance = appInstance;
		this.startP = startP;
		this.endP = endP;
		this.controlOne = controlOne;
		this.curveType = curveType;
		if (curveType == QUAD) {				// create the QUAD object only if the type is QUAD
			this.quadMarker = new QuadMarker(Color.RED);
			this.setQuadCurve(new QuadCurve2D.Double(startP.x, startP.y, controlOne.x, controlOne.y, endP.x, endP.y));
			appInstance.getCurves().add(this); // Add the newly created curve to the array list of curves
		}
	}// End of QUAD constructor

	// TODO complete constructor
	// Constructor to create a CUBE - two control points
	Curve(int curveType, Point2D.Double startP, Point2D.Double endP, Point2D.Double controlStart,
			Point2D.Double controlEnd) {
		// this(curveType, startP, endP, controlStart); // call the Quad constructor
		// this.controlTwo = controlEnd; // define the end control
		// TODO create the cubic curve object
		// TODO create the CUBE marker
		// this.markerEnd = new QuadMarker(controlOne, markerRadius, Color.RED);
	}

	// Factory method to create a Curve object and add to the curves ArrayList
	// static so it can be called when there is no Curve object created yet
	public static Curve createQuadCurve(CurveDrawer appInstance, Point2D.Double startP, Point2D.Double endP,
			Point2D.Double controlOne) {
		Curve newCurve = new Curve(appInstance, QUAD, startP, endP, controlOne);
		return newCurve;
	}

	// This method can update the coordinates of a curve - to be called from the mouse adapter
	public void updateCurve(Curve curve) {
		curve.startP.setLocation(1000, 500);
		curve.controlOne.setLocation(600, 300);
		curve.getQuadCurve().setCurve(curve.startP, curve.controlOne, curve.endP);
		curve.quadMarker.setQuadMarker(Color.GREEN);
	}

	public QuadCurve2D.Double getQuadCurve() {
		return quadCurve;
	}
	public QuadMarker getQuadMarker(){
		return getQuadMarker();
	}
	public Color getCurveColor(){
		return curveColor;
	}

	public void setQuadCurve(QuadCurve2D.Double quadCurve) {
		this.quadCurve = quadCurve;
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

		// Public method to set the QuadMarker coordinates
		public void setQuadMarker(Color markerColor) {
			this.markerColor = markerColor;
			this.markerEllipse.setFrame(controlOne.x - markerRadius, controlOne.y - markerRadius, markerRadius * 2,
					markerRadius * 2);
			// this.lineMarkerToStart.setLine(controlOne.x, controlOne.y, startP.x, startP.y);
			// this.lineMarkerToEnd.setLine(controlOne.x, controlOne.y, endP.x, endP.y);
		}

	}// End of QuadMarker

} // End of Curve class
