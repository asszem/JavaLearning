package Threads.Practice.Bank.BankOperation_6_SetThreadPriorities;

import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SettingThreadPriorities {

	private static final Logger mainLogger = Logger.getLogger(SettingThreadPriorities.class.getName());

	public static void main(String[] args) {
		LoggerSetup.startLogging();
		mainLogger.info("Main execution started");
		int[] initialBalance = { 500, 800 };                             // The initial account balances
		int[] totalCredits = new int[initialBalance.length];             // Two different cr totals
		int[] totalDebits = new int[initialBalance.length];              // Two different db totals
		int transactionCount = 5;                                       // Number of debits and of credits
		int clerkCount = 4;

		// Create the account, the bank, and the clerks...
		Bank theBank = new Bank();                                       // Create a bank
		Vector<Clerk> clerks = new Vector<Clerk>();                      // Stores the clerk
		Vector<Account> accounts = new Vector<Account>();                // Stores the accounts
		int priority = 0;
		for (int i = 0; i < clerkCount; ++i) {
			priority = i % 2 == 0 ? Thread.MIN_PRIORITY + (i + 1) : Thread.MAX_PRIORITY - (i + 1);
			if (priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY)
				priority = Thread.NORM_PRIORITY;
			clerks.add(new Clerk(i + 1, theBank, priority));                 // Create the clerks
			mainLogger.info(String.format("Clerk ID%d added. Priority: %d", i + 1, priority));
		}

		for (int i = 0; i < initialBalance.length; ++i) {
			accounts.add(new Account(i + 1, initialBalance[i]));             // Create accounts
			totalCredits[i] = totalDebits[i] = 0;
		}

		ExecutorService threadPool = Executors.newCachedThreadPool();

		// Create and start the transaction source threads
		Future<int[]> credits = threadPool.submit(new TransactionSource(TransactionType.CREDIT, transactionCount,
				accounts, clerks, Thread.MAX_PRIORITY - 1));
		Future<int[]> debits = threadPool.submit(new TransactionSource(TransactionType.DEBIT, transactionCount,
				accounts, clerks, Thread.MIN_PRIORITY + 1));

		// Create and start the clerk threads
		for (Clerk clerk : clerks) {
			threadPool.submit(clerk);
		}

		try {
			totalCredits = credits.get();
			totalDebits = debits.get();
		} catch (ExecutionException e) {
			System.out.println(e.getCause());
		} catch (InterruptedException e) {
			System.out.println(e);
		}

		// Orderly shutdown when all threads have ended
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(10L, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println(e);
		}

		if (threadPool.isTerminated())
			System.out.println("\nAll clerks have completed their tasks.\n");
		else {
			System.out.println("\nClerks still running - shutting down anyway.\n");
			threadPool.shutdownNow();
		}

		// Now output the results
		for (int i = 0; i < accounts.size(); ++i) {
			String result="Account Number:" + accounts.get(i).getAccountNumber() + "\n" + "Original balance    : $"
					+ initialBalance[i] + "\n" + "Total credits       : $" + totalCredits[i] + "\n"
					+ "Total debits        : $" + totalDebits[i] + "\n" + "Final balance       : $"
					+ accounts.get(i).getBalance() + "\n" + "Should be           : $"
					+ (initialBalance[i] + totalCredits[i] - totalDebits[i]) + "\n";
			System.out.println(result);
			mainLogger.info(result);
		}

		mainLogger.info("Main execution completed");
	}
}
