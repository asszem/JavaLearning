package Threads.Practice.Bank.BankOperation_5_UsingExecutors;

import java.util.logging.Level;
import java.util.logging.Logger;

// Define the bank

public class Bank {

	private static final Logger BankClassLogger = Logger.getLogger(Bank.class.getName());
	
	//Inicialize the file handler to handle fine messages
	{
		BankClassLogger.addHandler(BankLogger.BankClassFineHandler);
		BankClassLogger.setLevel(Level.FINER);
	}

	// Perform a transaction
	public void bankExecuteTransaction(Transaction transaction) {
		BankClassLogger.fine("Execute transaction method started");
		synchronized (transaction.getAccount()) {
			int balance = 0;
			switch (transaction.getTransactionType()) {
			case CREDIT:
				BankClassLogger.fine("CREDIT transaction started for account " + transaction.getAccount());
				System.out
						.println("Start credit of " + transaction.getAccount() + " amount: " + transaction.getAmount());

				// Get current balance
				balance = transaction.getAccount().getBalance();
				BankClassLogger.fine("Account balance read before crediting: " + balance);
				// Credits require a lot of checks...
				try {
					Thread.sleep(100);

				} catch (InterruptedException e) {
					System.out.println(e);
				}
				balance += transaction.getAmount();                        // Increment the balance
				BankClassLogger
						.info("Balance credit to be updated to "+ balance
								+ " ("+transaction.getAccount().getBalance()
								+ " + "
								+ transaction.getAmount()
								+ " = " + balance
								+ ")");
				transaction.getAccount().setBalance(balance);              // Restore account balance
				BankClassLogger.fine("Account balance actually updated (credited) to:"
						+ transaction.getAccount().getBalance());
				BankClassLogger.fine("Account" + transaction.getAccount() + " credit operation completed");
				System.out
						.println("  End credit of " + transaction.getAccount() + " amount: " + transaction.getAmount());
				break;
			case DEBIT:
				BankClassLogger.fine("DEBIT transaction started for account " + transaction.getAccount());
				System.out
						.println("Start debit of " + transaction.getAccount() + " amount: " + transaction.getAmount());

				// Get current balance
				balance = transaction.getAccount().getBalance();
				BankClassLogger.fine("Account balance read before debiting: " + balance);
				// Debits require even more checks...
				try {
					Thread.sleep(150);

				} catch (InterruptedException e) {
					System.out.println(e);
				}
				balance -= transaction.getAmount();                        // Decrement the balance...
				BankClassLogger
						.info("Balance debit to be updated to "+ balance
								+ " ("+transaction.getAccount().getBalance()
								+ " + "
								+ transaction.getAmount()
								+ " = " + balance
								+ ")");
				transaction.getAccount().setBalance(balance);              // Restore account balance
				BankClassLogger.fine("Account balance actually updated (debited) to:" + transaction.getAccount().getBalance());
				BankClassLogger.fine("Account" + transaction.getAccount() + " debit operation completed.");
				System.out
						.println("  End debit of " + transaction.getAccount() + " amount: " + transaction.getAmount());
				break;

			default:                                                       // We should never get here
				System.out.println("Invalid transaction");
				System.exit(1);
			}
		}
	}
}
