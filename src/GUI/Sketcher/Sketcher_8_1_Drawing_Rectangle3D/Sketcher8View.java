package GUI.Sketcher.Sketcher_8_1_Drawing_Rectangle3D;

import javax.swing.JComponent;
import java.util.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Sketcher8View extends JComponent implements Observer {

	private Sketcher8 theApp;                                     // The application object

	public Sketcher8View(Sketcher8 theApp) {
		this.theApp = theApp;
	}

	// Method called by Observable object when it changes
	public void update(Observable o, Object rectangle) {
		// Code to respond to changes in the model...
	}

	// Method to draw on the view
	@Override  // overrides javax.swing.JComponent.paint method
	public void paint(Graphics g) {
		//The graphics context is passed as the argument to the paint() method as type Graphics
		//Graphics is the base class for Graphics2D
		//so to use the methods defined in the Graphics2D class you must first CAST it to that type.

		// Temporary code...
		Graphics2D g2D = (Graphics2D) g;        // Get a Java 2D DEVICE CONTEXT

		g2D.setPaint(Color.RED);                        // Draw in red - all subsequent drawings are in red
		g2D.draw3DRect(50, 50, 150, 100, true);                   // Draw a raised 3D rectangle
		g2D.drawString("A nice 3D rectangle", 60, 100);           // Draw some text
		g2D.setPaint(Color.BLACK);                                 // Change the color to Blue
		g2D.drawLine(10, 10, 100, 100);
	}

}
