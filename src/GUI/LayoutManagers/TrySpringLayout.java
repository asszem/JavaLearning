package GUI.LayoutManagers;

import javax.swing.*;
import java.awt.*;

public class TrySpringLayout {

  public static void createWindow(){
    JFrame aWindow = new JFrame("This is the Window Title");
    Toolkit theKit = aWindow.getToolkit();                             // Get the window toolkit
    Dimension wndSize = theKit.getScreenSize();                        // Get screen size

    // Set the position to screen center & size to half screen size
    aWindow.setSize(wndSize.width/2, wndSize.height/2);                // Set window size
    aWindow.setLocationRelativeTo(null);                               // Center window
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    SpringLayout layout = new SpringLayout();                          // Create a layout manager
    Container contentPane = aWindow.getContentPane();                      // Get the content pane
    contentPane.setLayout(layout);                                         // Set the container layout mgr

    JButton[] buttons = new JButton[6];                                // Array to store buttons
    SpringLayout.Constraints springLayoutConstraints = null;
    for(int i = 0; i < buttons.length; ++i) {
      buttons[i] = new JButton("Button " + (i+1));
      contentPane.add(buttons[i]);                                         // Add a Button to content pane
   }

    Spring xSpring = Spring.constant(5,15,25);                         // x constraint for first button
    Spring ySpring = Spring.constant(10,30, 50);                       // y constraint for first button

    // Connect x,y for first button to left and top of container by springs
    springLayoutConstraints = layout.getConstraints(buttons[0]);
    springLayoutConstraints.setX(xSpring);
    springLayoutConstraints.setY(ySpring);

    // Hook buttons together with springs
    for(int i = 1 ; i< buttons.length ; ++i) {
      springLayoutConstraints = layout.getConstraints(buttons[i]);
      layout.putConstraint(SpringLayout.WEST, buttons[i],						//Link West of Comp1 to East of Comp2
                             xSpring,SpringLayout.EAST, buttons[i-1]);
      layout.putConstraint(SpringLayout.NORTH, buttons[i],
                             ySpring,SpringLayout.SOUTH, buttons[i-1]);
    }
    // Uncomment the following code to constrain the content pane
    SpringLayout.Constraints constraint = layout.getConstraints(contentPane);
    
    //Set a constraint for the right side (EAST) of the contentPane of of current+15
    constraint.setConstraint(SpringLayout.EAST,
                             Spring.sum(springLayoutConstraints.getConstraint(SpringLayout.EAST),
                             Spring.constant(15)));
    constraint.setConstraint(SpringLayout.SOUTH,
                             Spring.sum(springLayoutConstraints.getConstraint(SpringLayout.SOUTH),
                             Spring.constant(10)));
    aWindow.pack();
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