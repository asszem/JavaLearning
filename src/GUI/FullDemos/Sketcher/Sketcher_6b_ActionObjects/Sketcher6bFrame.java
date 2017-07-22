package GUI.FullDemos.Sketcher.Sketcher_6b_ActionObjects;

// Frame for the Sketcher6b application
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import static java.awt.event.InputEvent.*;
import static GUI.FullDemos.Sketcher.Sketcher_6b_ActionObjects.SketcherConstants.*;
import static java.awt.AWTEvent.*;
import static java.awt.Color.*;

@SuppressWarnings("serial")
public class Sketcher6bFrame extends JFrame {

	// File actions - each action will have a new object instance of type FileAction
	private FileAction newAction, openAction, closeAction, saveAction, saveAsAction, printAction, exitAction, renameAction;
	private FileAction[] fileActions;                                    // File actions as an array

	// Element type actions
	private TypeAction lineAction, rectangleAction, circleAction, curveAction;
	private TypeAction[] typeActions;                                    // Type actions as an array

	// Element color actions
	private ColorAction redAction, yellowAction, greenAction, blueAction;
	private ColorAction[] colorActions;                                  // Color actions as an array

	private JMenuBar menuBar = new JMenuBar();                           // Window menu bar
	private JMenu elementMenu;                                           // Elements menu
	private JMenu colorMenu;                                             // Color menu
	private Color elementColor = DEFAULT_ELEMENT_COLOR;                  // Current element color
	private int elementType = DEFAULT_ELEMENT_TYPE;                      // Current element type

	// Constructor
	public Sketcher6bFrame(String title) {
		setTitle(title);                                                   // Set the window title
		setJMenuBar(menuBar);                                              // Add the menu bar to the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);                           // Default is exit the application

		createFileMenu();                                                  // Create the File menu
		createElementMenu();                                               // Create the element menu
		createColorMenu();                                                 // Create the element menu
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
		renameAction = new FileAction("Rename", 'R', CTRL_DOWN_MASK);

		// Initialize the array
		FileAction[] actions = { openAction, closeAction, saveAction, saveAsAction, printAction, exitAction, renameAction };
		fileActions = actions;
	}

	// Create the File menu
	private void createFileMenu() {
		JMenu fileMenu = new JMenu("File");                                // Create File menu
		fileMenu.setMnemonic('F');                                         // Create shortcut
		createFileMenuActions();                                           // Create Actions for File menu item

		// Construct the file drop-down menu
		fileMenu.add(newAction);                                           // New Sketch menu item
		fileMenu.add(openAction);                                          // Open sketch menu item
		fileMenu.add(closeAction);                                         // Close sketch menu item
		fileMenu.addSeparator();                                           // Add separator
		fileMenu.add(saveAction);                                          // Save sketch to file
		fileMenu.add(saveAsAction);                                        // Save As menu item
		fileMenu.addSeparator();                                           // Add separator
		fileMenu.add(renameAction);										   // Rename the file - this added just to practice and test if I understand code correctly
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
	}

	// Create the Element menu
	private void createElementMenu() {
		createElementTypeActions();
		elementMenu = new JMenu("Elements");                               // Create Elements menu
		elementMenu.setMnemonic('E');                                      // Create shortcut
		createRadioButtonDropDown(elementMenu, typeActions, lineAction);
		menuBar.add(elementMenu);                                          // Add the element menu
	}

	// Create Element menu actions
	private void createElementColorActions() {
		redAction = new ColorAction("Red", RED, 'R', CTRL_DOWN_MASK | ALT_DOWN_MASK);
		yellowAction = new ColorAction("Yellow", YELLOW, 'Y', CTRL_DOWN_MASK | ALT_DOWN_MASK);
		greenAction = new ColorAction("Green", GREEN, 'G', CTRL_DOWN_MASK | ALT_DOWN_MASK);
		blueAction = new ColorAction("Blue", BLUE, 'B', CTRL_DOWN_MASK | ALT_DOWN_MASK);

		// Initialize the array
		ColorAction[] actions = { redAction, greenAction, blueAction, yellowAction };
		colorActions = actions;
	}

	// Create the Color menu
	private void createColorMenu() {
		createElementColorActions();
		colorMenu = new JMenu("Color");                                    // Create Elements menu
		colorMenu.setMnemonic('C');                                        // Create shortcut
		createRadioButtonDropDown(colorMenu, colorActions, blueAction);
		menuBar.add(colorMenu);                                            // Add the color menu
	}

	// Menu creation helper
	private void createRadioButtonDropDown(JMenu menu, Action[] actions, Action selected) {
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem item = null;
		for (Action action : actions) {
			group.add(menu.add(item = new JRadioButtonMenuItem(action)));
			if (action == selected) {
				item.setSelected(true);                                        // This is default selected
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
			Object source = e.getSource();
			System.out.println(source);
			if (source==renameAction){
				System.out.println("Rename selected");
			}
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
			elementColor = color;

			// This is temporary just to show it works...
			getContentPane().setBackground(color);
		}

		private Color color;
	}

}
