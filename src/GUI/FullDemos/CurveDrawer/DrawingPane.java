package GUI.FullDemos.CurveDrawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import static GUI.FullDemos.CurveDrawer.CurveApp.QUAD;
import static GUI.FullDemos.CurveDrawer.CurveApp.CUBE;

@SuppressWarnings("serial")
public class DrawingPane extends JComponent {

	private MouseHandler mouseHandler;			// The MouseHandler object for the whole drawing pane
	CurveApp appInstance; 					// A reference to the CurveApp object, which is the app instance

	// Constructor
	DrawingPane(CurveApp appInstance) {
		mouseHandler = new MouseHandler();
		this.appInstance = appInstance;
	}

	public void drawCurve(Graphics2D context, Curve curve) {
		// Update the Curve to get the latest coordinates before drawing
		if (curve.getCurveType() == QUAD) {
			context.draw(curve.getQuadCurve());						// Draw the curve itself
			context.setColor(curve.getQuadMarker().markerColor);
			context.draw(curve.getQuadMarker().markerEllipse); 		// Draw the marker
			// Draw the connecting lines
			context.draw(curve.getQuadMarker().lineMarkerToStart);
			context.draw(curve.getQuadMarker().lineMarkerToEnd);
			context.setColor(curve.getCurveColor());
		}
		if (curve.getCurveType() == CUBE) {
			context.draw(curve.getCubeCurve());						// Draw the curve itself
			// Draw the first marker
			context.setColor(curve.getCubeMarker().markerOneColor);
			context.draw(curve.getCubeMarker().markerOneEllipse); 	// Draw the first ellipse
			context.draw(curve.getCubeMarker().lineMarkerToStart);

			// Draw the second marker
			context.setColor(curve.getCubeMarker().markerTwoColor);
			context.draw(curve.getCubeMarker().markerTwoEllipse); 	// Draw the Second ellipse
			context.draw(curve.getCubeMarker().lineMarkerToEnd);
			context.setColor(curve.getCurveColor()); 				// Reset the original curve color
		}
	}

	@Override
	public void paint(Graphics g) {									// Do the actual drawing
		Graphics2D g2dcontext = (Graphics2D) g;
		// System.out.println("Paint method called");
		// System.out.println(appInstance.getCurves().size());
		// Draw all the curves that are stored in the appInstance
		for (Curve curve : appInstance.getCurves()) {
			drawCurve(g2dcontext, curve);
		}

	}// End of paint

	// Getter method for MouseHandler object of Drawing Pane
	public MouseHandler getMouseHandler() {
		return mouseHandler;
	}

	// Inner class for handling drawing pane's mouse events
	class MouseHandler extends MouseInputAdapter {

		Curve curveSelected; 	// Reference to store the current Curve object that is being selected
		int markerSelected;		// Which marker is selected for a CUBE curve

		@Override
		public void mousePressed(MouseEvent m) {
			// System.out.println("Mouse pressed");
			// Walk through the available curves and see if the pressed location is within the marker's location
			// System.out.println("Num. of curves:"+appInstance.getCurves().size());
			// System.out.println("Pressed Mouse coords: " + m.getX() + ", " + m.getY());
			for (Curve curve : appInstance.getCurves()) {
				if (curve.getCurveType() == QUAD) {
					System.out.print("Quad pressed\nMarker coords: " + curve.getQuadMarker().markerEllipse.getX());
					System.out.println(" , " + curve.getQuadMarker().markerEllipse.getY());
					if (curve.getQuadMarker().markerEllipse.contains(m.getX(), m.getY())) {
						System.out.println("Curve selected: " + curve.toString());
						curveSelected = curve;
					}
				}
				if (curve.getCurveType() == CUBE) {
					System.out.print("Cube pressed\nMarker coords: " + curve.getCubeMarker().markerOneEllipse.getX());
					System.out.println(" , " + curve.getCubeMarker().markerOneEllipse.getY());
					if (curve.getCubeMarker().markerOneEllipse.contains(m.getX(), m.getY())) {
						System.out.println("Marker One selected for curve: " + curve.toString());
						curveSelected = curve;
						markerSelected = 1;
					}
					if (curve.getCubeMarker().markerTwoEllipse.contains(m.getX(), m.getY())) {
						System.out.println("Marker Two selected for curve: " + curve.toString());
						curveSelected = curve;
						markerSelected = 2;
					}
				}
			}// end for walking through curves arraylist
		}// end mouse pressed

		@Override
		public void mouseDragged(MouseEvent m) {
			// System.out.println("Mouse is dragged");
			if (curveSelected != null) {
				if (curveSelected.getCurveType() == QUAD) {
					Point2D.Double controlOne = new Point2D.Double(m.getX(), m.getY());
					curveSelected.updateCurve(curveSelected.getStartP(), curveSelected.getEndP(), controlOne, null);
				}
				if (curveSelected.getCurveType() == CUBE) {
					Point2D.Double controlOne;
					Point2D.Double controlTwo;
					if (markerSelected == 1) {
						controlOne = new Point2D.Double(m.getX(), m.getY());
						controlTwo = curveSelected.getControlTwo();
						curveSelected.updateCurve(curveSelected.getStartP(), curveSelected.getEndP(), controlOne,
								controlTwo);
					}
					if (markerSelected == 2) {
						controlTwo = new Point2D.Double(m.getX(), m.getY());
						controlOne = curveSelected.getControlOne();	// remains the same
						curveSelected.updateCurve(curveSelected.getStartP(), curveSelected.getEndP(), controlOne,
								controlTwo);
					}
				}

				appInstance.getDrawingPane().repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent m) {
			if (curveSelected != null) {
				System.out.println("Mouse released");
				curveSelected = null;
			}
		}

		public void mouseMoved(MouseEvent m) {
			// System.out.println("Mouse coords: " + m.getX() + ", " + m.getY());
		}

	} // End of MouseHandler class
}// End of DrawingPane class
