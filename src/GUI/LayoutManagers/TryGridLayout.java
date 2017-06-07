package GUI.LayoutManagers;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EtchedBorder;

public class TryGridLayout {

  public static void createWindow(){
    JFrame aWindow = new JFrame("This is the Window Title");
    Toolkit theKit = aWindow.getToolkit();                             // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();                        // Get screen size

    // Set the position to screen center & size to half screen size
    aWindow.setSize(wndSize.width/2, wndSize.height/2);                // Set window size
    aWindow.setLocationRelativeTo(null);                               // Center window
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    GridLayout grid = new GridLayout(3,4,30,20);   // Rows, cols, hGap, vGap
    Container content = aWindow.getContentPane();                      // Get the content pane
    content.setLayout(grid);                                           // Set the container layout mgr
    EtchedBorder edge = new EtchedBorder(EtchedBorder.RAISED);         // Button border

    // Now add ten Button components
    JButton button = null;                                             // Stores a button
    for(int i = 1 ; i <= 10 ; ++i) {
      content.add(button = new JButton(" Press " + i));                // Add a Button
      button.setBorder(edge);                                          // Set the border
    }
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