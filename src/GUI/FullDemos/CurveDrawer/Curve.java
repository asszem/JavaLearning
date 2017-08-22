package GUI.FullDemos.CurveDrawer;

import java.awt.Color;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.Random;

import static GUI.FullDemos.CurveDrawer.CurveApp.QUAD;
import static GUI.FullDemos.CurveDrawer.CurveApp.CUBE;

public class Curve {

	private CurveApp appInstance;				// A reference to the CurveApp object, which is the app instance
	private int curveType;						// Type of the curve. 0=Quadratic, 1=Cubic
	private Point2D.Double startP;				// Start position of the curve
	private Point2D.Double endP;				// End position of the curve
	private Point2D.Double controlOne;			// Control position for Quad and Cubic curve
	private Point2D.Double controlTwo;			// Control position for Cubic curve
	private QuadCurve2D.Double quadCurve;		// The quadCurve object itself
	private CubicCurve2D.Double cubicCurve;		// The cubicCurve object itself
	private Color curveColor = Color.BLUE;		// The color of the Curve
	private int markerRadius = 5;				// The radius for the marker circle
	private QuadMarker quadMarker;				// Marker for Quad curve
	private CubeMarker cubeMarker;				// Marker object for Cube curve
	// private QuadMarker markerEnd; // Marker for Cube curve

	// Constructor to create a QUAD - one control point
	private Curve(CurveApp appInstance, int curveType, Point2D.Double startP, Point2D.Double endP,
			Point2D.Double controlOne) {
		this.appInstance = appInstance;
		this.startP = startP;
		this.endP = endP;
		this.controlOne = controlOne;
		this.curveType = curveType;
		if (curveType == QUAD) {				// instantiate a QuadCurve2D.Double object only if the type is QUAD
			this.quadMarker = new QuadMarker(Color.RED);
			this.setQuadCurve(new QuadCurve2D.Double(startP.x, startP.y, controlOne.x, controlOne.y, endP.x, endP.y));
			appInstance.getCurves().add(this); // Add the newly created curve to the array list of curves
			System.out.println("Quad curve created. Curve count: " + appInstance.getCurves().size());
		}
	}// End of QUAD constructor

	// Constructor to create a CUBE - two control points
	Curve(CurveApp appInstance, int curveType, Point2D.Double startP, Point2D.Double endP, Point2D.Double controlStart,
			Point2D.Double controlEnd) {
		this(appInstance, curveType, startP, endP, controlStart);  // Call the basic constructor to create these
		this.controlTwo = controlEnd; 								 // define the end control for the CUBE
		this.cubeMarker = new CubeMarker(Color.CYAN, Color.BLUE);
		this.setCubeCurve(new CubicCurve2D.Double(startP.x, startP.y, controlOne.x, controlOne.y, controlTwo.x,
				controlTwo.y, endP.x, endP.y));
		appInstance.getCurves().add(this); // Add the newly created curve to the array list of curves
		System.out.println("Cube curve created. New Curve count: " + appInstance.getCurves().size());
	}

	// Factory method to create a Curve object and add to the curves ArrayList
	// Static so it can be called when there is no Curve object created yet
	// Creates QUAD curve with one control point only
	public static Curve createCurve(CurveApp appInstance, Point2D.Double startP, Point2D.Double endP,
			Point2D.Double controlOne) {
		Curve newCurve = new Curve(appInstance, QUAD, startP, endP, controlOne);
		return newCurve;
	}

	// Creates CUBE curve with two control points
	public static Curve createCurve(CurveApp appInstance, Point2D.Double startP, Point2D.Double endP,
			Point2D.Double controlOne, Point2D.Double controlTwo) {
		Curve newCurve = new Curve(appInstance, CUBE, startP, endP, controlOne, controlTwo);
		return newCurve;
	}

