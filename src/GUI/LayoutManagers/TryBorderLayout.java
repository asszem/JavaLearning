package GUI.LayoutManagers;

import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.*;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class TryBorderLayout {

  public static void createWindow(){
    JFrame aWindow = new JFrame("This is the Window Title");
    Toolkit theKit = aWindow.getToolkit();                             // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();                        // Get screen size

    // Set the position to screen center & size to half screen size
    aWindow.setSize(wndSize.width/2, wndSize.height/2);                // Set window size
    aWindow.setLocationRelativeTo(null);                               // Center window
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    BorderLayout border = new BorderLayout(10, 10);                    // hGap, vGap
    int hGap = border.getHgap();
    int vGap = border.getVgap();
    border.setHgap(hGap);
    border.setVgap(vGap);

    Container content = aWindow.getContentPane();                      // Get the content pane
    content.setLayout(border);                                         // Set the container layout mgr
    
    //Set various borders
    EtchedBorder borderEtched = new EtchedBorder(EtchedBorder.RAISED);
    BevelBorder borderBevel = new BevelBorder(BevelBorder.RAISED); 

    // Now add five JButton components and set their borders
    JButton button;
    content.add(button = new JButton("EAST Border Button"), BorderLayout.EAST);
    button.setBorder(borderEtched);
    content.add(button = new JButton("WEST Border Button"), BorderLayout.WEST);
    button.setBorder(borderEtched);
    content.add(button = new JButton("NORTH Border Button"), BorderLayout.NORTH);
    button.setBorder(borderEtched);
    content.add(button = new JButton("SOUTH Border Button"), BorderLayout.SOUTH);
    button.setBorder(borderEtched);
    content.add(button = new JButton("CENTER Border Button"), BorderLayout.CENTER);
    button.setBorder(borderBevel);

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