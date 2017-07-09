package GUI;

//To import accelerator key masks
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
//To import Action object property keys
import static javax.swing.Action.LARGE_ICON_KEY;
import static javax.swing.Action.SMALL_ICON;
import static javax.swing.Action.NAME;
import static javax.swing.Action.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Window_Menu_Buttons_ActionObjects_Sample {

	private JFrame jFrame;
	private JMenuBar jMenuBar;
	private JMenu fileMenu;
	private JMenu fileSubMenu;
	private FileMenuActionObject openFile, saveFile, quitProgram;
	private ButtonActionObject button1;
	private ButtonActionObject button2;
	private JToolBar toolBar;

	// Toolbar icons
	public final static String imagePath = "J:/Horton Assets/Sketcher/";
	public final static Icon OPEN24 = new ImageIcon(imagePath + "Open24.gif");
	public final static Icon QUITPROGRAM = new ImageIcon("J:/virag24.jpg");
	public final static Icon SAVE24 = new ImageIcon(imagePath + "Save24.gif");
	public final static Icon OPEN16 = new ImageIcon(imagePath + "Open16.gif");
	public final static Icon SAVE16 = new ImageIcon(imagePath + "Save16.gif");

	public void createGUI() {
		jFrame = createJFrame();
		setJMenuBar();
		createActionObjects(); 		// Could be called from setFileMenuItems as well
		setTooltipsForActionObjects();
		setIconsForActionObjects();
		setFileMenu();
		setFileMenuItems(); 		// Could be called from setFileMenu as well
		setButtons();
		setActionListeners(); 		// For Windows events only
		setToolBar();
		addToolBarItems();
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
		fileSubMenu = new JMenu("Save submenu...");
		fileSubMenu.setMnemonic('R');
		// fileMenu.add(fileSubMenu); //Submenu must be added with FileMenu items to set correct order
		jMenuBar.add(fileMenu);
		// setFileMenuItems();
	}

	public void createActionObjects() {
		// Action objects will create JButton or JMenuItem or JCheckbox objects based on the method they are used in
		openFile = new FileMenuActionObject("Open", 'O', CTRL_DOWN_MASK);
		saveFile = new FileMenuActionObject("Save", 'R', CTRL_DOWN_MASK);
		quitProgram = new FileMenuActionObject("Quit", 'Q', CTRL_DOWN_MASK);
		button1 = new ButtonActionObject("Button 1");
		button2 = new ButtonActionObject("Button 2");
		
		saveFile.setEnabled(false);
	}

	public void setIconsForActionObjects() {
		openFile.putValue(LARGE_ICON_KEY, OPEN24);
		openFile.putValue(SMALL_ICON, OPEN16);
		saveFile.putValue(LARGE_ICON_KEY, SAVE24);
		saveFile.putValue(SMALL_ICON, SAVE16);
		quitProgram.putValue(LARGE_ICON_KEY, QUITPROGRAM);
		quitProgram.putValue(SMALL_ICON, QUITPROGRAM);
		// use SMALL_ICON property to set icon for JMenu
	}

	public void setTooltipsForActionObjects() {
		openFile.putValue(SHORT_DESCRIPTION, "Open file");
		saveFile.putValue(SHORT_DESCRIPTION, "Save file");
		quitProgram.putValue(SHORT_DESCRIPTION, "Quit program");
	}

	public void setFileMenuItems() {
		// action objects should be initialized at this point.
		// createActionObjects();
		fileMenu.add(openFile);
		fileMenu.add(fileSubMenu);
		fileSubMenu.add(saveFile);
		fileMenu.addSeparator();
		fileMenu.add(quitProgram);
	}

	public void setButtons() {

		// Add buttons created from ButtonAction objects
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton(button1)); // JButton objects are created from Action objects
		buttonPanel.add(new JButton(button2));

		// Add buttons created from FileMenuActionObjects

		// Add a button from openFile Object and set a custom title
		JButton buttonOpenFile = new JButton(openFile);
		buttonOpenFile.setHideActionText(true);			// To hide the NAME attribute of Action button
		buttonOpenFile.setText("Open File Button");		// To overwrite the displayed attribute
		buttonPanel.add(buttonOpenFile); // Button created from the same Action object as for OpenFile

		// Add a button from saveFile
		buttonPanel.add(new JButton(saveFile));

		// Add a button from quitProgram
		buttonPanel.add(new JButton(quitProgram));

		// Get the content pane and add the buttonPanel to it
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

	public void setToolBar() {
		toolBar = new JToolBar("Toolbar title");
		jFrame.getContentPane().add(toolBar, BorderLayout.NORTH);
	}

	public void addToolBarItems() {
		toolBar.add(openFile);
		toolBar.add(saveFile);
		toolBar.add(quitProgram);
		toolBar.addSeparator();
		toolBar.add(button1);
		toolBar.add(button2);
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
			// Identify event source by action object NAME property
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

			// Identify event source by Action Object variable name
			Object eventSource = e.getSource();

			// Determine event source object class type

			// If the eventSource object was coming from the ToolBar -> the Action object create is of type JButton
			if (eventSource instanceof JButton) {
				// If the event source was a JButton object
				Action currentAction = ((JButton) eventSource).getAction();
				if (currentAction == openFile) {
					System.out.println("Open file action object type JButton");
				}
			}// end eventsource instanceof

			// If the eventSource was coming from the MenuBar -> the Action object created is of type JMenuItem
			if (eventSource instanceof JMenuItem) {
				Action currentAction = ((JMenuItem) eventSource).getAction();
				if (currentAction == openFile) {
					System.out.println("Open file action object type JMenuItem");
				}
			}

		}// End of ActionPerformed method

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

	// Method is not used, but included here only for reference if Checkboxes were used
	private void setMenuItemCorrespondToolbarItem(JMenu sourceMenu, Object eventSource) {
		// Check if eventSource is JButton
		if (eventSource instanceof JButton) {
			// Create a Jbutton object reference for the eventSource object
			JButton tempButton = (JButton) eventSource;
			// Get the Action object for the event source - this is why the tempButton was created
			Action sourceActionObject = tempButton.getAction();
			// Iterate through available JMenu items
			for (int i = 0; i < sourceMenu.getItemCount(); i++) {
				// Create a temp reference for the current menu item
				JMenuItem tempItem = sourceMenu.getItem(i);
				// Set the state checked if the current JMenu item's Action object equals with the event source
				// Action object
				boolean isSameAction = tempItem.getAction().equals(sourceActionObject);
				tempItem.setSelected(isSameAction);
				if (isSameAction) {
					System.out.println("Menu item set to " + sourceActionObject.getValue(NAME));
				}

			}
		}
	} // End setMenuItem...

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
