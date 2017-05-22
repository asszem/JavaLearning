package Threads.Practice.Bank.BankOperation_5_UsingExecutors;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

//Clerks will run as separate thread but don't need to return value, so they can be Runnable
public class Clerk implements Runnable {

	int ID;
	private Bank theBank;
	
	//synchronized list makes it thread safe. all methods iterating over the list must be synchronized
	private List<Transaction> inTray =                                   // The in-tray holding transactions
			Collections.synchronizedList(new LinkedList<Transaction>()); // LinkedList holding Transaction objects
	private int maxTransactions = 8;                                     // Maximum transactions in the in-tray

	// Constructor
	public Clerk(int ID, Bank theBank) {
		this.ID = ID;
		this.theBank = theBank;                                            // Who the clerk works for
	}

	// Receive a transaction and report true if accepted
	synchronized public boolean clerkAcceptsTransaction(Transaction transaction) {
		if (inTray.size() >= maxTransactions)
			return false;
		inTray.add(transaction);                                           // Add transaction to the list
		return true;
	}

	// The working clerk... - this will be executed, when the Clerk thread starts
	public void run() {
		while (true) {	//infinite loop to keep the clerk working
			while (inTray.size() == 0) {                                      // No transaction waiting?
				try {
					Thread.sleep(200);                                           // then take a break
					if (inTray.size() != 0) { //break the while loop if job assigned
						break;
					} else {
						return; //return why? where
					}
				} catch (InterruptedException e) {
					System.out.println("Clerk " + ID + "\n" + e);
					return;
				}
			} //end of inner while checking empty tray
			theBank.bankExecuteTransaction(inTray.remove(0)); //when a transaction is done by the bank, remove it from tray
			if (Thread.interrupted()) {
				System.out.println("Interrupt flag for Clerk " + ID + " set. Terminating.");
				return;
			} //end if
		} //end outer while - the cycle that keeps Clerk working
	} //end run()

} //end class
