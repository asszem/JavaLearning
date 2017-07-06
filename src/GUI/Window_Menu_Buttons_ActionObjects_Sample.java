package GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import static java.awt.event.InputEvent.*;

public class Window_Menu_Buttons_ActionObjects_Sample {

	private JFrame jFrame;
	private JMenuBar jMenuBar;
	private JMenu fileMenu;
	private JMenu fileSubMenu;
	private FileMenuActionObject openFile, recentFile, quitProgram;
	private ButtonActionObject button1;
	private ButtonActionObject button2;

	public void createGUI() {
		jFrame = createJFrame();
		setJMenuBar();
		createActionObjects(); 		// Could be called from setFileMenuItems as well
		setFileMenu();
		setFileMenuItems(); 		// Could be called from setFileMenu as well
		setButtons();
		setActionListeners(); 		// For Windows events only
		jFrame.setVisible(true);
	}

	public JFrame createJFrame() {
		jFrame = new JFrame("Window with Action Objects Sample");
		Toolkit theKit = jFrame.getToolkit();                             // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       // Get screen size
		jFrame.setSize(wndSize.width / 2, wndSize.height / 2);   	      // Set window size
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return jFrame;
	}

	public void setJMenuBar() {
		jMenuBar = new JMenuBar();
		jFrame.setJMenuBar(jMenuBar);
	}

	public void setFileMenu() {
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		fileSubMenu = new JMenu("Recent...");
		fileSubMenu.setMnemonic('R');
		fileMenu.add(fileSubMenu);
		jMenuBar.add(fileMenu);
		// setFileMenuItems();
	}

	public void createActionObjects() {
		openFile = new FileMenuActionObject("Open", 'O', CTRL_DOWN_MASK);
		recentFile = new FileMenuActionObject("Recent", 'R', CTRL_DOWN_MASK);
		quitProgram = new FileMenuActionObject("Quit", 'Q', CTRL_DOWN_MASK);
		button1 = new ButtonActionObject("Button 1");
		button2 = new ButtonActionObject("Button 2");
	}

	public void setFileMenuItems() {
		// action objects should be initialized at this point.
		// createActionObjects();
		fileMenu.add(openFile);
		fileSubMenu.add(recentFile);
		fileMenu.addSeparator();
		fileMenu.add(quitProgram);
	}

	public void setButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton(button1));
		buttonPanel.add(new JButton(button2));
		Container content = jFrame.getContentPane();
		content.add(buttonPanel);
	}

	public void setActionListeners() {
		jFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Window is closing");
				jFrame.dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Window closed");
				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
	}

	@SuppressWarnings("serial")
	class FileMenuActionObject extends AbstractAction {

		// Constructor to create object with name - Which is to IDENTIFY the object
		public FileMenuActionObject(String objectName) {
			super(objectName);
		}

		// Constructor to Create action with a name and accelerator key
		public FileMenuActionObject(String name, char ch, int modifiers) {
			super(name);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(ch, modifiers)); // Set property by key:value

			// Now find the character to underline
			int index = name.toUpperCase().indexOf(ch);
			if (index != -1) {
				putValue(DISPLAYED_MNEMONIC_INDEX_KEY, index); // Set property by key:value
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (getValue(NAME).equals("Open")) {
				System.out.println("Open file pressed");
			}
			if (getValue(NAME).equals("Recent")) {
				System.out.println("Recent files pressed");
			}
			if (getValue(NAME).equals("Quit")) {
				System.out.println("Quiting...");
				System.exit(0);
			}
		}

	}

	@SuppressWarnings("serial")
	class ButtonActionObject extends AbstractAction {

		public ButtonActionObject(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (getValue(NAME).equals("Button 1")) {
				System.out.println("Button 1 pressed.");
			}
			if (getValue(NAME).equals("Button 2")) {
				System.out.println("Button 2 pressed.");
			}
		}
	}

	public static void main(String[] args) {
		Window_Menu_Buttons_ActionObjects_Sample theAppInstance = new Window_Menu_Buttons_ActionObjects_Sample();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				theAppInstance.createGUI();
			}
		});
	}// End main
}
