package GUI.Practice;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

public class ContentPlane_Sample {
	public static void main(String[] args) {
		SimpleJFrame jFrameWindow = new SimpleJFrame();
		SwingUtilities.invokeLater(jFrameWindow);
		Dimension jFrameWindowDimension = jFrameWindow.simpleJFrame.getSize();
		System.out.println(jFrameWindowDimension.height);
	}

}
