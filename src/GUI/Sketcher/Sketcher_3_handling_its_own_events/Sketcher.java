package GUI.Sketcher.Sketcher_3_handling_its_own_events;
// Sketching application
import javax.swing.*;
import java.awt.*;

import javax.swing.SwingUtilities;

public class Sketcher {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createWindow();
            }
        });
  }

  public static void createWindow(){
    window = new SketcherFrame("Sketcher handling its own events");                            // Create the app window
    Toolkit theKit = window.getToolkit();                              // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();                        // Get screen size

    // Set the position to screen center & size to half screen size
    window.setSize(wndSize.width/2, wndSize.height/2);                 // Set window size
    window.setLocationRelativeTo(null);                                // Center window
//    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    window.setVisible(true);
  }

  private static SketcherFrame window;                                 // The application window
}