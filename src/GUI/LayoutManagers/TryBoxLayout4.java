package GUI.LayoutManagers;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class TryBoxLayout4 {

  public static void createWindow(){
    JFrame aWindow = new JFrame("This is the Window Title");
    Toolkit theKit = aWindow.getToolkit();                             // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();                        // Get screen size

    // Set the position to screen center & size to half screen size
    aWindow.setSize(wndSize.width/2, wndSize.height/2);                // Set window size
    aWindow.setLocationRelativeTo(null);                               // Center window
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create left column of radio buttons
    Box leftBox = Box.createVerticalBox();
    ButtonGroup radioGroup = new ButtonGroup();                        // Create button group
    JRadioButton rbutton;                                              // Stores a button
    radioGroup.add(rbutton = new JRadioButton("Red"));                 // Add to group
    leftBox.add(rbutton);                                                 // Add to Box
    leftBox.add(Box.createVerticalStrut(30));                             // Space between
    radioGroup.add(rbutton = new JRadioButton("Green"));
    leftBox.add(rbutton);
    leftBox.add(Box.createVerticalStrut(30));                             // Space between
    radioGroup.add(rbutton = new JRadioButton("Blue"));
    leftBox.add(rbutton);
    leftBox.add(Box.createVerticalStrut(30));                             // Space between
    radioGroup.add(rbutton = new JRadioButton("Yellow"));
    leftBox.add(rbutton);
    leftBox.add(Box.createGlue());                                        // Glue at the end

    // Create a panel with a titled border to hold the left Box container
    JPanel leftPanel = new JPanel(new BorderLayout());
    leftPanel.setBorder(new TitledBorder(
                                   new EtchedBorder(EtchedBorder.RAISED),                 // Border to use
                                   "Left Panel Border"));                     // Border title
    leftPanel.add(Box.createHorizontalStrut(100));
    leftPanel.add(leftBox, BorderLayout.WEST);

    // Create right columns of checkboxes
    //TODO create a method that calculates current font width in pixels
    String rightPanelTitle="Right Panel Border";
//    GraphicsEnvironment.getLocalGraphicsEnvironment().get
    Box rightBox = Box.createVerticalBox();
//    rightBox.add(Box.createHorizontalStrut(rightPanelTitle.length()*20));                            // Starting space
//    rightBox.add(Box.createVerticalStrut(30));   
    rightBox.add(new JCheckBox("Dashed"));
    rightBox.add(Box.createVerticalStrut(30));                            // Space between
    rightBox.add(new JCheckBox("Thick"));
    rightBox.add(Box.createVerticalStrut(30));                            // Space between
    rightBox.add(new JCheckBox("Rounded"));
    rightBox.add(Box.createGlue());                                       // Glue at the end

    // Create a panel with a titled border to hold the right Box container
    JPanel rightPanel = new JPanel(new BorderLayout());
    rightPanel.setBorder(new TitledBorder(
                                   new EtchedBorder(),                 // Border to use
                                   rightPanelTitle));                // Border title
    rightPanel.add(Box.createHorizontalStrut(120));
    rightPanel.add(rightBox, BorderLayout.WEST);

    // Create Top Box to hold left and right panel
    Box topBox = Box.createHorizontalBox();
    topBox.add(leftPanel);
    topBox.add(Box.createHorizontalStrut(5));                             // Space between vertical boxes
    topBox.add(rightPanel);
    topBox.add(Box.createVerticalStrut(5));
    
    // Create top panel to hold Top Box
    JPanel topPanel = new JPanel(); //flow layout is default
    topPanel.setBorder(new TitledBorder("Top Panel Border"));
    topPanel.add(topBox);  //Top panel has only the top box
    
    
    // Create bottom Panel to hold the bottom buttons
    JPanel bottomPanel = new JPanel();
    Border outerBorder=BorderFactory.createLineBorder(Color.RED, 10);
    Border innerBorder=BorderFactory.createRaisedBevelBorder();
    Border titleBorder=BorderFactory.createTitledBorder("Bottom Panel Border");
    Border doubleBorder=new CompoundBorder(outerBorder, innerBorder);
    bottomPanel.setBorder(new CompoundBorder(titleBorder,doubleBorder));
    
//    bottomPanel.setBorder(new CompoundBorder(
//           BorderFactory.createLineBorder(Color.black, 1),             // Outer border
//           BorderFactory.createBevelBorder(BevelBorder.RAISED)));      // Inner border

    Border buttonBorder = BorderFactory.createRaisedBevelBorder();             // Button border
    JButton button;
    Dimension size = new Dimension(80,20);
    bottomPanel.add(button = new JButton("Defaults"));
    button.setBorder(buttonBorder);
    button.setPreferredSize(size);
    bottomPanel.add(button = new JButton("OK"));
    button.setBorder(buttonBorder);
    button.setPreferredSize(size);
    bottomPanel.add(button = new JButton("Cancel"));
    button.setBorder(buttonBorder);
    button.setPreferredSize(size);

    //Create a Full Content Box that holds Top and Bottom Panel
    Box fullContentBox=Box.createVerticalBox();
    fullContentBox.add(topPanel);
    fullContentBox.add(bottomPanel);
    //Create a full content panel which has a border and holds the Full Content Box
    JPanel fullContentPanel = new JPanel();
    fullContentPanel.setBorder(new TitledBorder("Full Content Panel Border"));
    fullContentPanel.add(fullContentBox);
    
    // Add the fullContentPanel to contentPane
    Container content = aWindow.getContentPane();                      // Get content pane
    content.add(fullContentPanel);
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