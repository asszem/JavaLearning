package GUI.FullDemos.CurveDrawer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import static GUI.FullDemos.CurveDrawer.CurveApp.QUAD;
import static GUI.FullDemos.CurveDrawer.CurveApp.CUBE;

@SuppressWarnings("serial")
public class CurveDrawerFrame extends JFrame {

	CurveApp appInstance;	 // A reference to the CurveApp object, which is the app instance
	CurveButton curveButton;

	CurveDrawerFrame(CurveApp appInstance) {
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
		// System.out.println("Drawing Panel size:"+drawingPanel.getSize().getWidth()+", "+drawingPanel.getSize().getHeight());
		setVisible(true);
		// Size will be determined after setVisible. If resized, call validate() or doLayout()
		validate();
		doLayout();
		double drawingPaneWidth = appInstance.getDrawingPane().getSize().getWidth();
		double drawingPaneHeight = appInstance.getDrawingPane().getSize().getHeight();
		System.out.println("DrawingPane size: " + drawingPaneWidth + ", " + drawingPaneHeight);

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
				// TODO generate random position considering the size of the panel
//				Point2D.Double startP = new Point2D.Double(100, 100);
//				Point2D.Double endP = new Point2D.Double(200, 200);
//				Point2D.Double controlP = new Point2D.Double(230, 140);
				ArrayList<Point2D.Double> newPositions = getRandomPosition();
				Point2D.Double startP = newPositions.get(0);
				Point2D.Double endP = newPositions.get(1);
				Point2D.Double controlP = newPositions.get(2);
				appInstance.createNewCurve(startP, endP, controlP, null);  //4th parameter must be null
				appInstance.getDrawingPane().repaint();
			}
			if (getValue(NAME).equals("Cubic")) {
				System.out.println("Cubic button pressed");
				ArrayList<Point2D.Double> newPositions = getRandomPosition();
				Point2D.Double startP = newPositions.get(0);
				Point2D.Double endP = newPositions.get(1);
				Point2D.Double controlOne = newPositions.get(2);
				Point2D.Double controlTwo = newPositions.get(3);
				appInstance.createNewCurve(startP, endP, controlOne, controlTwo);
				appInstance.getDrawingPane().repaint();
			}

		}

		// Generate random points and marker(s) for curves
		public ArrayList<Point2D.Double> getRandomPosition() {
			ArrayList<Point2D.Double> positions = new ArrayList<>();
			Point2D.Double startP;
			Point2D.Double endP;
			Point2D.Double controlOne;	// For QUAD and CUBE
			Point2D.Double controlTwo;	// For CUBE only
			Random rnd = new Random();
			appInstance.getDrawingPane().validate();		// To get the correct size before creating random object
			int drawingPaneWidth = (int) appInstance.getDrawingPane().getSize().getWidth();
			int drawingPaneHeight = (int) appInstance.getDrawingPane().getSize().getHeight();
			int offlimit = 100; // to make sure new Curve wont be drawed to the right-bottom part of drawing pane
//			System.out.println("DrawingPane size: " + drawingPaneWidth + ", " + drawingPaneHeight);

			startP = new Point2D.Double(rnd.nextInt(drawingPaneWidth) - offlimit,
					rnd.nextInt(drawingPaneHeight) - offlimit);

			int xSize = rnd.nextInt(drawingPaneWidth/2);	// horizontal size of new curve
			int ySize = rnd.nextInt(drawingPaneHeight)/2;	// vertical size of new curve
			endP = new Point2D.Double(startP.getX() + xSize, startP.getY() + ySize);
			controlOne = new Point2D.Double(startP.getX() + rnd.nextInt(xSize), startP.getY() + rnd.nextInt(ySize));
			controlTwo = new Point2D.Double(endP.getX() + rnd.nextInt(xSize), endP.getY() + rnd.nextInt(ySize));
			positions.add(startP);
			positions.add(endP);
			positions.add(controlOne);
			positions.add(controlTwo);
			System.out.println(positions);
			return positions;
		}
	}
}