	// This method can update the coordinates of a curve - to be called from the mouse adapter
	public void updateCurve(Point2D.Double newStartP, Point2D.Double newEndP, Point2D.Double newControlOne,
			Point2D.Double newControlTwo) {
		startP.setLocation(newStartP);
		endP.setLocation(newEndP);
		controlOne.setLocation(newControlOne);
		if (curveType == QUAD) {
			getQuadCurve().setCurve(startP, controlOne, endP);
			quadMarker.updateQuadMarker();
		}
		if (curveType == CUBE) {
			controlTwo.setLocation(newControlTwo);
			getCubeCurve().setCurve(startP, controlOne, controlTwo, endP);
			cubeMarker.updateCubeMarker();
		}
		// appInstance.getDrawingPane().repaint();
	}

	public QuadCurve2D.Double getQuadCurve() {
		return quadCurve;
	}

	public void setQuadCurve(QuadCurve2D.Double quadCurve) {
		this.quadCurve = quadCurve;
	}

	public CubicCurve2D.Double getCubeCurve() {
		return cubicCurve;
	}

	public void setCubeCurve(CubicCurve2D.Double cubeCurve) {
		this.cubicCurve = cubeCurve;
	}

	public Color getCurveColor() {
		return curveColor;
	}

	public int getCurveType() {
		return curveType;
	}

	public Point2D.Double getStartP() {
		return startP;
	}

	public Point2D.Double getEndP() {
		return endP;
	}

	public QuadMarker getQuadMarker() {
		return quadMarker;
	}

	public CubeMarker getCubeMarker() {
		return cubeMarker;
	}

	public Point2D.Double getControlOne() {		// getter to get the ControlPoint's coordinates
		return controlOne;
	}

	public Point2D.Double getControlTwo() {		// getter to get the ControlPoint's coordinates
		return controlTwo;
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

		// Updates the QuadMarker based on the Curve's coordinates - to be called when a curve is modified
		public void updateQuadMarker() {
			this.markerEllipse.setFrame(controlOne.x - markerRadius, controlOne.y - markerRadius, markerRadius * 2,
					markerRadius * 2);
			this.lineMarkerToStart.setLine(controlOne.x, controlOne.y, startP.x, startP.y);
			this.lineMarkerToEnd.setLine(controlOne.x, controlOne.y, endP.x, endP.y);
		}

	}// End of QuadMarker

	// Inner class to have all info for CUBE markers - the two circles and the lines
	class CubeMarker {

		// The class should have access to the private fields of Curve to be able to draw the marker and the lines
		Color markerOneColor;
		Color markerTwoColor;
		Ellipse2D.Double markerOneEllipse;
		Ellipse2D.Double markerTwoEllipse;
		Line2D.Double lineMarkerToStart;
		Line2D.Double lineMarkerToEnd;

		// Constructor for CUBE marker
		CubeMarker(Color markerOneColor, Color markerTwoColor) {
			this.markerOneColor = markerOneColor;
			this.markerOneEllipse = new Ellipse2D.Double(controlOne.x - markerRadius, controlOne.y - markerRadius,
					markerRadius * 2, markerRadius * 2);
			this.markerTwoEllipse = new Ellipse2D.Double(controlTwo.x - markerRadius, controlTwo.y - markerRadius,
					markerRadius * 2, markerRadius * 2);
			this.lineMarkerToStart = new Line2D.Double(controlOne.x, controlOne.y, startP.x, startP.y);
			this.lineMarkerToEnd = new Line2D.Double(controlTwo.x, controlTwo.y, endP.x, endP.y);
		}

		// Updates the QuadMarker based on the Curve's coordinates - to be called when a curve is modified
		public void updateCubeMarker() {
			this.markerOneEllipse.setFrame(controlOne.x - markerRadius, controlOne.y - markerRadius, markerRadius * 2,
					markerRadius * 2);
			this.lineMarkerToStart.setLine(controlOne.x, controlOne.y, startP.x, startP.y);
			this.markerTwoEllipse.setFrame(controlTwo.x - markerRadius, controlTwo.y - markerRadius, markerRadius * 2,
					markerRadius * 2);
			this.lineMarkerToEnd.setLine(controlTwo.x, controlTwo.y, endP.x, endP.y);
		}

	}// End of CubeMarker

} // End of Curve class
