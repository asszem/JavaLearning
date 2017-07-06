package GUI;
//Create frame

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import static java.awt.event.InputEvent.*;

//Add menu
//Add button
//Add action object to Frame, Menu, Button

import javax.swing.SwingUtilities;

public class ActionObjects_Sample {

	private static JFrame jFrame;
	private static JMenuBar jMenuBar;
	private static JMenu fileMenu;
	private static JMenuItem fileMenuItemOpenFile;
	private static JMenuItem fileMenuItemQuit;

	public static void createGUI() {
		jFrame = createJFrame();
		setJMenuBar();
		setFileMenu();
		jFrame.setVisible(true);
	}

	public static void setJMenuBar() {
		jMenuBar = new JMenuBar();
		jFrame.setJMenuBar(jMenuBar);
	}
	public static void setFileMenu(){
		fileMenu=new JMenu("File");
		fileMenu.setMnemonic('F');
		jMenuBar.add(fileMenu);
		setFileMenuItems();
		
	}
	public static void setFileMenuItems(){
		fileMenuItemOpenFile = new JMenuItem("Open File");
		fileMenuItemOpenFile.setAccelerator(KeyStroke.getKeyStroke('F', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));
		fileMenu.add(fileMenuItemOpenFile);
		
		fileMenuItemQuit = new JMenuItem("Quit");
		fileMenuItemQuit.setAccelerator(KeyStroke.getKeyStroke('Q', ALT_DOWN_MASK));
		fileMenu.add(fileMenuItemQuit);
	}

	public static JFrame createJFrame() {
		jFrame = new JFrame("Action Objects Sample");
		Toolkit theKit = jFrame.getToolkit();                             // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       // Get screen size
		jFrame.setSize(wndSize.width / 2, wndSize.height / 2);   	      // Set window size
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return jFrame;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createGUI();
			}
		});
	}

	//Inner class for FileMenu Actions
	class FileMenuActions extends AbstractAction{

		//Constructor to create object with name
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
