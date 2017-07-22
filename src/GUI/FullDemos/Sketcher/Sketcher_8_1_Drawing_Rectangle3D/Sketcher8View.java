//The ScetcherView instance is added to the window content pane in Sketcher8 app
package GUI.FullDemos.Sketcher.Sketcher_8_1_Drawing_Rectangle3D;

import javax.swing.JComponent;
import java.util.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class Sketcher8View extends JComponent implements Observer {

	private Sketcher8 theApp; // The application object

	public Sketcher8View(Sketcher8 theApp) {
		this.theApp = theApp;
	}

	// Method called by Observable object when it changes
	public void update(Observable o, Object rectangle) {
		// Code to respond to changes in the model...
	}

	// Method to draw on the view
	@Override // overrides javax.swing.JComponent.paint method
	// Applications should not invoke paint directly,
	// but should instead use the repaint method to schedule the component for redrawing.
	public void paint(Graphics g) {
		// The graphics context is passed as the argument to the paint() method as type Graphics
		// Graphics is the base class for Graphics2D
		// so to use the methods defined in the Graphics2D class you must first CAST it to that type.
		// methods: draw3Drect, drawString, etc

		// Temporary code...
		Graphics2D g2D = (Graphics2D) g; // Get a Java 2D DEVICE CONTEXT

		g2D.setPaint(Color.RED); // Draw in red - all subsequent drawings are in red
		g2D.draw3DRect(50, 50, 150, 100, true); // Draw a raised 3D rectangle
		g2D.drawString("A nice 3D rectangle", 60, 100); // Draw some text
		g2D.setPaint(Color.BLACK); // Change the color to Blue
		g2D.drawLine(10, 10, 100, 100);
		
		//Draw a line
		// Define a line object directly providing 4 coordinates as type float
		Line2D.Float line2DFloat = new Line2D.Float(600.0f, 650.0f, 200.0f, 200.0f);
		
		// Define two Point objects
		Point2D.Float p1Float = new Point2D.Float(300.0f, 300.0f);
		Point2D.Float p2Float= new Point2D.Float(500.0f, 500.0f);
		
		// Create a line object based on two point objects
		Line2D.Float line2DFromPoints = new Line2D.Float(p1Float, p2Float);
		
		// Draw the lines based on the Line2D objets
		g2D.draw(line2DFloat);
		g2D.draw(line2DFromPoints);
		
		// Create rectangle
		float width=300.0f;
		float height=300.0f;
		Rectangle2D.Float rectangle=new Rectangle2D.Float(400.0f, 300.0f, width, height);
		g2D.draw(rectangle);
	}

}
