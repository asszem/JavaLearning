package Threads.Practice.Bank.BankOperation_6_SetThreadPriorities;

import java.util.List;
import java.util.logging.Logger;
import java.util.Collections;
import java.util.LinkedList;

public class Clerk implements Runnable {

	int ID;
	int priority;                                                        // This thread's priority
	private Bank theBank;
	private List<Transaction> inTray =                                   // The in-tray holding transactions
			Collections.synchronizedList(new LinkedList<Transaction>());
	private int maxTransactions = 8;                                     // Maximum transactions in the in-tray

	public static final Logger logger = Logger.getLogger(Clerk.class.getName());

	// Constructor
	public Clerk(int ID, Bank theBank, int priority) {
		this.ID = ID;
		this.theBank = theBank;                                            // Who the clerk works for
		this.priority = priority;
	}

	// Receive a transaction
	synchronized public boolean assignTransactionToClerk(Transaction transaction) {
		if (inTray.size() >= maxTransactions) {
			return false;
		}
		logger.fine(String.format("Transaction %s assigned to clerk %d", transaction.getTransactionID(), ID));
		inTray.add(transaction);                                           // Add transaction to the list
		return true;
	}

	// The working clerk...
	public void run() {
		Thread.currentThread().setPriority(priority);                      // Set the priority for this thread
		logger.info(String.format("Clerk ID%d started running with priority:%d", ID, priority));
		while (true) {
			while (inTray.size() == 0) {                                      // No transaction waiting?
				try {
					logger.fine(String.format("Clerk %d went to sleep", ID));
					Thread.sleep(200);                                           // then take a break
					if (inTray.size() != 0) {
						break;
					} else {
						return;
					}
				} catch (InterruptedException e) {
					System.out.println("Clerk " + ID + "\n" + e);
					return;
				}
			}
			theBank.doTransaction(inTray.remove(0), ID);  						// assign the top transaction on the List
			if (Thread.interrupted()) {
				System.out.println("Interrupt flag for Clerk " + ID + " set. Terminating.");
				return;
			}
		}
	}

}
