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
	
	public MouseHandler getMouseHandler(){
		return mouseHandler;
	}

	// Constructor
	DrawingPane(CurveDrawer appInstance) {
		mouseHandler = new MouseHandler();
		this.appInstance=appInstance;
	}

	class MouseHandler extends MouseInputAdapter {

		@Override
		public void mousePressed(MouseEvent m) {
			// walk through the available curves and see if the pressed location is within the marker's location
			for (Curve curve : appInstance.getCurves()) {
			}
		}
	} // End of MouseHandler class

	public void drawCurve(Graphics2D context, Curve curve) {
		context.draw(curve.getQuadCurve());						// Draw the curve itself
//		context.setColor(curve.getQuadMarker().markerColor);
//		context.draw(curve.getQuadMarker().markerEllipse);		// Draw the marker
//						     						// Draw the connecting lines
//		context.draw(curve.getQuadMarker().lineMarkerToStart);
//		context.draw(curve.getQuadMarker().lineMarkerToEnd);
//		context.setColor(curve.getCurveColor());
	}

	@Override // overrides JComponent paint method
	public void paint(Graphics g) { // this will get the graphics
		Graphics2D g2dcontext = (Graphics2D) g; // casts the graphics to Graphics2D type. This will be the graphics context to draw upon
		// Draw all curves 
			System.out.println("curve drwa");
		for (Curve curve : appInstance.getCurves()) {
			drawCurve(g2dcontext, curve);
		}

	}// End of paint

}// End of DrawingPane class
