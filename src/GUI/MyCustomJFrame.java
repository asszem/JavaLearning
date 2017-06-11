package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

@SuppressWarnings("serial")
public class MyCustomJFrame extends JFrame {

	private JMenuBar menuBar = new JMenuBar();                            // Window menu bar

	// Constructor
	public MyCustomJFrame(String title) {
		setTitle(title);                                                  // Set the window title
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Set the position to screen center & size to half screen size
		Toolkit theKit = this.getToolkit();                               // Get the window toolkit
		Dimension wndSize = theKit.getScreenSize();                       // Get screen size
		setSize(wndSize.width / 2, wndSize.height / 2);   	              // Set window size
		setLocationRelativeTo(null); 		                              // Center window

		// Set the menus
		setJMenuBar(menuBar);                                             // Add the menu bar to the window

		JMenu fileMenu = new JMenu("File");                               // Create File menu
		JMenu editMenu = new JMenu("Edit");                      		  // Create Elements menu

		menuBar.add(fileMenu);                                            // Add the file menu
		menuBar.add(editMenu);     		                                  // Add the element menu

		JMenu fileInnerMenu = new JMenu("File operations");
		fileMenu.add(fileInnerMenu);
		JMenuItem newFile = fileInnerMenu.add("New file");
		JMenuItem saveFile = fileInnerMenu.add("Save file");
		fileInnerMenu.addSeparator();
		JMenuItem openFile = fileInnerMenu.add("Open file");
		
	}
}
