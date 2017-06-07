package GUI.Applets;

import javax.swing.*;
import java.applet.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;                                     // Class defining events
import java.awt.event.ActionListener;                                  // Interface for receiving events

@SuppressWarnings("serial")
public class TryCardLayout extends JApplet implements ActionListener {
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
    Container content = getContentPane();
    content.setLayout(card);                                           // Set card as the layout mgr
    JButton button;                                                    // Stores a button
    for(int i = 1 ; i <= 6 ; ++i) {
      content.add(button = new JButton(" Press " + i), "CardIDString" + i);    // Add a button
      button.addActionListener(this);                                  // Add listener for button
    }
    card.show(content, "CardIDString5");                               // Show the 5th card to start
  }

  // Handle button events
  public void actionPerformed(ActionEvent e) {
	  System.out.println("Event: " +e );
//	  JLabel label = new JLabel ("This is label");
//	  getContentPane().add(label);
    card.next(getContentPane());                                       // Switch to the next card
  }

  private CardLayout card = new CardLayout(50,50);                     // The layout manager
}