package GUI.FullDemos.CurveDrawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

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
		context.draw(curve.getQuadCurve());						// Draw the curve itself
		context.setColor(curve.getQuadMarker().markerColor);
		context.draw(curve.getQuadMarker().markerEllipse); // Draw the marker
		// TODO if curve type is Cube, draw the second marker ellipse
		// Draw the connecting lines
		context.draw(curve.getQuadMarker().lineMarkerToStart);
		context.draw(curve.getQuadMarker().lineMarkerToEnd);
		// TODO if curve type is CUBE, draw the ControlTwo markers
		context.setColor(curve.getCurveColor());
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2dcontext = (Graphics2D) g;
		System.out.println("Paint method called");
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

		Curve curveSelected; // Reference to store the current Curve object that is being selected

		@Override
		public void mousePressed(MouseEvent m) {
			System.out.println("Mouse pressed");
			// Walk through the available curves and see if the pressed location is within the marker's location
			// System.out.println("Num. of curves:"+appInstance.getCurves().size());
			System.out.println("Pressed Mouse coords: " + m.getX() + ", " + m.getY());
			for (Curve curve : appInstance.getCurves()) {
				System.out.print("Marker coords: " + curve.getQuadMarker().markerEllipse.getX());
				System.out.println(" , " + curve.getQuadMarker().markerEllipse.getY());
				if (curve.getQuadMarker().markerEllipse.contains(m.getX(), m.getY())) {
					System.out.println("Curve selected: " + curve.toString());
					curveSelected = curve;
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent m) {
//			System.out.println("Mouse is dragged");
			if (curveSelected != null) {
				Point2D.Double controlP = new Point2D.Double(m.getX(), m.getY());
				curveSelected.updateCurve(curveSelected.getStartP(), curveSelected.getEndP(), controlP);
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
//			System.out.println("Mouse coords: " + m.getX() + ", " + m.getY());
		}

	} // End of MouseHandler class
}// End of DrawingPane class
