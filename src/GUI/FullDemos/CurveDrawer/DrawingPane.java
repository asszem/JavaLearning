package GUI.FullDemos.CurveDrawer;

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
import javax.swing.event.MouseInputAdapter;

@SuppressWarnings("serial")
public class DrawingPane extends JComponent {

	private MouseHandler mouseHandler;			// The MouseHandler object for the whole drawing pane
	CurveDrawer appInstance;

	public MouseHandler getMouseHandler() {
		return mouseHandler;
	}

	// Constructor
	DrawingPane(CurveDrawer appInstance) {
		mouseHandler = new MouseHandler();
		this.appInstance = appInstance;
	}

	class MouseHandler extends MouseInputAdapter {

		Curve curveToUpdate;

		@Override
		public void mousePressed(MouseEvent m) {
			System.out.println("Mouse pressed");
			// Walk through the available curves and see if the pressed location is within the marker's location
			for (Curve curve : appInstance.getCurves()) {
				if (curve.getQuadMarker().markerEllipse.contains(m.getX(), m.getY())) {
					System.out.println("Curve selected: " + curve.toString());
					curveToUpdate = curve;
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent m) {
			System.out.println("Mouse is dragged");
			if (curveToUpdate != null) {
				Point2D.Double controlP = new Point2D.Double(m.getX(), m.getY());
				curveToUpdate.updateCurve(curveToUpdate.getStartP(), curveToUpdate.getEndP(), controlP);
				appInstance.getDrawingPane().repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent m) {
			if (curveToUpdate != null) {
				System.out.println("Mouse released");
				curveToUpdate = null;
			}
		}

	} // End of MouseHandler class

	public void drawCurve(Graphics2D context, Curve curve) {
		// Update the Curve to get the latest coordinates before drawing
		context.draw(curve.getQuadCurve());						// Draw the curve itself
		context.setColor(curve.getQuadMarker().markerColor);
		context.draw(curve.getQuadMarker().markerEllipse); // Draw the marker
		// Draw the connecting lines
		context.draw(curve.getQuadMarker().lineMarkerToStart);
		context.draw(curve.getQuadMarker().lineMarkerToEnd);
		context.setColor(curve.getCurveColor());
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2dcontext = (Graphics2D) g;
		System.out.println("Paint method called");
		// System.out.println(appInstance.getCurves().size());
		for (Curve curve : appInstance.getCurves()) {
			drawCurve(g2dcontext, curve);
		}

	}// End of paint

}// End of DrawingPane class
