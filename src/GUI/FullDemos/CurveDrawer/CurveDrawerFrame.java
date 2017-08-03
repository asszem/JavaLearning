package GUI.FullDemos.CurveDrawer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CurveDrawerFrame extends JFrame {

	CurveDrawer appInstance;	 // A reference to the CurveDrawer object, which is the app instance
	CurveButton curveButton;

	CurveDrawerFrame(CurveDrawer appInstance) {
		this.appInstance = appInstance;
		setTitle("Curve with dragabble markers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1000, 800);
		Container contentPane = getContentPane();

		// Create a JPanel for the buttons
		JPanel buttonPanel = new JPanel();
		TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "Button Panel");
		buttonPanel.setBorder(titledBorder);

		// Create the buttons with different button name and button label text
		JButton quadButton = new JButton(new CurveButton("Quad")); // "Quad" is to identify the action object
		JButton cubeButton = new JButton(new CurveButton("Cubic"));
		quadButton.setText("Add Quad Curve");
		cubeButton.setText("Add Cubic Curve");
		buttonPanel.add(quadButton);
		buttonPanel.add(cubeButton);
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		// Create a Container for the DrawingPane
		Container drawingPaneContainer = appInstance.getDrawingPane();
		// The MouseListener needs to be added to a separate drawingPane container, not to the full content Pane
		// Otherwise the mouse coordinates will be including area outside of drawing pane
		drawingPaneContainer.addMouseMotionListener(appInstance.getDrawingPane().getMouseHandler());		// This is required to listen to drag and drop!
		drawingPaneContainer.addMouseListener(appInstance.getDrawingPane().getMouseHandler());

		// Create a JPanel for the drawing Pane
		JPanel drawingPanel = new JPanel();
		drawingPanel.setLayout(new BorderLayout());
		drawingPanel.add(drawingPaneContainer, BorderLayout.CENTER);
		TitledBorder drawingBorder = new TitledBorder(new EtchedBorder(), "Drawing Panel");
		drawingPanel.setBorder(drawingBorder);

		contentPane.add(drawingPanel, BorderLayout.CENTER);

		setVisible(true);

		// NOTE Do not add the MouseListener directly to the content Pane!
		// contentPane.add(appInstance.getDrawingPane());
		// contentPane.addMouseMotionListener(appInstance.getDrawingPane().getMouseHandler()); // This is required to listen to drag and drop!
		// contentPane.addMouseListener(appInstance.getDrawingPane().getMouseHandler());
	}

	class CurveButton extends AbstractAction {

		public CurveButton(String objectName) {
			super(objectName);

			// If setting the size here means setting the size of the COMPONENT!
			// Dimension preferredSize = new Dimension(100, 200);
			// setPreferredSize(preferredSize);
			// setSize(preferredSize);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Button is pressed");
			if (getValue(NAME).equals("Quad")) {
				System.out.println("Quad button pressed");
				Point2D.Double startP = new Point2D.Double(100, 100);
				Point2D.Double endP = new Point2D.Double(200, 200);
				Point2D.Double controlP = new Point2D.Double(230, 140);
				appInstance.createNewCurve(startP, endP, controlP);
				appInstance.getDrawingPane().repaint();
			}
			if (getValue(NAME).equals("Cubic")) {
				System.out.println("Cubic button pressed");
			}

		}

	}
}
