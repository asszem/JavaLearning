package GUI.FullDemos.Sketcher.Sketcher_2;

// Main window for the Sketcher application
import javax.swing.*;
import static java.awt.event.InputEvent.*;

@SuppressWarnings("serial")
public class SketcherFrame extends JFrame {

	// Static instance variables

	// Top menu bar
	private JMenuBar menuBar = new JMenuBar();                           // Window menu bar

	// File menu items
	private JMenuItem newItem, openItem, closeItem, saveItem, saveAsItem, printItem, exitItem, aboutItem;

	// Element menu items
	private JRadioButtonMenuItem lineItem, rectangleItem, circleItem,   // Types
			curveItem, textItem;
	private JCheckBoxMenuItem redItem, yellowItem,                  	// Colors
			greenItem, blueItem;

	// Constructor
	public SketcherFrame(String title) {
		setTitle(title);                                                   // Set the window title
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setJMenuBar(menuBar);                                              // Add the menu bar to the window

		JMenu fileMenu = new JMenu("File");                                // Create File menu
		JMenu elementMenu = new JMenu("Elements");                         // Create Elements menu
		JMenu aboutMenu = new JMenu("About");

		// Create Shortcuts for top-level menus
		fileMenu.setMnemonic('F'); 						// Create shortcut
		elementMenu.setMnemonic('E'); 					// Create shortcut
		aboutMenu.setMnemonic('A');

		// Construct the About menu
		aboutItem = aboutMenu.add("Version");
		//About menu item accelerator
		aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));
		
		
		// Construct the file drop-down menu
		newItem = fileMenu.add("New");                                      // Add New item
		openItem = fileMenu.add("Open");                                    // Add Open item
		closeItem = fileMenu.add("Close");                                  // Add Close item
		fileMenu.addSeparator();                                            // Add separator
		saveItem = fileMenu.add("Save");                                    // Add Save item
		saveAsItem = fileMenu.add("Save As...");                            // Add Save As item
		fileMenu.addSeparator();                                            // Add separator
		printItem = fileMenu.add("Print");                                  // Add Print item
		exitItem = fileMenu.add("Quit");

		// Add File Menu accelerators
		newItem.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
		openItem.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
		saveItem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		printItem.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));

		// Construct the Element drop-down menu
		elementMenu.add(lineItem = new JRadioButtonMenuItem("Line", true));
		elementMenu.add(rectangleItem = new JRadioButtonMenuItem("Rectangle", false));
		elementMenu.add(circleItem = new JRadioButtonMenuItem("Circle", false));
		elementMenu.add(curveItem = new JRadioButtonMenuItem("Curve", false));
		ButtonGroup types = new ButtonGroup();
		types.add(lineItem);
		types.add(rectangleItem);
		types.add(circleItem);
		types.add(curveItem);

		// Add element menu Accelerators
		lineItem.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
		rectangleItem.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));
		circleItem.setAccelerator(KeyStroke.getKeyStroke('I', CTRL_DOWN_MASK));
		curveItem.setAccelerator(KeyStroke.getKeyStroke('V', CTRL_DOWN_MASK));
		elementMenu.addSeparator();

		JMenu colorMenu = new JMenu("Color");
		elementMenu.add(colorMenu);
		colorMenu.add(redItem = new JCheckBoxMenuItem("Red", false));
		colorMenu.add(yellowItem = new JCheckBoxMenuItem("Yellow", false));
		colorMenu.add(greenItem = new JCheckBoxMenuItem("Green", false));
		colorMenu.add(blueItem = new JCheckBoxMenuItem("Blue", true));
		// Add accelerators to color menu
		
		redItem.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK));
		yellowItem.setAccelerator(KeyStroke.getKeyStroke('Y', CTRL_DOWN_MASK));
		greenItem.setAccelerator(KeyStroke.getKeyStroke('G', CTRL_DOWN_MASK));
		blueItem.setAccelerator(KeyStroke.getKeyStroke('B', CTRL_DOWN_MASK));	
		
		
		

		menuBar.add(fileMenu);                                             // Add the file menu
		menuBar.add(elementMenu);                                          // Add the element menu
		menuBar.add(aboutMenu);												// Add the about menu
	}

}