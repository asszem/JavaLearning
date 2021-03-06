package HortonExercises.Ch16.Ex123_Andras;

// Generates transactions for clerks
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class TransactionSource implements Callable<int[][]> {

	private TransactionType type;
	private int maxTrans;
	private Vector<Account> accounts;
	private Vector<Clerk> clerks;
	private Vector<Supervisor> supervisors; // available supervisors
	private int[][] accountTotalCreditDebit; // [][0]=CREDIT, [][1]=DEBIT
	private final int CREDIT_INDEX = 0;
	private final int DEBIT_INDEX = 1;
	public static final Logger logger = Logger.getLogger(TransactionSource.class.getName());

	public TransactionSource(int maxTrans, Vector<Account> accounts, Vector<Clerk> clerks,
			Vector<Supervisor> supervisors) {
		this.maxTrans = maxTrans;
		this.accounts = accounts;
		this.clerks = clerks;
		this.supervisors = supervisors;
		accountTotalCreditDebit = new int[accounts.size()][2];
		logger.info("Transaction Source constructor completed.");
	}

	// The source of transactions
	public int[][] call() {
		logger.info("Transaction Source thread started.");
		// Create transactions randomly distributed between the accounts
		Random rand = new Random();
		Transaction transaction = null;                                    // Stores a transaction
		int amount = 0;                                                    // Stores an amount of money
		int accountSelect = 0;                                             // Selects an account
		int nextSupervisor = 0;
		for (int i = 1; i <= maxTrans; ++i) {
			// Generate a random account index for operation
			accountSelect = rand.nextInt(accounts.size());
			amount = 50 + rand.nextInt(26);                                  // Generate amount of $50 to $75
			// if (rand.nextBoolean()) {
			//// System.out.println("Creating a Credit type transaction source");
			// this.type = TransactionType.CREDIT;
			// } else {
			//// System.out.println("Creating a Debit type transaction source");
			// this.type = TransactionType.DEBIT;
			// }
			this.type = rand.nextBoolean() ? TransactionType.CREDIT : TransactionType.DEBIT;

			//Uncommenting this will mess up the results. Find out why
//			try {
//				Thread.sleep(25);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
			//Use the synchronized factory method for creating new transactions so that each will have unique ID
				transaction=Transaction.createTransaction(accounts.get(accountSelect), type, amount);
				logger.fine("New transaction created. ID: " + transaction.getTransactionId());

			// Keep track of total credit [0] and debit [1] for each account
			if (this.type == TransactionType.CREDIT) {
				accountTotalCreditDebit[accountSelect][CREDIT_INDEX] += amount;  // Keep total tally for credits/account
			}
			if (this.type == TransactionType.DEBIT) {
				accountTotalCreditDebit[accountSelect][DEBIT_INDEX] += amount;  // Keep total tally for debits/account
			}

			// Assign the transaction to the next available supervisor
			// There is no limit how much transaction a supervisor can accept. Clerks have limits
			supervisors.get(nextSupervisor).assignTransactionToSupervisor(transaction);
			logger.fine("Transaction " + transaction.getTransactionId() + " assigned to supervisor:"
					+ supervisors.get(nextSupervisor).supervisorID);
			if (++nextSupervisor >= supervisors.size()) {
				nextSupervisor = 0;
			}

			//The running Supervisor thread will assign the transaction to the Clerks

			if (Thread.interrupted()) {
				System.out.println("Interrupt flag for " + type + " transaction source set. Terminating.");
				return accountTotalCreditDebit;
			}
		}
		return accountTotalCreditDebit; //Returns the total credit/debit of all created transaction objects
	}

}