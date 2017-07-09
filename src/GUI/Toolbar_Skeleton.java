package GUI;

import static java.awt.event.InputEvent.*;		//To import accelerator key masks
import static javax.swing.Action.*;				//To import Action object property keys
import static javax.swing.Action.NAME;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class Toolbar_Skeleton {

	private JFrame window;
	private ToolbarAction tbaOpen;
	private ToolbarAction tbaSave;
	private ToolbarAction tbaQuit;
	private JToolBar toolBar;

	// Toolbar icons
	public final static String imagePath = "J:/Horton Assets/Sketcher/";
	public final static Icon OPEN24 = new ImageIcon(imagePath + "Open24.gif");
	public final static Icon SAVE24 = new ImageIcon(imagePath + "Save24.gif");

	public void createGUI() {
		window = createJFrame();
		createActionObjects();
		setIconForToolbarObjects();
		setTooltipForToolBarObjects();
		createToolbar();
		addToolbarItems();
		window.setVisible(true);
	}

	public void createActionObjects() {
		tbaOpen = new ToolbarAction("Open", 'O', CTRL_DOWN_MASK);
		tbaSave = new ToolbarAction("Save", 'S', CTRL_DOWN_MASK);
		tbaQuit = new ToolbarAction("Quit", 'Q', CTRL_DOWN_MASK);
	}

	public void setIconForToolbarObjects() {
		tbaOpen.putValue(LARGE_ICON_KEY, OPEN24);
		tbaSave.putValue(LARGE_ICON_KEY, SAVE24);
		// no icon gif for tbaQuit
	}
	public void setTooltipForToolBarObjects(){
		tbaOpen.putValue(SHORT_DESCRIPTION, "Toolbar open tooltip");
		tbaSave.putValue(SHORT_DESCRIPTION, "Toolbar save tooltip");
	}

	public void createToolbar() {
		toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(true);
		toolBar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.darkGray),
				BorderFactory.createEmptyBorder(2, 2, 4, 2)));

		// Add the toolbar to the container
		Container content = window.getContentPane();
		content.add(toolBar, BorderLayout.NORTH); // Default layout mgr is BorderLayout for content pane
	}

	public void addToolbarItems() {
		toolBar.add(tbaOpen);
		toolBar.add(tbaSave);
		toolBar.add(tbaQuit);
		toolBar.addSeparator();
		createToolBarButton(tbaOpen);
		createToolBarButton(tbaSave);
		createToolBarButton(tbaQuit);
		toolBar.addSeparator();
	}

	public void createToolBarButton(Action action) {
		JButton button = new JButton(action);                              // Create a JButton object from Action object
		button.setBorder(BorderFactory.createCompoundBorder(               // Add button border
				new EmptyBorder(2, 5, 5, 2),                               // Outside border - empty, as it is used as a padding from the edges of toolbar
				BorderFactory.createRaisedBevelBorder()));                 // Inside border
		button.setHideActionText(false);                                    // No label on the button
		toolBar.add(button);
	}

	public JFrame createJFrame() {
		JFrame window = new JFrame("Toolbar Skeleton");
		Toolkit theKit = window.getToolkit();                             // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       // Get screen size
		window.setSize(wndSize.width / 2, wndSize.height / 2);   	      // Set window size
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return window;
	}

	public static void main(String[] args) {
		Toolbar_Skeleton appInstance = new Toolbar_Skeleton();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				appInstance.createGUI();
			}
		});
	}

	@SuppressWarnings("serial")
	class ToolbarAction extends AbstractAction {

		// Basic constructor
		public ToolbarAction(String name) {
			super(name);
			//Toolbar icon also could be set here based on action name
			/*
			String fileName=name.equals("Change color")?"color":"numbers";
			String iconFileName = "J:\\Exercises\\Ch18\\" + fileName + ".gif";
			if (Files.exists(Paths.get(iconFileName))) {
				putValue(SMALL_ICON, new ImageIcon(iconFileName));
			}
			*/
		}

		// Constructor for menu item accellerators
		public ToolbarAction(String name, char shortcut, int keyStrokeModifier) {
			super(name);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(shortcut, keyStrokeModifier)); // Set property by key:value

			// Now find the character to underline
			int index = name.toUpperCase().indexOf(shortcut);
			if (index != -1) {
				putValue(DISPLAYED_MNEMONIC_INDEX_KEY, index); // Set property by key:value
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object eventSource = e.getSource();
			if (eventSource instanceof JButton) {
				System.out.println("Event source: JButton");
				Action currentAction = ((JButton) eventSource).getAction();
				if (currentAction == tbaOpen) {
					System.out.println("Open pressed");
				}
				if (currentAction == tbaSave) {
					System.out.println("Save pressed");
				}
				if (currentAction == tbaQuit) {
					System.out.println("Quit pressed");
					System.exit(0);
				}
			}
		}
	}

	// Method is not used, but included here only for reference
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
	}

} // end of Toolbar_Skeleton class
