package GUI.Applets.StarApplet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StarApplet extends JApplet {

	// Initialize the applet
	@Override
	public void init() {
		BorderLayout border = new BorderLayout(10, 10);                    // hGap, vGap
		getContentPane().setLayout(border);

		StarPane pane = new StarPane(); // Pane containing stars
		// JPanel starsPanel = new JPanel();
		// getContentPane().add(starsPanel, BorderLayout.CENTER);
		// starsPanel.add(pane);

		JPanel buttonPanel = new JPanel();
		JButton jbutton = new JButton("Disco!");
		jbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button pressed");
				pane.repaint();
			}
		});
		buttonPanel.add(jbutton);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
		getContentPane().add(pane, BorderLayout.CENTER);
	}

	// Class defining a pane on which to draw
	class StarPane extends JComponent {

		@Override
		public void paint(Graphics g) {
			Graphics2D g2D = (Graphics2D) g;
			float delta = 60f;                                               // Increment between stars
			float starty = 0f;                                               // Starting y position
			int rows=10;
			int columns=10;

			// Draw rows of stars
			for (int yCount = 0; yCount < rows; yCount++) {
				starty += delta;                                               // Increment row position
				float startx = 0f;                                             // Start x position in a row

				GeneralPath currentStar;

				// Draw columns of stars
				for (int xCount = 0; xCount < columns; xCount++) {
					currentStar = Star.starAt(startx += delta, starty);
					g2D.setPaint(randomColor());
					g2D.fill(currentStar);
					g2D.draw(currentStar);
				}
			}
		}
	}

	public Color randomColor() {
		Random rnd = new Random();
		Color color = Color.BLACK;
		switch (rnd.nextInt(5)) {
		case 1:
			color = Color.RED;
			break;
		case 2:
			color = Color.BLUE;
			break;
		case 3:
			color = Color.GREEN;
			break;
		case 4:
			color = Color.YELLOW;
			break;
		case 5:
			color = Color.CYAN;
			break;
		default:
			color = Color.MAGENTA;
		}

		return color;
	}
}
