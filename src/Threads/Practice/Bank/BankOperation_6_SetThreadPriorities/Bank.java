package Threads.Practice.Bank.BankOperation_6_SetThreadPriorities;

import java.util.logging.Level;
import java.util.logging.Logger;

// Define the bank

public class Bank {
	private static final Logger logger = Logger.getLogger(Bank.class.getName());

	// Inicialize logger so when the assingTransactionToClerk method is called, the logger setup will be already completed
	{
		String handlerFile = "J:/Logs/BankOpsLogs/ThreadPrioritiesLogfiles/BankOperations.log";
		LoggerSetup.addFileHandler(handlerFile, Level.FINE, logger);
	}

  // Perform a transaction
  public void doTransaction(Transaction transaction, int clerkID) {
    synchronized(transaction.getAccount()) {
      int balance = 0;
      switch(transaction.getTransactionType()) {
        case CREDIT:
          System.out.println("Clerk " + clerkID + ": Start credit of " +
                  transaction.getAccount() + " amount: " +
                  transaction.getAmount());

          // Get current balance
          balance = transaction.getAccount().getBalance();
          int balanceBefore=balance;
          // Credits require a lot of checks...
          try {
            Thread.sleep(200);

          } catch(InterruptedException e) {
            System.out.println(e);
          }
          balance += transaction.getAmount();                          // Increment the balance
          transaction.getAccount().setBalance(balance);                // Restore account balance
          System.out.println("  End credit of " +
                  transaction.getAccount() + " amount: " +
                  transaction.getAmount());
          logger.fine(String.format("Transaction ID %s, initial balance=%d, amount=%d, updated balance=%d"
        		  ,transaction.getTransactionID(), 
        		  balanceBefore, transaction.getAmount(), transaction.getAccount().getBalance()));
          break;

        case DEBIT:
          System.out.println("Clerk " + clerkID + ": Start debit of " +
                  transaction.getAccount() + " amount: " +
                  transaction.getAmount());

          // Get current balance
          balance = transaction.getAccount().getBalance();

          // Debits require even more checks...
          try {
            Thread.sleep(500);
          } catch(InterruptedException e) {
            System.out.println(e);
          }
          balance -= transaction.getAmount();                          // Increment the balance...
          transaction.getAccount().setBalance(balance);                // Restore account balance

          System.out.println("  End debit of " +
                  transaction.getAccount() + " amount: " +
                  transaction.getAmount());
          break;

        default:                                                       // We should never get here
          System.out.println("Invalid transaction");
          System.exit(1);
      }
    }
  }
}
