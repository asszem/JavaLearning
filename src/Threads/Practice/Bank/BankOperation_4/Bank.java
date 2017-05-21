package Threads.Practice.Bank.BankOperation_4;
// Define the bank

// The synchronized CREDIT and DEBIT code blocks prevent to perform credit/depit operations on the same ACCOUNT

public class Bank {

	// Perform a transaction
	public void executeTransaction(Transaction transaction) {
		switch (transaction.getTransactionType()) {
		case CREDIT:
			synchronized (transaction.getAccount()) { 			// Start synchronized block on Object ACCOUNT
				System.out
						.println("Start credit of " + transaction.getAccount() + " amount: " + transaction.getAmount());

				// Get current balance
				int balance = transaction.getAccount().getBalance();

				// Credits require a lot of checks...
				// This sleep was only included to give a chance for other threads to run simultaneously
				try {
					Thread.sleep(100);

				} catch (InterruptedException e) {
					System.out.println(e);
				}
				balance += transaction.getAmount();                          // Increment the balance
				// the balance needs to be set inside the synchronized block
				transaction.getAccount().setBalance(balance);                // Restore account balance
				System.out
						.println("  End credit of " + transaction.getAccount() + " amount: " + transaction.getAmount());
				break;
			} // End CREDIT synchronized block
		case DEBIT:
			// The DEBIT block needs to be synchronized as well.
			synchronized (transaction.getAccount()) {
				System.out
						.println("Start debit of " + transaction.getAccount() + " amount: " + transaction.getAmount());

				// Get current balance
				int balance = transaction.getAccount().getBalance();

				// Debits require even more checks...
				try {
					Thread.sleep(150);

				} catch (InterruptedException e) {
					System.out.println(e);
				}
				balance -= transaction.getAmount();                          // Decrement the balance...
				transaction.getAccount().setBalance(balance);                // Restore account balance

				System.out
						.println("  End debit of " + transaction.getAccount() + " amount: " + transaction.getAmount());
				break;
			} // end DEBIT synchronized block
				 // this is where the actual balance setting used to be, but it needed to be moved to the sync blocks
				 // otherwise it would have been possible that a credit operation completes
				 // but before reaching here, a new debit operation or another credit operation is called
				 // and then an incorrect balance would have been set.

		default:                                                         // We should never get here
			System.out.println("Invalid transaction");
			System.exit(1);
		}
	}
}
