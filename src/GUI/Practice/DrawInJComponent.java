package GUI.Practice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DrawInJComponent {

	JFrame window;

	public void createJFrame() {
		window = new JFrame("Draw in JComponent Sample or Skeleton");
		window.setBounds(50, 50, 600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent drawJComponent=this.createJComponent(); //Create the JComponent on the AppInstance
		window.getContentPane().add(drawJComponent, BorderLayout.CENTER); //Add the jComponent to the CENTER
		window.setVisible(true);
	}

	// An Inner class that extends to the Abstrac JComponent class
	@SuppressWarnings("serial")
	class innerJComponent extends JComponent {

		@Override
		public void paint(Graphics g) {
			Graphics2D g2DContext=(Graphics2D) g;
			g2DContext.setColor(Color.RED);
			g2DContext.drawString("This is the well", 100, 100);
		}
	}

	// Method to implement the JComponent
	public JComponent createJComponent() {
		return new innerJComponent();
	}

	public static void main(String[] args) {
		DrawInJComponent theAppInstance = new DrawInJComponent();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				theAppInstance.createJFrame();
			}
		});
	}
}
