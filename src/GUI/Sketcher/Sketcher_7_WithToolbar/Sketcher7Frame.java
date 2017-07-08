package GUI.Sketcher.Sketcher_7_WithToolbar;

// Frame for the Sketcher7 application
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

import static java.awt.event.InputEvent.*;
import static java.awt.Color.*;
import static GUI.Sketcher.Sketcher_7_WithToolbar.Sketcher7Constants.*;
import static javax.swing.Action.*;

@SuppressWarnings("serial")
public class Sketcher7Frame extends JFrame {

	// File actions
	private FileAction newAction, openAction, closeAction, saveAction, saveAsAction, printAction, exitAction;
	private FileAction[] fileActions;                                    // File actions as an array

	// Element type actions
	private TypeAction lineAction, rectangleAction, circleAction, curveAction;
	private TypeAction[] typeActions;                                    // Type actions as an array

	// Element color actions
	private ColorAction redAction, yellowAction, greenAction, blueAction;
	private ColorAction[] colorActions;                                  // Color actions as an array

	// MenuBar
	private JMenuBar menuBar = new JMenuBar();                           // Window menu bar
	private JMenu colorMenu;											 // ColorMenu in Menubar
	private JMenu elementMenu;
	private JMenu fileMenu;

	private Color elementColor = DEFAULT_ELEMENT_COLOR;                  // Current element color
	private int elementType = DEFAULT_ELEMENT_TYPE;                      // Current element type

	// Toolbar
	private JToolBar toolBar = new JToolBar();                           // Window toolbar

