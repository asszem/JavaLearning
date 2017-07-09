/*
 * Exercise 2 - Requirement
 * Replace the action listener for the selection buttons in the Lottery applet
 *  with a mouse listener and use the mousePressed() method to update the selection with a new value.
 * 
 * */
package HortonExercises.Ch18.Ex23;

// Applet to generate lottery entries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;                                               // For random number generator

@SuppressWarnings("serial")
public class Lottery extends JApplet {

	// Variables
	final static int NUMBER_COUNT = 6;                                   // Number of lucky numbers
	final static int MIN_VALUE = 1;                                      // Minimum in range
	final static int MAX_VALUE = 49;                                     // Maximum in range
	final static int[] VALUES = new int[MAX_VALUE - MIN_VALUE + 1];      // Array of possible VALUES. Array is FINAL

	// A static initialization block to set up the values array
	static {                                                             // Initialize array
		for (int i = 0; i < VALUES.length; ++i)
			VALUES[i] = i + MIN_VALUE;									 // from 1 (0+Min_Value=1) to 49 (VALUES.length=49)
	}

	// An array of custom buttons for the selected numbers
	private NumberButton[] luckyNumberButtons = new NumberButton[NUMBER_COUNT];
	final public static int PICK_LUCKY_NUMBERS = 1;                      // Select button ID
	final public static int COLOR = 2;                            	     // Color button ID

	// swap colors
	Color flipColor = new Color(Color.YELLOW.getRGB() ^ Color.RED.getRGB());
	Color startColor = Color.YELLOW;                              	       // start color

	private static Random choice = new Random();                  	       // Random number generator

	// Generate n=NUMBER_COUNT random selections from the VALUES array in the values length range
	private static int[] getNumbers() {
		int[] numbers = new int[NUMBER_COUNT];                              // Store for the numbers to be returned
		int candidate = 0;                                                  // Stores a candidate selection
		for (int i = 0; i < NUMBER_COUNT; ++i) {                            // Loop to find the selections

			search:
			// Loop to find a new selection different from any found so far
			while (true) {
				candidate = VALUES[choice.nextInt(VALUES.length)];			// Get a random number from the VALUES array
				// Check from i=0 up until the current buttonValue of i. When i=0, this is skipped
				for (int j = 0; j < i; ++j) {                               // Check against existing selections
					if (candidate == numbers[j]) {                          // If it is the same
						continue search;                                    // get another random selection
					}
				}
				// This can be reached only if the for loop above executes without any match
				numbers[i] = candidate;                                     // Store the selection in numbers array
				break;                                                      // and go to find the next
			}
		}
		return numbers;                                                    // Return an array of NUMBER_COUNT different random numbers
	}

	private static int[] sortNumbersAscending(int[] input) {
		// int[] sorted=new int[input.length];
		// sorted=java.util.Arrays.sort(input);
		java.util.Arrays.sort(input);
		return input;
	}

	// Initialize the applet
	@Override
	public void init() {
		SwingUtilities.invokeLater(                                        // Create interface components
				new Runnable() {                                                 // on the event dispatching thread

					public void run() {
						createGUI();
					}
				});
	}

