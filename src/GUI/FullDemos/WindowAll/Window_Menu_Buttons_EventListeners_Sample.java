package GUI.FullDemos.WindowAll;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import static java.awt.event.InputEvent.*;

import java.awt.Container;

public class Window_Menu_Buttons_EventListeners_Sample {

	private static JFrame jFrame;
	private static JMenuBar jMenuBar;
	private static JMenu fileMenu;
	private static JMenu fileSubMenu;
	private static JMenuItem fileMenuItemOpenFile;
	private static JMenuItem fileMenuItemQuit;
	private static JMenuItem fileSubMenuItemRecent;
	private static JButton button1;

	public static void createGUI() {
		jFrame = createJFrame();
		setJMenuBar();
		setFileMenu();
		setButtons();
		setActionListeners();
		jFrame.setVisible(true);
	}

	public static JFrame createJFrame() {
		jFrame = new JFrame("Window with Event Listener Sample");
		Toolkit theKit = jFrame.getToolkit();                             // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       // Get screen size
		jFrame.setSize(wndSize.width / 2, wndSize.height / 2);   	      // Set window size
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return jFrame;
	}

	public static void setJMenuBar() {
		jMenuBar = new JMenuBar();
		jFrame.setJMenuBar(jMenuBar);
	}

	public static void setFileMenu() {
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		fileSubMenu = new JMenu("Recent...");
		fileSubMenu.setMnemonic('R');
		fileMenu.add(fileSubMenu);
		jMenuBar.add(fileMenu);
		setFileMenuItems();
	}

	public static void setFileMenuItems() {
		fileMenuItemOpenFile = new JMenuItem("Open File");
		fileMenuItemOpenFile.setAccelerator(KeyStroke.getKeyStroke('F', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));
		fileMenu.add(fileMenuItemOpenFile);

		fileMenuItemQuit = new JMenuItem("Quit");
		fileMenuItemQuit.setAccelerator(KeyStroke.getKeyStroke('Q', ALT_DOWN_MASK));
		fileMenu.add(fileMenuItemQuit);

		fileSubMenuItemRecent = new JMenuItem("Recent");
		fileSubMenuItemRecent.setAccelerator(KeyStroke.getKeyStroke('R', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));
		fileSubMenu.add(fileSubMenuItemRecent);
	}

	public static void setButtons() {
		JPanel buttonPanel = new JPanel();
		button1 = new JButton("Button 1");
		buttonPanel.add(button1);

		Container content = jFrame.getContentPane();
		content.add(buttonPanel);
	}

	public static void setActionListeners() {
		InnerActionListeners actionListeners = new InnerActionListeners();
		fileMenuItemOpenFile.addActionListener(actionListeners);
		fileMenuItemQuit.addActionListener(actionListeners);
		fileSubMenuItemRecent.addActionListener(actionListeners);
		button1.addActionListener(actionListeners);

		jFrame.addWindowListener(new InnerWindowListeners());
	}

	static class InnerActionListeners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object eventSource = e.getSource();
			if (eventSource == fileMenuItemQuit) {
				System.out.println("Quiting..");
				System.exit(0);
			}
			if (eventSource == fileMenuItemOpenFile) {
				System.out.println("Open file pressed...");
			}
			if (eventSource == fileSubMenuItemRecent) {
				System.out.println("Recent pressed...");
			}
			if (eventSource == button1) {
				System.out.println("Button 1 pressed...");
			}

		}

	}

	static class InnerWindowListeners implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			System.out.println("Window is opened");
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Window is closing");
			jFrame.dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) {
			System.out.println("Window is closed");
			System.exit(0);
		}

		@Override
		public void windowIconified(WindowEvent e) {
			System.out.println("Window is iconified");
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			System.out.println("Window is deiconified");
		}

		@Override
		public void windowActivated(WindowEvent e) {
			System.out.println("Window Activated");
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			System.out.println("Window Deactivated");
		}

	}

	static class InnerMouseMotions extends MouseAdapter {
		//Implement mouse events 
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createGUI();
			}
		});
	}//End main
}//end class
