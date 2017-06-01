package HortonExercises.Ch16.Ex123_Andras;

import java.util.List;
import java.util.logging.Logger;
import java.util.Collections;
import java.util.LinkedList;

public class Clerk implements Runnable {

	int ID;
	private Bank theBank;
	private List<Transaction> inTray =                                   // The in-tray holding transactions
			Collections.synchronizedList(new LinkedList<Transaction>());
	private int maxTransactionsPerClerk = 2;                             // Maximum transactions in the in-tray
	private Supervisor mySupervisor;	// Reference to the supervisor object
	public static final Logger logger = Logger.getLogger(Clerk.class.getName());

	// Constructor
	public Clerk(int ID, Bank theBank, Supervisor clerksSupervisor) {
		this.ID = ID;
		this.theBank = theBank;                                            // Who the clerk works for
		this.mySupervisor = clerksSupervisor;				// The supervisor of the clerk
		logger.info("Clerk constructor completed. Clerk ID: " + ID + " Supervisor: " + mySupervisor.supervisorID);
	}

	// Receive a transaction. Synchronized, so TBV:same transaction can not be assigned to different clerks
	synchronized public boolean assignTransactionToClerk(Transaction transaction) {
		if (inTray.size() >= maxTransactionsPerClerk) {
			logger.fine("Clerk " + ID + " refused transaction " + transaction.getTransactionId() + " from supervisor"
					+ mySupervisor.supervisorID + " because inTray full");
			return false;
		}
		inTray.add(transaction);                                           // Add transaction to the list
		logger.fine("Clerk " + ID + " accepted transaction " + transaction.getTransactionId() + " from supervisor "
				+ mySupervisor.supervisorID);
		return true;
	}

	// The working clerk...
	public void run() {
		logger.info("The Clerk thread started");
		// Outer while
		while (true) {
			// Inner while
			while (inTray.size() == 0) {                                      // No transaction waiting?
				try {
					Thread.sleep(200);                                           // then take a break
					if (inTray.size() != 0) {
						logger.info("Clerk run inTray size changed from 0, breaking inner while");
						break; // Break from while, because there is now transaction in Clerk's inTray
					} else {
						//If the Clerks keep running even if empty tray, they will complete all transactions eventually
						logger.info("Clerk's tray is still empty, but keep running");
						
						//Uncomment to try
						//If the Clerk is terminated when tray is empty, no one will accept incoming transactions
//						logger.info("Clerk thread return called becasue inTray is empty after sleep");
//						return; // Terminating the run() method. 
					}
				} catch (InterruptedException e) {
					System.out.println("Clerk " + ID + "\n" + e);
					logger.info("Interrupt Exception for Clerk " + ID + " set. Terminating thread.");
					return;
				}
			}// end inner while
			//If this is reached, there is transaction in Tray for clerk. Let's do it, and remove from tray
			theBank.doTransaction(inTray.remove(0));
			if (Thread.interrupted()) {
				System.out.println("Interrupt flag for Clerk " + ID + " set. Terminating.");
				logger.info("Interrupt flag for Clerk " + ID + " set. Terminating thread.");
				return;
			}
		} // End outer while
	} //end run() for Clerk

}
