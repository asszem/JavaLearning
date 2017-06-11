// Source: http://www.java2s.com/Tutorial/Java/0240__Swing/UsingGridBagConstraints.htm
package GUI.LayoutManagers.GridBagLayout;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridBagButtons {

	private static final Insets insets = new Insets(0, 0, 0, 0);

	public static void main(final String args[]) {
		final JFrame frame = new JFrame("GridBagLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		JButton button;
		// Row One - Three Buttons
		button = new JButton("x0y0");
		//			 Container Component pos size		anchor						fill
		addComponent(frame, button, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		button = new JButton("x1y0");
		addComponent(frame, button, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		button = new JButton("x2y0");
		addComponent(frame, button, 2, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		// Row Two - Two Buttons
		button = new JButton("x0y1");
		addComponent(frame, button, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		button = new JButton("x2y1");
		addComponent(frame, button, 2, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		// Row Three - Two Buttons
		button = new JButton("x0y2");
		addComponent(frame, button, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		button = new JButton("x1y2");
		addComponent(frame, button, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		frame.setSize(500, 200);
		frame.setVisible(true);
	}

	private static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0, anchor, fill,
				insets, 0, 0);
		container.add(component, gbc);
	}
}
