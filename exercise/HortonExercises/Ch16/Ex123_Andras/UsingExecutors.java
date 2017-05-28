/*
 * Ex1. Requirement:
 * Modify the UsingExecutors example in the chapter so that each transaction is a debit or a credit at random.
 * 
 * Ex2. Requirement:
 * Modify the result of the previous exercise to allow an arbitrary number of transaction source objects to be in 
 * effect
 * 
 * Ex3. Requirement
 * Extend the result of the previous exercise to incorporate two supervisors for two teams of clerks
 * where the supervisors each run in their own thread. 
 *
 * The supervisor threads should receive transactions from transaction sources 
 * and pass them to the clerks they supervise. 
 *
 * The supervisors' work should result in a variable time delay
 * in transferring transaction to the clerks of between 100 and 500 milliseconds.
 * */
package HortonExercises.Ch16.Ex123_Andras;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class UsingExecutors {

	public static void main(String[] args) {
		int[] initialBalance = { 500, 800 };                    // The initial account balances
		int accountCount=initialBalance.length;
		int[] totalCredits = new int[accountCount];             // For each account get a total of credits 
		int[] totalDebits = new int[accountCount];              // For each account get a total of debits 
		int transactionCount = 5;                               // Number of trnansaction to be done / transactions source objects
		int clerkCount = 2;

		// Create the account, the bank, and the clerks...
		Bank theBank = new Bank();                                       // Create a bank
		Vector<Clerk> clerks = new Vector<Clerk>();                      // Stores the clerk
		Vector<Account> accounts = new Vector<Account>();                // Stores the accounts

		for (int i = 0; i < clerkCount; ++i) {
			clerks.add(new Clerk(i + 1, theBank));                           // Create the clerks
		}

		for (int i = 0; i < initialBalance.length; ++i) {
			accounts.add(new Account(i + 1, initialBalance[i]));             // Create accounts
			totalCredits[i] = totalDebits[i] = 0;
		}

		ExecutorService threadPool = Executors.newCachedThreadPool();

		//Create the arbitrary number of transaction source objects
		int transactionSourcesCount=(int) (Math.random() * 10) + 1; //min1, max 10 Transaction source objects
		TransactionSource[] transactionSourcePool = new TransactionSource[transactionSourcesCount];
		for (int i = 0; i < transactionSourcesCount; i++) {
			transactionSourcePool[i]=new TransactionSource(transactionCount, accounts, clerks);
		}
		//start the threads for each source object
		//The result must be stored in a Collection because I can't initialize generic arrays
		ArrayList<Future<int[][]>> transactionSourceResults = new ArrayList<>();
		for (int i=0;i<transactionSourcesCount;i++){
			transactionSourceResults.add(threadPool.submit(transactionSourcePool[i]));
		}

		// Create and start the clerk threads
		for (Clerk clerk : clerks) {
			threadPool.submit(clerk);
		}
		try {
			// Get the total of credits/debits for each account for each transaction source
			for (int currentAccountIndex = 0; currentAccountIndex < accountCount; currentAccountIndex++) {
				//get the results for EACH transaction Source object for each account
				for (int x=0;x<transactionSourcesCount;x++){
				//get the actual ArrayList item that holds the Future<int[][]> object
				totalCredits[currentAccountIndex]+=transactionSourceResults.get(x).get()[currentAccountIndex][0];
				totalDebits[currentAccountIndex] += transactionSourceResults.get(x).get()[currentAccountIndex][1];
				}
			}
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

		if (threadPool.isTerminated()) {
			System.out.println("\nAll clerks have completed their tasks.\n");
		} else {
			System.out.println("\nClerks still running - shutting down anyway.\n");
			threadPool.shutdownNow();
		}

		// Now output the results
		System.out.println("Number of Transaction Source objects: "+transactionSourcesCount);
		for (int i = 0; i < accounts.size(); ++i) {
			System.out.println("Account Number:" + accounts.get(i).getAccountNumber() + "\n" + "Original balance    : $"
					+ initialBalance[i] + "\n" + "Total credits       : $" + totalCredits[i] + "\n"
					+ "Total debits        : $" + totalDebits[i] + "\n" + "Final balance       : $"
					+ accounts.get(i).getBalance() + "\n" + "Should be           : $"
					+ (initialBalance[i] + totalCredits[i] - totalDebits[i]) + "\n");
		}
	}
}
