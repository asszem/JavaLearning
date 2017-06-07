package GUI.LayoutManagers;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class TryBoxLayout {

  public static void createWindow(){
    JFrame aWindow = new JFrame("This is the Window Title");
    Toolkit theKit = aWindow.getToolkit();                             // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();                        // Get screen size

    // Set the position to screen center & size to half screen size
    aWindow.setSize(wndSize.width/2, wndSize.height/2);                // Set window size
    aWindow.setLocationRelativeTo(null);                               // Center window
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create left column of radio buttons
    Box left = Box.createVerticalBox();
    ButtonGroup radioGroup = new ButtonGroup();                        // Create button group
    JRadioButton rbutton;                                              // Stores a button
    radioGroup.add(rbutton = new JRadioButton("Red"));                 // Add to group
    left.add(rbutton);                                                 // Add to Box
    radioGroup.add(rbutton = new JRadioButton("Green"));
    left.add(rbutton);
    radioGroup.add(rbutton = new JRadioButton("Blue"));
    left.add(rbutton);
    radioGroup.add(rbutton = new JRadioButton("Yellow"));
    left.add(rbutton);

    // Create right columns of checkboxes
    Box right = Box.createVerticalBox();
    right.add(new JCheckBox("Dashed"));
    right.add(new JCheckBox("Thick"));
    right.add(new JCheckBox("Rounded"));

    right.add(rbutton= new JRadioButton("New R Button"));
    radioGroup.add(rbutton);

    // Create top row to hold left and right
    Box top = Box.createHorizontalBox();
    top.add(left);
    top.add(right);
    // Create bottom row of buttons
    JPanel bottomPanel = new JPanel();
    Border edge = BorderFactory.createRaisedBevelBorder();             // Button border
    JButton button;
    Dimension size = new Dimension(80,20);
    bottomPanel.add(button = new JButton("Defaults"));
    button.setBorder(edge);
    button.setPreferredSize(size);
    bottomPanel.add(button = new JButton("OK"));
    button.setBorder(edge);
    button.setPreferredSize(size);
    bottomPanel.add(button = new JButton("Cancel"));
    button.setBorder(edge);
    button.setPreferredSize(size);

    // Add top and bottom panel to content pane
    Container content = aWindow.getContentPane();                      // Get content pane
    content.setLayout(new BorderLayout());                             // Set border layout manager
    content.add(top, BorderLayout.CENTER);
    content.add(bottomPanel, BorderLayout.SOUTH);

    aWindow.pack();                                                    // Size for components
    aWindow.setVisible(true);                                          // Display the window
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createWindow();
            }
        });
  }
}
