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

public class JMenuBar_Sample {

	private static JFrame window;

	private static void addMenuBar(JFrame jFrame) {
		JMenuBar mainMenuBar = new JMenuBar();
		jFrame.setJMenuBar(mainMenuBar);
		JMenu jMenu = new JMenu("Menu");
		JMenu jSubMenu = new JMenu("SubMenu");

		JMenuItem menuItem1 = new JMenuItem("MenuItem 1");
		JMenuItem menuItem2 = new JMenuItem("MenuItem 2");
		JCheckBoxMenuItem jcbMenuItem1 = new JCheckBoxMenuItem("Checkbox 1");
		JCheckBoxMenuItem jcbMenuItem2 = new JCheckBoxMenuItem("Checkbox 2");
		JRadioButtonMenuItem jrbMenuItem1 = new JRadioButtonMenuItem("Button 1");
		JRadioButtonMenuItem jrbMenuItem2 = new JRadioButtonMenuItem("Button 2");
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(jrbMenuItem1);
		radioButtonGroup.add(jrbMenuItem2);

		// Add menu elements
		mainMenuBar.add(jMenu);
		jMenu.add(menuItem1);
		jMenu.add(jcbMenuItem1);
		jMenu.add(jrbMenuItem1);
		jMenu.addSeparator();
		jMenu.add(jSubMenu);
		jSubMenu.add(menuItem2);
		jSubMenu.add(jcbMenuItem2);
		jSubMenu.add(jrbMenuItem2);

		// Create shortcuts for menu
		jMenu.setMnemonic('M');
		
		//Create accelerator for menu items
		menuItem1.setAccelerator(KeyStroke.getKeyStroke('M', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));
		menuItem2.setAccelerator(KeyStroke.getKeyStroke('N', (CTRL_DOWN_MASK | ALT_DOWN_MASK)));

	}

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
}
