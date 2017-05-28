package HortonExercises.Ch16.Ex123_Andras;

// Generates transactions for clerks
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;

public class TransactionSource implements Callable<int[][]> {

	private TransactionType type;
	private int maxTrans;
	private Vector<Account> accounts;
	private Vector<Clerk> clerks;
	private int[][] accountTotalCreditDebit; // [][0]=CREDIT, [][1]=DEBIT
	private final int CREDIT_INDEX = 0;
	private final int DEBIT_INDEX = 1;

	public TransactionSource(int maxTrans, Vector<Account> accounts, Vector<Clerk> clerks) {
		this.maxTrans = maxTrans;
		this.accounts = accounts;
		this.clerks = clerks;
		accountTotalCreditDebit = new int[accounts.size()][2];
	}

	// The source of transactions
	public int[][] call() {
		// Create transactions randomly distributed between the accounts
		Random rand = new Random();
		Transaction transaction = null;                                    // Stores a transaction
		int amount = 0;                                                    // Stores an amount of money
		int accountSelect = 0;                                                    // Selects an account
		boolean done = false;
		for (int i = 1; i <= maxTrans; ++i) {
			// Generate a random account index for operation
			accountSelect = rand.nextInt(accounts.size());
			amount = 50 + rand.nextInt(26);                                  // Generate amount of $50 to $75
//			if (rand.nextBoolean()) {
////				System.out.println("Creating a Credit type transaction source");
//				this.type = TransactionType.CREDIT;
//			} else {
////				System.out.println("Creating a Debit type transaction source");
//				this.type = TransactionType.DEBIT;
//			}
			this.type=rand.nextBoolean()?TransactionType.CREDIT:TransactionType.DEBIT;
			transaction = new Transaction(accounts.get(accountSelect),              // Account
					type,                              						 // Transaction type
					amount);                          						 // of amount
			// Keep track of total credit [0] and debit [1] for each account
			if (this.type == TransactionType.CREDIT) {
				accountTotalCreditDebit[accountSelect][CREDIT_INDEX] += amount;  // Keep total tally for credits/account
			}
			if (this.type == TransactionType.DEBIT) {
				accountTotalCreditDebit[accountSelect][DEBIT_INDEX] += amount;  // Keep total tally for debits/account
			}
			done = false;
			while (true) {
				// Find a clerk to do the transaction
				for (Clerk clerk : clerks) {
					if (done = clerk.doTransaction(transaction))
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
					return accountTotalCreditDebit;
				}
			}
			if (Thread.interrupted()) {
				System.out.println("Interrupt flag for " + type + " transaction source set. Terminating.");
				return accountTotalCreditDebit;
			}
		}
		return accountTotalCreditDebit;
	}

}