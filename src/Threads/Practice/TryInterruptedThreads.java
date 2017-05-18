package Threads.Practice;

import java.io.IOException;

public class TryInterruptedThreads extends Thread {

	private String firstName;                                            // Store for first name
	private String secondName;                                           // Store for second name
	private long aWhile;                                                 // Delay in milliseconds

	public TryInterruptedThreads(String firstName, String secondName, long delay) {
		this.firstName = firstName;                                        // Store the first name
		this.secondName = secondName;                                      // Store the second name
		aWhile = delay;                                                    // Store the delay
		setDaemon(true);                                                   // Thread is daemon
	}

	public static void main(String[] args) {
		// Create three threads
		Thread first = new TryInterruptedThreads("First ", "Thread 1", 200L);		// Type is THREAD!
		Thread second = new TryInterruptedThreads("Second ", "Thread 2", 300L);
		Thread third = new TryInterruptedThreads("Third ", "Thread 3", 500L);

		System.out.println("Press Enter when you have had enough...\n");
		first.start();                                                     // Start the first thread
		second.start();                                                    // Start the second thread
		third.start();                                                     // Start the third thread

		try {
			System.in.read();                                                // Wait until Enter key pressed
			System.out.println("Enter pressed...\n");

			// Interrupt the threads
			first.interrupt();
			second.interrupt();
			third.interrupt();

		} catch (IOException e) {                                          // Handle IO exception
			System.err.println(e);                                           // Output the exception
		}
		System.out.println("Ending main()");
		return;
	}

	// Method where thread execution will start
	@Override
	public void run() {
		try {																	// sleep thorws exception
			while (true) {                                                      // Loop indefinitely...
				System.out.print(firstName);                                    // Output first name
				sleep(aWhile);                                                  // Wait aWhile msec.
				System.out.print(secondName + "\n");                        	// Output second name
			}
		} catch (InterruptedException e) {                                  	// Handle thread interruption
			//When an InterruptedException is thrown, the flag that registers the interrupt in the thread is cleared, so a
			//subsequent call to isInterrupted() or interrupted() returns false.
			System.out.println("Interrupted!" + firstName + secondName + e);    // Output the exception
		}
	}

}