	// Constructor
	public Sketcher7Frame(String title) {
		setTitle(title);                                                   // Set the window title
		setJMenuBar(menuBar);                                              // Add the menu bar to the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);                           // Default is exit the application

		createFileMenu();                                                  // Create the File menu
		createElementMenu();                                               // Create the element menu
		createColorMenu();                                                 // Create the element menu
		createToolbar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);
	}

	// Create File menu item actions
	private void createFileMenuActions() {
		newAction = new FileAction("New", 'N', CTRL_DOWN_MASK);
		openAction = new FileAction("Open", 'O', CTRL_DOWN_MASK);
		closeAction = new FileAction("Close");
		saveAction = new FileAction("Save", 'S', CTRL_DOWN_MASK);
		saveAsAction = new FileAction("Save As...");
		printAction = new FileAction("Print", 'P', CTRL_DOWN_MASK);
		exitAction = new FileAction("Exit", 'X', CTRL_DOWN_MASK);

		// Disable some items by default
		 closeAction.setEnabled(false);
		 saveAction.setEnabled(false);
		 saveAsAction.setEnabled(false);
		 printAction.setEnabled(false);

		// Initialize the array
		FileAction[] actions = { openAction, closeAction, saveAction, saveAsAction, printAction, exitAction };
		fileActions = actions;

		// Add toolbar icons
		newAction.putValue(LARGE_ICON_KEY, NEW24);
		openAction.putValue(LARGE_ICON_KEY, OPEN24);
		saveAction.putValue(LARGE_ICON_KEY, SAVE24);
		saveAsAction.putValue(LARGE_ICON_KEY, SAVEAS24);
		printAction.putValue(LARGE_ICON_KEY, PRINT24);

		// Add small menu icons
		newAction.putValue(SMALL_ICON, NEW16);
		openAction.putValue(SMALL_ICON, OPEN16);
		saveAction.putValue(SMALL_ICON, SAVE16);
		saveAsAction.putValue(SMALL_ICON, SAVEAS16);
		printAction.putValue(SMALL_ICON, PRINT16);

		// Add tooltips
		newAction.putValue(SHORT_DESCRIPTION, "Create new file");
		openAction.putValue(SHORT_DESCRIPTION, "Open file");
		saveAction.putValue(SHORT_DESCRIPTION, "Save file");
		saveAsAction.putValue(SHORT_DESCRIPTION, "Save as...");
		printAction.putValue(SHORT_DESCRIPTION, "Print file");
	}

	// Create the File menu
	private void createFileMenu() {
		fileMenu = new JMenu("File");		                                // Create File menu
		fileMenu.setMnemonic('F');                                          // Create shortcut
		createFileMenuActions();                                            // Create Actions for File menu item

		// Construct the file drop-down menu
		fileMenu.add(newAction);                                           // New Sketch menu item
		fileMenu.add(openAction);                                          // Open sketch menu item
		fileMenu.add(closeAction);                                         // Close sketch menu item
		fileMenu.addSeparator();                                           // Add separator
		fileMenu.add(saveAction);                                          // Save sketch to file
		fileMenu.add(saveAsAction);                                        // Save As menu item
		fileMenu.addSeparator();                                           // Add separator
		fileMenu.add(printAction);                                         // Print sketch menu item
		fileMenu.addSeparator();                                           // Add separator
		fileMenu.add(exitAction);                                          // Print sketch menu item
		menuBar.add(fileMenu);                                             // Add the file menu
	}

	// Create Element menu actions
	private void createElementTypeActions() {
		lineAction = new TypeAction("Line", LINE, 'L', CTRL_DOWN_MASK);
		rectangleAction = new TypeAction("Rectangle", RECTANGLE, 'R', CTRL_DOWN_MASK);
		circleAction = new TypeAction("Circle", CIRCLE, 'C', CTRL_DOWN_MASK);
		curveAction = new TypeAction("Curve", CURVE, 'U', CTRL_DOWN_MASK);

		// Initialize the array
		TypeAction[] actions = { lineAction, rectangleAction, circleAction, curveAction };
		typeActions = actions;

		// Add large toolbar icons to Action objects
		lineAction.putValue(LARGE_ICON_KEY, LINE24); // Icon references are from Sketcher7Constants file
		rectangleAction.putValue(LARGE_ICON_KEY, RECTANGLE24);
		circleAction.putValue(LARGE_ICON_KEY, CIRCLE24);
		curveAction.putValue(LARGE_ICON_KEY, CURVE24);

		// Add small menu icons to Action objects
		lineAction.putValue(SMALL_ICON, LINE16); // Icon references are from Sketcher7Constants file
		rectangleAction.putValue(SMALL_ICON, RECTANGLE16);
		circleAction.putValue(SMALL_ICON, CIRCLE16);
		curveAction.putValue(SMALL_ICON, CURVE16);

		lineAction.putValue(SHORT_DESCRIPTION, "Draw lines");
		rectangleAction.putValue(SHORT_DESCRIPTION, "Draw rectangles");
		circleAction.putValue(SHORT_DESCRIPTION, "Draw circles");
		curveAction.putValue(SHORT_DESCRIPTION, "Draw curves");
	}

	// Create the Elements menu
	private void createElementMenu() {
		createElementTypeActions();
		elementMenu = new JMenu("Elements"); 	                           // Create Elements menu
		elementMenu.setMnemonic('E');                                      // Create shortcut
		createRadioButtonDropDown(elementMenu, typeActions, lineAction);
		menuBar.add(elementMenu);                                          // Add the element menu
	}

	// Create Color menu actions
	private void createElementColorActions() {
		redAction = new ColorAction("Red", RED, 'R', CTRL_DOWN_MASK | ALT_DOWN_MASK);
		yellowAction = new ColorAction("Yellow", YELLOW, 'Y', CTRL_DOWN_MASK | ALT_DOWN_MASK);
		greenAction = new ColorAction("Green", GREEN, 'G', CTRL_DOWN_MASK | ALT_DOWN_MASK);
		blueAction = new ColorAction("Blue", BLUE, 'B', CTRL_DOWN_MASK | ALT_DOWN_MASK);

		// Initialize the array
		ColorAction[] actions = { redAction, greenAction, blueAction, yellowAction };
		colorActions = actions;

		// Add color icons
		redAction.putValue(LARGE_ICON_KEY, RED24);
		yellowAction.putValue(LARGE_ICON_KEY, YELLOW24);
		greenAction.putValue(LARGE_ICON_KEY, GREEN24);
		blueAction.putValue(LARGE_ICON_KEY, BLUE24);

		redAction.putValue(SMALL_ICON, RED16);
		yellowAction.putValue(SMALL_ICON, YELLOW16);
		greenAction.putValue(SMALL_ICON, GREEN16);
		blueAction.putValue(SMALL_ICON, BLUE16);

		// Add tool tips
		redAction.putValue(SHORT_DESCRIPTION, "Draw in red");
		blueAction.putValue(SHORT_DESCRIPTION, "Draw in blue");
		greenAction.putValue(SHORT_DESCRIPTION, "Draw in green");
		yellowAction.putValue(SHORT_DESCRIPTION, "Draw in yellow");
	}

	// Create the Color menu
	private void createColorMenu() {
		createElementColorActions();
		colorMenu = new JMenu("Color");  		                            // Create Elements menu
		colorMenu.setMnemonic('C');                                         // Create shortcut
		createRadioButtonDropDown(colorMenu, colorActions, blueAction);
		menuBar.add(colorMenu);                                             // Add the color menu
	}

	// Menu creation helper
	private void createRadioButtonDropDown(JMenu menu, Action[] actions, Action selected) {
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem item = null;
		for (Action action : actions) {
			group.add(menu.add(item = new JRadioButtonMenuItem(action)));
			if (action == selected) {
				item.setSelected(true);                                        // This is default selected
			} else {
				item.setIcon(null); // If not selected, hide the small icon
			}
		}
	}

	// Create toolbar buttons on the toolbar
	private void createToolbar() {
		for (FileAction action : fileActions) {								// Iterate through fileActions array
			if (action != exitAction && action != closeAction)				// if its not an exitAction or closeAction
				addToolbarButton(action);                                   // Add the toolbar button using the addToolbarButton method
		}
		toolBar.addSeparator();
		for (TypeAction action : typeActions) {
			addToolbarButton(action);
		}
		toolBar.addSeparator();
		for (ColorAction action : colorActions) {
			addToolbarButton(action);
		}
	}

	// Create and add a toolbar button
	private void addToolbarButton(Action action) {
		JButton button = new JButton(action);                              // Create a JButton object from Action object
		button.setBorder(BorderFactory.createCompoundBorder(               // Add button border
				new EmptyBorder(2, 5, 5, 2),                               // Outside border - empty, as it is used as a padding from the edges of toolbar
				BorderFactory.createRaisedBevelBorder()));                 // Inside border
		button.setHideActionText(true);                                    // No label on the button
		toolBar.add(button);                                               // Add the toolbar button
	}

	// Method to set the same Menu state as it was set from pressing a JButton in the Toolbar
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
				if (isSameAction) {
					tempItem.setSelected(isSameAction);
					tempItem.setIcon(tempItem.getIcon());  // Does not work.
					System.out.println("Menu item set to " + sourceActionObject.getValue(NAME));
				}

			}
		}
	}

	// Inner class defining Action objects for File menu items
	class FileAction extends AbstractAction {

		// Create action with a name
		FileAction(String name) {
			super(name);
		}

		// Create action with a name and accelerator
		FileAction(String name, char ch, int modifiers) {
			super(name);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(ch, modifiers));

			// Now find the character to underline
			int index = name.toUpperCase().indexOf(ch);
			if (index != -1) {
				putValue(DISPLAYED_MNEMONIC_INDEX_KEY, index);
			}
		}

		// Event handler
		public void actionPerformed(ActionEvent e) {
			// You will add action code here eventually...
		}
	}

	// Inner class defining Action objects for Element type menu items
	class TypeAction extends AbstractAction {

		// Create action with just a name property
		TypeAction(String name, int typeID) {
			super(name);
			this.typeID = typeID;
		}

		// Create action with a name and an accelerator
		private TypeAction(String name, int typeID, char ch, int modifiers) {
			this(name, typeID);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(ch, modifiers));

			// Now find the character to underline
			int index = name.toUpperCase().indexOf(ch);
			if (index != -1) {
				putValue(DISPLAYED_MNEMONIC_INDEX_KEY, index);
			}
		}

		public void actionPerformed(ActionEvent e) {
			elementType = typeID;
			setMenuItemCorrespondToolbarItem(elementMenu, e.getSource());
		}

		private int typeID;
	}

	// Handles color menu items
	class ColorAction extends AbstractAction {

		// Create an action with a name and a color
		public ColorAction(String name, Color color) {
			super(name);
			this.color = color;
		}

		// Create an action with a name, a color, and an accelerator
		public ColorAction(String name, Color color, char ch, int modifiers) {
			this(name, color);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(ch, modifiers));

			// Now find the character to underline
			int index = name.toUpperCase().indexOf(ch);
			if (index != -1) {
				putValue(DISPLAYED_MNEMONIC_INDEX_KEY, index);
			}
		}

		public void actionPerformed(ActionEvent e) {
			// System.out.println("Color event");
			elementColor = color;
			if (getValue(NAME).equals("Red")) {
				System.out.println("Red action");
			}
			setMenuItemCorrespondToolbarItem(colorMenu, e.getSource());
		}

		private Color color;
	}

}
