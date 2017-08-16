package GUI.FullDemos.CurveDrawer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class CurveApp {

	public static final int QUAD = 0; 			// Curve type constant
	public static final int CUBE = 1;				// Curve type constant
	private static CurveDrawerFrame window;
	private ArrayList<Curve> curves;
	private DrawingPane drawingPane;

	// Constructor to create a new app instance
	CurveApp() {
		curves = new ArrayList<>();
		drawingPane = new DrawingPane(this); // passing the reference of this CurveApp object to the DrawingPane
		window = new CurveDrawerFrame(this); // passing the reference of this CurverApp object to the CurveDrawerFrame

		// Point2D.Double startP = new Point2D.Double(100, 100);
		// Point2D.Double endP = new Point2D.Double(200, 200);
		// Point2D.Double controlP = new Point2D.Double(230, 140);
		// createNewCurve(startP, endP, controlP);

		drawingPane.repaint();
	}

	/**
	 * Creates QUAD curve if 4th parameter is null Creates CUBE curve if 4th parameter is not null
	 */
	public Curve createNewCurve(Point2D.Double startP, Point2D.Double endP, Point2D.Double controlOne,
			Point2D.Double controlTwo) {
		if (controlTwo == null) {
			Curve newCurve = Curve.createCurve(this, startP, endP, controlOne);
			return newCurve;
		} else {
			Curve newCurve = Curve.createCurve(this, startP, endP, controlOne, controlTwo);
			return newCurve;
		}
	}

	public ArrayList<Curve> getCurves() {
		return curves;
	}

	public DrawingPane getDrawingPane() {
		return drawingPane;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				CurveApp theAppInstance = new CurveApp();
			}
		});
	}// end main

}// end of CurveApp class
