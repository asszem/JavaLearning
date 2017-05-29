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

	public void assignTransactionToClerk(Transaction transaction) {

		boolean done = false;
		while (true) { // Run as long as all transactions have been reported as completed
			// The supervisor will find the next clerk he can assign the transaction to

			for (Clerk clerk : clerksUnderThisSupervisor) {
				if (done = clerk.assignTransactionToClerk(transaction))
					logger.fine(String.format("Transaction %s assigned to Clerk %s", transaction.getTransactionId(), clerk.ID));
					break; // break from for
			}
			if (done) {
				break; // Break from while
			}

			// No clerk was free so wait a while
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println(" TransactionSource\n" + e);
			}
		}
	}

	@Override
	public Integer call() {
		logger.fine("Supervisor thread started");
		Random rnd = new Random();
		Integer timeDelay = rnd.nextInt(600) + 100;		// Random number between 100 and 500
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			logger.fine("Transactions to be assigned by supervisor:" + transactionsToAssign.size());
			for (int i = 0; i < transactionsToAssign.size(); i++) {
				// logger.fine("Transaction " + transaction.getTransactionId() + " assigned to " + supervisorID);
				assignTransactionToClerk(transactionsToAssign.get(i));	// Transaction assigned to next available clerk
			}
			return timeDelay;
		}
	}

}
