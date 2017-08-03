package GUI.FullDemos.CurveDrawer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

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

		// Create a button Panel (which is a Component and also a Container)
		JPanel buttonPanel = new JPanel();
		TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "Button Panel");
		buttonPanel.setBorder(titledBorder);
		buttonPanel.add(new JButton(new CurveButton("Create Quadratic Curve Button")));
		buttonPanel.add(new JButton(new CurveButton("Create Cubic Curve Button")));
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		// The MouseListener needs to be added to a separate drawingPane container, not to the full content Pane
		// Otherwise the mouse coordinates will be including area outside of drawing pane
		Container drawingPaneContainer = appInstance.getDrawingPane();
		drawingPaneContainer.addMouseMotionListener(appInstance.getDrawingPane().getMouseHandler());		// This is required to listen to drag and drop!
		drawingPaneContainer.addMouseListener(appInstance.getDrawingPane().getMouseHandler());

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
			// TODO Auto-generated method stub
			System.out.println("Button is pressed");

		}

	}
}
