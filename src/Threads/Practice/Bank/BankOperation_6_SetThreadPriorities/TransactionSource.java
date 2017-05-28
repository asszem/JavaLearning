package Threads.Practice.Bank.BankOperation_6_SetThreadPriorities;

import java.io.IOException;
// Generates transactions for clerks
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionSource implements Callable<int[]> {

	public static final Logger logger = Logger.getLogger(TransactionSource.class.getName());
	private TransactionType type;
	private int maxTrans;
	private Vector<Account> accounts;
	private Vector<Clerk> clerks;
	private int priority;
	private int[] totals;

	//Constructor for creating a new transaction
	public TransactionSource(TransactionType type, int maxTrans, Vector<Account> accounts, Vector<Clerk> clerks,
			int priority) {
		this.type = type;
		this.maxTrans = maxTrans;
		this.accounts = accounts;
		this.clerks = clerks;
		this.priority = priority;
		totals = new int[accounts.size()]; // this is what the call() returns for each account
	}

	// The source of transactions
	public int[] call() {
		logger.info(String.format("%s type transaction thread started. Priority=%d", type, priority));
		Thread.currentThread().setPriority(priority);                      // Set the priority for this thread
		// Create transactions randomly distributed between the accounts
		Random rand = new Random();
		Transaction transaction = null;                                    // Stores a transaction
		int amount = 0;                                                    // Stores an amount of money
		int accountSelect = 0;                                             // Selects an account
		boolean done = false;
		for (int i = 1; i <= maxTrans; ++i) {
			// Generate a random account index for operation
			accountSelect = rand.nextInt(accounts.size());
			amount = 50 + rand.nextInt(26);                                   // Generate amount of $50 to $75
			transaction = new Transaction(accounts.get(accountSelect),        // Account
					type,                               // Transaction type
					amount,								// of amount
					i);                           		// transactionNumber used to generate the ID
			totals[accountSelect] += amount;            // Keep total SUM for account (either debit or credit)
			done = false;
			logger.fine(String.format("Transaction #%d created. ID:%s (Type: %s, Account:%s, Amount:%d)"
					,i, transaction.getTransactionID(), type,
					accounts.get(accountSelect), amount));
			while (true) {
				// Find a clerk to do the transaction
				for (Clerk clerk : clerks) {
					if (done = clerk.assignTransactionToClerk(transaction))
						break;
				}
				if (done) {
					break;
				}

				// No clerk was free so wait a while
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					System.out.println(" TransactionSource\n" + e);
					return totals;
				}
			}
			if (Thread.interrupted()) {
				System.out.println("Interrupt flag for " + type + " transaction source set. Terminating.");
				return totals;
			}
		}
		logger.info(String.format("%s type transaction thread completed. Totals created:%s ", type, java.util.Arrays.toString(totals)));
		return totals;
	}

}