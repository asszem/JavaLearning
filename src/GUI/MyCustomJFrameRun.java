package GUI;

import javax.swing.SwingUtilities;

public class MyCustomJFrameRun {

	private static MyCustomJFrame mainJFrame;

	private static void createWindow(String title) {
		mainJFrame = new MyCustomJFrame(title);
		mainJFrame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createWindow("Test");
			}
		});
	}

}
