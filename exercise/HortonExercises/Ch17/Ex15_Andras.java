/*
 * Requirement: 
 * 
 * Create an application with a square window in the center of the screen that is half the height of the screen
by deriving your own window class from JFrame.

Ex2:
Add six buttons to the application in the previous example in a vertical column on the left side of the
application window.

Ex3:
Add a menu bar containing the items File, Edit, Window, and Help

Ex4:
Add a drop-down menu for Edit containing two groups of items of your own choice with a separator
between them.

Ex5:
Add another item to the Edit drop-down menu, which itself has a drop-down menu, and provide
accelerators for the items in the menu.
 * */
package HortonExercises.Ch17;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import static java.awt.event.InputEvent.*;

@SuppressWarnings("serial")
public class Ex15_Andras extends JFrame {

	private static Ex15_Andras window;

	public static void createWindow() {
		window = new Ex15_Andras();
		window.setTitle("Exercise 2");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit toolkit = window.getToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int height = (int) screenSize.getHeight();
		int width = (int) screenSize.getWidth();
		window.setSize(height / 2, height / 2);
		window.setLocationRelativeTo(null);

		Container contentPane = window.getContentPane();
		addMenuBar();
		// addButtonsBorderLayout(contentPane);
		addButtonsBox(contentPane);
		// addButtonsGridLayout(contentPane);

		window.setVisible(true);
	}

	public static void addMenuBar() {
		JMenuBar mainMenuBar = new JMenuBar();
		window.setJMenuBar(mainMenuBar);
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu windowMenu = new JMenu("Window");
		JMenu helpMenu = new JMenu("Help");
		mainMenuBar.add(fileMenu);
		mainMenuBar.add(editMenu);
		mainMenuBar.add(windowMenu);
		mainMenuBar.add(helpMenu);

		JMenu editSubMenu1 = new JMenu("Edit Submenu 1");
		JMenu editSubMenu2 = new JMenu("Edit Submenu 2");
		JMenuItem menuItem1 = new JMenuItem("Menu Item 1");
		JMenuItem menuItem2 = new JMenuItem("Menu Item 2");

		editMenu.add(editSubMenu1);
		editMenu.addSeparator();
		editMenu.add(editSubMenu2);
		
		editSubMenu1.add(menuItem1);
		editSubMenu2.add(menuItem2);

		// Create shortcuts for menu
		fileMenu.setMnemonic('F');
		editMenu.setMnemonic('E');
		windowMenu.setMnemonic('W');
		helpMenu.setMnemonic('H');
		
		//Create accelerator for menu items
		menuItem1.setAccelerator(KeyStroke.getKeyStroke('M', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));
		menuItem2.setAccelerator(KeyStroke.getKeyStroke('N', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));
	}

	public static void addButtonsGridLayout(Container contentPane) {
		GridLayout gridLayout = new GridLayout(6, 1);
		contentPane.setLayout(gridLayout);
		JButton button;
		for (int i = 0; i < 6; i++) {
			button = new JButton("Button " + (i + 1));
			contentPane.add(button);
		}
	}

	public static void addButtonsBox(Container contentPane) {
		Box buttonBox = Box.createVerticalBox();
		JButton button;
		for (int i = 0; i < 6; i++) {
			button = new JButton("Button " + (i + 1));
			buttonBox.add(button);
			buttonBox.add(Box.createVerticalStrut(window.getHeight() / 10));
			// buttonBox.add(Box.createVerticalStrut(100));
		}
		contentPane.add(buttonBox);
	}

	public static void addButtonsBorderLayout(Container contentPane) {
		BorderLayout borderLayout = new BorderLayout();
		contentPane.setLayout(borderLayout);
		Box buttonBox = Box.createVerticalBox();
		JButton button;
		for (int i = 0; i < 6; i++) {
			button = new JButton("Button " + (i + 1));
			buttonBox.add(button);
		}
		contentPane.add(buttonBox, BorderLayout.WEST);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createWindow();
			}
		});
	}

}
