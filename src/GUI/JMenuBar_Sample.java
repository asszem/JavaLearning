package GUI;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import static java.awt.event.InputEvent.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JMenuBar_Sample implements ActionListener, ItemListener {

	private static JFrame window;
	private static JMenuBar mainMenuBar = new JMenuBar();
	private static JMenu jMenu = new JMenu("Menu");
	private static JMenu jSubMenu = new JMenu("SubMenu");
	private static JMenuItem menuItem1 = new JMenuItem("MenuItem 1");
	private static JMenuItem menuItem2 = new JMenuItem("MenuItem 2");
	private static JMenuItem quitMenuItem = new JMenuItem("Quit");
	private static JCheckBoxMenuItem jcbMenuItem1 = new JCheckBoxMenuItem("Checkbox 1");
	private static JCheckBoxMenuItem jcbMenuItem2 = new JCheckBoxMenuItem("Checkbox 2");
	private static JRadioButtonMenuItem jrbMenuItem1 = new JRadioButtonMenuItem("Button 1");
	private static JRadioButtonMenuItem jrbMenuItem2 = new JRadioButtonMenuItem("Button 2");
	private static ButtonGroup radioButtonGroup = new ButtonGroup();

	private static void addMenuBar(JFrame jFrame) {
		jFrame.setJMenuBar(mainMenuBar);
		radioButtonGroup.add(jrbMenuItem1);
		radioButtonGroup.add(jrbMenuItem2);

		// Add menu elements
		mainMenuBar.add(jMenu);
		jMenu.add(menuItem1);
		jMenu.add(jcbMenuItem1);
		jMenu.add(jrbMenuItem1);
		jMenu.addSeparator();
		jMenu.add(jSubMenu);
		jMenu.add(quitMenuItem);
		jSubMenu.add(menuItem2);
		jSubMenu.add(jcbMenuItem2);
		jSubMenu.add(jrbMenuItem2);

		// Create shortcuts for Menu - Alt-M will open Menu
		jMenu.setMnemonic('M');

		// Create accelerator for menu items
		menuItem1.setAccelerator(KeyStroke.getKeyStroke('M', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));
		menuItem2.setAccelerator(KeyStroke.getKeyStroke('N', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));

		// Add action listener to menu
		JMenuBar_Sample menuActionListener = new JMenuBar_Sample();
		menuItem1.addActionListener(menuActionListener);
		menuItem2.addActionListener(menuActionListener);
		jcbMenuItem1.addItemListener(menuActionListener);
		
		
		quitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Quit selected. Exiting.");
				System.exit(0);
			}
		});
	}//end of addMenuBar method

	public static void createJFrame() {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setBounds(300, 300, 600, 600);
		addMenuBar(window);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createJFrame();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if (eventSource == menuItem1) {
			System.out.println("Menu item 1 selected");
		}
		if (eventSource == menuItem2) {
			System.out.println("Menu item 2 selected");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object eventSource = e.getSource();
		if (eventSource == jcbMenuItem1) {
			System.out.print("CheckBox1 item state changed. isSelected: ");
			System.out.println(jcbMenuItem1.isSelected());
		}
		
	}
}
