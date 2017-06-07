package GUI.Applets;

import javax.swing.JButton;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import java.awt.Font;
//import java.awt.Container;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class TryApplet extends JApplet {
  @Override
  public void init() {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
              createAppletGUI();
            }
        });
    } catch (Exception e) {
        System.err.println("Applet GUI creation failed.");
    }
  }

  private void createAppletGUI() {
    // Set layout for content pane
    getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 30)); // Set layout

    JButton button;                                                       // Stores a button
    Font[] fonts = { new Font("Serif", Font.ITALIC, 16),                  // Two fonts
                     new Font("Dialog", Font.PLAIN, 14)
                   };

    BevelBorder edge = new BevelBorder(BevelBorder.RAISED);               // Bevelled border

    // Add the buttons using alternate fonts
    for(int i = 1 ; i <= 6 ; ++i) {
      add(button = new JButton("Press " + i));                            // Add the button
      button.setFont(fonts[i%2]);                                         // One of our own fonts
      button.setBorder(edge);                                             // Set the button border
    }
  }
}