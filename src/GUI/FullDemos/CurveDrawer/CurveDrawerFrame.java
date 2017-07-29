package GUI.FullDemos.CurveDrawer;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class CurveDrawerFrame extends JFrame {

	CurveDrawer appInstance;

	CurveDrawerFrame(CurveDrawer appInstance) {
		this.appInstance = appInstance;
		setTitle("Curve with dragabble markers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1000, 800);
		Container contentPane = getContentPane();
		contentPane.add(appInstance.getDrawingPane(), BorderLayout.CENTER);
		contentPane.addMouseListener(appInstance.getDrawingPane().getMouseHandler());
		contentPane.addMouseMotionListener(appInstance.getDrawingPane().getMouseHandler());		//This is required to listen to drag and drop!
		setVisible(true);
	}
}
