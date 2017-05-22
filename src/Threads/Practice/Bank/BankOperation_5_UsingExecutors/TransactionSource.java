package Threads.Practice.Bank.BankOperation_5_UsingExecutors;
// Generates transactions for clerks
// Returns an array of int with the total values of balances for each account. This is for the verification.

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;

public class TransactionSource implements Callable<int[]> {			//int[] will be returned when call() completed

  private TransactionType type;
  private int maxTrans;
  private Vector<Account> accounts;		//Vector is thread-safe. ArrayList is not. Not sure if simple array could have been used as well
  private Vector<Clerk> clerks;
  private int[] totals;

  //Constructor
  public TransactionSource(TransactionType type, int maxTrans, Vector<Account> accounts, Vector<Clerk> clerks) {
    this.type = type;
    this.maxTrans = maxTrans;
    this.accounts = accounts;
    this.clerks = clerks;
    totals = new int[accounts.size()];		//create an int to store the totals for each account
  }

  // The source of transactions
  public int[] call() {
    // Create transactions randomly distributed between the accounts
    Random rand = new Random();
    Transaction transaction = null;                                    // Stores a transaction
    int amount = 0;                                                    // Stores an amount of money
    int select = 0;                                                    // Selects an account
    boolean done = false;
    for(int i = 1 ; i <= maxTrans ; ++i) {								// Do ALL transactions required
      // Generate a random account index for  operation
      select = rand.nextInt(accounts.size());					//0(incl) - accounts size() exclusive. 
      amount = 50 + rand.nextInt(26);                                  // Generate amount of $50 to $75
      transaction = new Transaction(accounts.get(select),              // Account - 'select' can be used as index
                                    type,                              // Transaction type
                                    amount);                           //  of amount
      totals[select] += amount;                                        // Keep total (amount) for current account
      done = false;
      while(true) {											// repeat until one transaction is completed
        // Find a clerk to do the transaction
        for(Clerk clerk : clerks) {
          if(done = clerk.clerkAcceptsTransaction(transaction))		// doTransaction must return true if accepted by clerk
            break;
        } //end for
        if(done) { 		//break the while cycle only if transaction is done
          break;
        } 

        // No clerk was free so wait a while
        try {
          Thread.sleep(10);
        } catch(InterruptedException e) {
          System.out.println(" TransactionSource\n" + e);
          return totals;
        }
      } //end of while, still in for
      if(Thread.interrupted()) {
        System.out.println("Interrupt flag for "+ type + " transaction source set. Terminating.");
        return totals;
      } //end of if
    } //end of for
    return totals;
  } //end of call

}