	// Create User Interface for applet
	public void createGUI() {
		// Set up the selection buttons
		Container content = getContentPane();								// Content pane of the whole applet

		// The layout will have two Panes (NumberButton buttons on top panel and control buttons on bottom panel)
		// The panes will be arranged in one column, so the gridlayout setup is 0 row 1 column
		content.setLayout(new GridLayout(0, 1));                            // Set the layout for the applet

		// Set up the panel to hold the lucky number buttons. No layout specified. Flow layout is default
		JPanel buttonPane = new JPanel();                                  // Add the pane containing numbers

		// Create border using BorderFactory
		buttonPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.cyan, Color.blue),
				"Every One a Winner!"));
		/*
		 * A big advantage of using the BorderFactory methods rather than creating border objects from the border class constructors directly is that border objects are shared where possible, so you
		 * can use a particular border in various places in your code and only one object is created.
		 */

		int[] choices = getNumbers();                                      // Get initial set of numbers

		choices = sortNumbersAscending(choices);

		for (int i = 0; i < NUMBER_COUNT; ++i) {
			// Create a new NumberButton object with a random number on it picked from choices array
			// NumberButton object extends to JButton and implements ActionListener
			// There will be n=NUMBER_COUNT number of NumberButton objects created
			luckyNumberButtons[i] = new NumberButton(choices[i]);

			// Each NumberButton object has it's own MOUSE listener assigned
			// To handle PRESS
			luckyNumberButtons[i].addMouseListener(luckyNumberButtons[i]); // Button is it's own listener

			// To change the cursor on mouseover of Selection buttons
			luckyNumberButtons[i].addMouseListener(new MouseHandlerInner());
			buttonPane.add(luckyNumberButtons[i]);
		}
		content.add(buttonPane);										// Add buttonPane to the contentPane

		// Add the pane containing control buttons
		// FlowLayout arguments to make the components centered and the horizontal and vertical gaps to be 5 and 10 pixels
		JPanel controlPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));

		// Add the two control buttons

		// The button variable is temporary store for the reference to each button to setup border, size, actionlistener
		JButton button;                                                     // A button variable
		Dimension buttonSize = new Dimension(150, 20);                    	// Button size

		controlPane.add(button = new JButton("Generate Numbers!"));			// Adds the button to the ControlPane
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.addActionListener(new HandleControlButton(PICK_LUCKY_NUMBERS)); // Create the button object with ID=PICK_LUCKY_NUMBERS
		button.setPreferredSize(buttonSize);

		controlPane.add(button = new JButton("Change Color"));
		button.setBorder(BorderFactory.createRaisedBevelBorder());
		button.addActionListener(new HandleControlButton(COLOR));
		button.setPreferredSize(buttonSize);

		content.add(controlPane);											// Add controlPane to the content pane
	}

	// Class defining custom buttons showing lottery selection
	// Inner nested class, so it has access to all variables of parent class
	// Class extends to JButton
	// Class will extend to MouseAdapter and override the MousePressed method
	private class NumberButton extends JButton implements MouseListener {

		private int buttonValue;                                                 // Value for the selection button

		// Constructor to create a NumberButton type button object
		public NumberButton(int value) {
			super(Integer.toString(value));                                  // Call base constructor (JButton) and set the label
			this.buttonValue = value;                                        // Save the buttonValue for the instance
			setBackground(startColor);										 // Variable defined in Lottery class
			setBorder(BorderFactory.createRaisedBevelBorder());              // Add button border
			setPreferredSize(new Dimension(80, 20));
		}

		// Set the value of a specific button, without any other actions
		public void setValue(int value) {
			setText(Integer.toString(value));                                // Set buttonValue as the button label
			this.buttonValue = value;                                        // Save the buttonValue
		}

		public int[] getSortedNumbersArray() {
			int[] valuesSorted = new int[NUMBER_COUNT];
			for (int i = 0; i < NUMBER_COUNT; ++i) {
				valuesSorted[i] = luckyNumberButtons[i].buttonValue;
			}
			sortNumbersAscending(valuesSorted);
			return valuesSorted;
		}

		public void setAllButtonsWithUpdatedAndSortedValues(int[] sortedValues) {
			for (int i = 0; i < NUMBER_COUNT; ++i) {
				luckyNumberButtons[i].setValue(sortedValues[i]);
			}
		}

		// Check the buttonValue for the selection
		boolean hasValue(int possible) {
			return buttonValue == possible;                                  // Return true if equals current buttonValue
		}

		// Check the current choices
		boolean isButtonValueAlreadyUsed(int possible) {
			for (int i = 0; i < NUMBER_COUNT; ++i) {                        // For each button
				// if (luckyNumberButtons[i].hasValue(possible)) { // check against possible
				// return true; // Return true for any = match
				// }
				if (luckyNumberButtons[i].buttonValue == possible) {
					return true;
				}
			}
			return false;                                                    // Otherwise return false
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			e.getComponent().setCursor(crossCursor);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Change this selection to a new selection - change the number on the button if the button is pressed
			int candidate = 0;
			while (true) {                                                    // Loop to find a different selection
				candidate = VALUES[choice.nextInt(VALUES.length)];			  // Pick a random buttonValue from choices pool
				if (!isButtonValueAlreadyUsed(candidate)) {                         // If it is different (from any other candidates)
					break;                                                    // end the loop
				}
			}
			// This is reached when new candidate did not match any of the existing button values
			setValue(candidate);     // We have one so set the button buttonValue

			// Get an array of current button values and sort it ascending
			int[] sortedValues = getSortedNumbersArray();

			// Use the sorted array to update the buttons so they will be in ascending order
			setAllButtonsWithUpdatedAndSortedValues(sortedValues);

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			e.getComponent().setCursor(defaultCursor);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		Cursor crossCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	}

	// Class defining a handler for a control button. Does not extend to JButton, as this class is only for handling the event
	private class HandleControlButton implements ActionListener {

		private int buttonID;												// To decide which of the two buttons
		// The listeners for each of the control buttons are of the same class type, so the listener object needs some way to
		// determine which button originated a particular event.

		// Constructor
		public HandleControlButton(int buttonID) {
			this.buttonID = buttonID;                                        // Store the button ID
		}

		// Handle button click
		public void actionPerformed(ActionEvent e) {
			System.out.println("Handle button pressed");
			switch (buttonID) {
			case PICK_LUCKY_NUMBERS:
				int[] numbers = getNumbers();                                // Get maxCount random numbers
				numbers = sortNumbersAscending(numbers);
				for (int i = 0; i < NUMBER_COUNT; ++i) {
					luckyNumberButtons[i].setValue(numbers[i]);                    // Set the button VALUES to the new numbers
				}
				break;
			case COLOR:
				// The new color will be based on the flip color and the actual color
				// get the flipColor RGB value and XOR with the actual background color of the buttons
				Color color = new Color(flipColor.getRGB() ^ luckyNumberButtons[0].getBackground().getRGB());
				for (int i = 0; i < NUMBER_COUNT; ++i)
					luckyNumberButtons[i].setBackground(color);                      // Set the button colors to the new color
				break;
			}
		}

	}

	private class MouseHandlerInner extends MouseAdapter {

		Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
		Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

		// Handle mouse entering the selection button
		public void mouseEntered(MouseEvent e) {
			e.getComponent().setCursor(handCursor);                            // Switch to hand cursor
		}

		// Handle mouse exiting the selection button
		public void mouseExited(MouseEvent e) {
			e.getComponent().setCursor(defaultCursor);                         // Change to default cursor
		}

	}

}
