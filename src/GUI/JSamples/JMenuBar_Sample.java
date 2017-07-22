package GUI.JSamples;

import static java.awt.event.InputEvent.ALT_DOWN_MASK;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class JMenuBar_Sample implements ActionListener, ItemListener {

	private JFrame window;
	private JMenuBar mainMenuBar = new JMenuBar();
	private JMenu jMenu = new JMenu("Menu");
	private JMenu jSubMenu = new JMenu("SubMenu");
	private JMenuItem menuItem1 = new JMenuItem("MenuItem 1");
	private JMenuItem menuItem2 = new JMenuItem("MenuItem 2");
	private JMenuItem quitMenuItem = new JMenuItem("Quit");
	private JCheckBoxMenuItem jcbMenuItem1 = new JCheckBoxMenuItem("Checkbox 1");
	private JCheckBoxMenuItem jcbMenuItem2 = new JCheckBoxMenuItem("Checkbox 2");
	private JRadioButtonMenuItem jrbMenuItem1 = new JRadioButtonMenuItem("Button 1");
	private JRadioButtonMenuItem jrbMenuItem2 = new JRadioButtonMenuItem("Button 2");
	private ButtonGroup radioButtonGroup = new ButtonGroup();

	private void addMenuBar(JFrame jFrame) {
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

	public void createJFrame() {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setBounds(300, 300, 600, 600);
		addMenuBar(window);
		window.setVisible(true);
	}

	// Menu creation helper - copied from Sketcher7 for reference
	// Method creates radio buttons from Action objects array and set the selected button based on 3rd argument
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

	public static void main(String[] args) {
		JMenuBar_Sample theAppInstance=new JMenuBar_Sample();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				theAppInstance.createJFrame();
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
