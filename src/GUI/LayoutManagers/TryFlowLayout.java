package GUI.LayoutManagers;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

public class TryFlowLayout {
  // Create the application window
  public static void createWindow(){
    JFrame aWindow = new JFrame("This is the Window Title");
    Toolkit theKit = aWindow.getToolkit();                             // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();                        // Get screen size

    // Set the position to screen center & size to half screen size
    aWindow.setSize(wndSize.width/2, wndSize.height/2);                // Set window size
    aWindow.setLocationRelativeTo(null);                               // Center window
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//    FlowLayout flow = new FlowLayout();                           // Create a layout manager
//    FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
    LayoutManager flow2 = new FlowLayout();
    FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 20, 60);		//Left aligned, Horizontal, vertical gap
    Container content = aWindow.getContentPane();                   // Get the content pane
    content.setLayout(flow);                                        // Set the container layout mgr
    flow.setHgap(20);
    flow.setVgap(20);

    // Now add six button components
    for(int i =  1; i <= 6 ; ++i)
      content.add(new JButton("Press " + i));                          // Add a Button to content pane

    aWindow.pack(); //Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. 
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