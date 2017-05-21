package Threads.Practice.Bank.BankOperation_1;
// Define the bank

// The whole 'executeTransaction' method is synchronized.
// Problem with this is that this setup allows only one transaction execution at a time
// However in a bank, many transactions should be allowed to run simultaneously on DIFFERENT accounts
// The solution will be in BankOps4, synching Credit/Debit code blocks only and not the whole method

public class Bank {

	// Perform a transaction
	// If this method is synchronized, it will prevent the clerk objects to operate on the same account sametime
	synchronized public void executeTransaction(Transaction transaction) {
		int balance = transaction.getAccount().getBalance();               // Get current balance

		switch (transaction.getTransactionType()) {
		case CREDIT:
			// Credits require a lot of checks...
			try {
				Thread.sleep(100);

			} catch (InterruptedException e) {
				System.out.println(e);
			}
			balance += transaction.getAmount();                            // Increment the balance
			break;

		case DEBIT:
			// Debits require even more checks...
			try {
				Thread.sleep(150);

			} catch (InterruptedException e) {
				System.out.println(e);
			}
			balance -= transaction.getAmount();                            // Decrement the balance
			break;

		default:                                                         // We should never get here
			System.out.println("Invalid transaction");
			System.exit(1);
		}
		// The account balance is set after the debit/credit operations performed
		// and the new 'balance' value is determined
		transaction.getAccount().setBalance(balance);                      // Restore the account balance
	}
}
