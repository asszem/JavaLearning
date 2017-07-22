package GUI.JSkeletons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DrawInJComponent_Skeleton {

	JFrame window;

	// Method to setup the JFrame
	public void setupJFrame() {
		window = new JFrame("Draw in JComponent Skeleton");
		window.setBounds(50, 50, 600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent viewJComponent = this.createJComponent(); // Create the JComponent on the AppInstance
		window.getContentPane().add(viewJComponent, BorderLayout.CENTER); // Add the jComponent to the CENTER
		window.setVisible(true);
	}

	// Inner class implementing JComponent's paint() and Observer's update() methods
	@SuppressWarnings("serial")
	class innerJComponentView extends JComponent implements Observer {

		@Override
		public void paint(Graphics g) {
			Graphics2D g2DContext = (Graphics2D) g;
			// draw on g2DContext
			g2DContext.setColor(Color.RED);
			g2DContext.drawString("Böbe", 100, 100);
			// ..
		}

		@Override
		public void update(Observable o, Object arg) {
			// Actions to take when the Observable object changes
		}
	}

	// Factory method to instantiate a JComponent object
	public JComponent createJComponent() {
		return new innerJComponentView();
	}

	// Main method to start Event Dispatcher thread and call createJFrame method
	public static void main(String[] args) {
		DrawInJComponent_Skeleton theAppInstance = new DrawInJComponent_Skeleton();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				theAppInstance.setupJFrame();
			}
		});
	}
}
