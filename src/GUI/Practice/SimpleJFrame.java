package GUI.Practice;

import javax.swing.JFrame;

public class SimpleJFrame implements Runnable {

	String windowTitle="Default Widnow title";
	int width=400;
	int height=200;
	int posX=1000;
	int posY=100;
	JFrame simpleJFrame;
	
	public SimpleJFrame(){
		simpleJFrame=new JFrame(windowTitle);
	}

	public static JFrame createWindow(String windowTitle, int width, int height, int posX, int posY) {
		JFrame aWindow = new JFrame(windowTitle);
		aWindow.setBounds(posX, posY, width, height);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.setVisible(true); // Display the window
		return aWindow;
	}

	public void run() {
		createWindow(windowTitle, width, height, posX, posY);
	}

}
