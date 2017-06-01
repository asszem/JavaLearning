/*
 * The supervisor threads should receive transactions from transaction sources 
 * and pass them to the clerks they supervise. 
 *
 * The supervisors' work should result in a variable time delay
 * in transferring transaction to the clerks of between 100 and 500 milliseconds.
 * */
package HortonExercises.Ch16.Ex123_Andras;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class Supervisor implements Callable<Integer> {

	public String supervisorID;
	public Vector<Clerk> clerksUnderThisSupervisor = new Vector<Clerk>();
	public Vector<Transaction> transactionsToAssign = new Vector<>();
	public static final Logger logger = Logger.getLogger(Supervisor.class.getName());

	// Constructor
	public Supervisor(String supervisorID) {
		this.supervisorID = supervisorID;
	}

	public void assignTransactionToSupervisor(Transaction transaction) {
		logger.fine("Transaction " + transaction.getTransactionId() + " assigned to " + supervisorID);
		transactionsToAssign.add(transaction);
	}

	// If a clerk is refusing a transaction, because her plate is full, try other available clerks
	public void assignTransactionToClerk(Transaction transaction) {

		boolean isTransactionAssignedToAClerk = false;
		while (true) { // Run as long as all transactions have been reported as completed
			// The supervisor will find the next clerk he can assign the transaction to

			for (Clerk clerk : clerksUnderThisSupervisor) {
				// Return true if transaction is accepted by a clerk, or false, if not
				if (isTransactionAssignedToAClerk = clerk.assignTransactionToClerk(transaction)) {
					// logger.fine(String.format("Transaction %s assigned to Clerk %s by %s and it is DONE.",
					// transaction.getTransactionId(), clerk.ID, supervisorID));
					break;
				}// break from for loop, we have found a clerk who has accepted the transaction
				logger.info("Clerk " + clerk.ID + " did not accept transaction. Let's try the next one");
			}
			// If this point is reached, a clerk must have accepted the transaction
			if (isTransactionAssignedToAClerk) {
				break; // Break from while
			}

			// If this point is reached, no clerk accepted the transaction
			// No clerk was free so the Supervisor thread will wait a while and try again with all clerks
			long sleepTime = 10;
			logger.info("No available clekrs. Let's sleep Supervisor thread for " + sleepTime);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.out.println(" TransactionSource\n" + e);
				logger.warning("Interrupted Exception in sleeping Supervisor in assignTransactionToClerk method");
			}
		}// End while
	} // End assign transaction to clerk

	@Override
	public Integer call() {
		logger.fine("Supervisor thread started");
		Random rnd = new Random();
		Integer timeDelay = rnd.nextInt(600) + 100;		// Random number between 100 and 500
		// TODO If sleep(50) is set it works, if timeDelay is used, it doesnt give proper result. Investigate why
		try {
			Thread.sleep(timeDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// This Supervisor thread will be running as long as all transactions have been assigned (the for loop finishes)
		while (true) {
			StringBuilder transactionsList = new StringBuilder("Total number of Transactions assigned to "
					+ supervisorID + ": " + transactionsToAssign.size() + "\n");
			for (Transaction transaction : transactionsToAssign) {
				transactionsList.append(transaction.getTransactionId() + "\n");
			}
			logger.fine(transactionsList.toString());
			for (int i = 0; i < transactionsToAssign.size(); i++) {
				logger.fine("Supervisor +" + supervisorID + " attempts to assign transaction "
						+ transactionsToAssign.get(i).getTransactionId());
				assignTransactionToClerk(transactionsToAssign.get(i));	// Transaction assigned to next available clerk
			}
			logger.info("Thread for " + supervisorID + " completed, terminating");
			return timeDelay;
		}
	}

}
