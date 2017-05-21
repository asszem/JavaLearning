package Threads.Practice.Bank.BankOperation_5_UsingExecutors;
// Generates transactions for clerks
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;

public class TransactionSource implements Callable<int[]> {

  public TransactionSource(TransactionType type, int maxTrans, Vector<Account> accounts, Vector<Clerk> clerks) {
    this.type = type;
    this.maxTrans = maxTrans;
    this.accounts = accounts;
    this.clerks = clerks;
    totals = new int[accounts.size()];
  }

  // The source of transactions
  public int[] call() {
    // Create transactions randomly distributed between the accounts
    Random rand = new Random();
    Transaction transaction = null;                                    // Stores a transaction
    int amount = 0;                                                    // Stores an amount of money
    int select = 0;                                                    // Selects an account
    boolean done = false;
    for(int i = 1 ; i <= maxTrans ; ++i) {
      // Generate a random account index for  operation
      select = rand.nextInt(accounts.size());
      amount = 50 + rand.nextInt(26);                                  // Generate amount of $50 to $75
      transaction = new Transaction(accounts.get(select),              // Account
                                    type,                              // Transaction type
                                    amount);                           //  of amount
      totals[select] += amount;                                        // Keep total tally for account
      done = false;
      while(true) {
        // Find a clerk to do the transaction
        for(Clerk clerk : clerks) {
          if(done = clerk.doTransaction(transaction))
            break;
        }
        if(done) {
          break;
        }

        // No clerk was free so wait a while
        try {
          Thread.sleep(10);
        } catch(InterruptedException e) {
          System.out.println(" TransactionSource\n" + e);
          return totals;
        }
      }
      if(Thread.interrupted()) {
        System.out.println("Interrupt flag for "+ type + " transaction source set. Terminating.");
        return totals;
      }
    }
    return totals;
  }

  private TransactionType type;
  private int maxTrans;
  private Vector<Account> accounts;
  private Vector<Clerk> clerks;
  private int[] totals;
}