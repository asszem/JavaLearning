/*
 * Bank Ops 2 - Synchronized Bank DoTransaction method included in BankOps 1
 * Bank Ops 3 - Multiple account not included
 * 
 * */
package Threads.Practice.Bank.BankOperation_4;
import java.util.Random;

public class BankOperation4 {
  public static void main(String[] args) {
    int[] initialBalance = {500, 800};                                 // The initial account balances
    int[] totalCredits = new int[initialBalance.length];               // Two different cr totals
    int[] totalDebits = new int[initialBalance.length];                // Two different db totals
    int transactionCount = 20;                                         // Number of debits and of credits

    // Create the account, the bank, and the clerks...
    Bank theBank = new Bank();                                         // Create a bank
    Clerk clerk1 = new Clerk(theBank);                                 // Create the first clerk
    Clerk clerk2 = new Clerk(theBank);                                 // Create the second clerk

    // Create the accounts, and initialize total credits and debits
    Account[] accounts = new Account[initialBalance.length];
    for(int i = 0 ; i < initialBalance.length ; ++i) {
      accounts[i] = new Account(i+1, initialBalance[i]);               // Create accounts
      totalCredits[i] = totalDebits[i] = 0;
    }
    // Create the threads for the clerks as daemon, and start them off
    Thread clerk1Thread = new Thread(clerk1, "CreditThread");
    Thread clerk2Thread = new Thread(clerk2, "DebitThread");
    clerk1Thread.setDaemon(true);                                      // Set first as daemon
    clerk2Thread.setDaemon(true);                                      // Set second as daemon
    clerk1Thread.start();                                              // Start the first
    clerk2Thread.start();                                              // Start the second

    // Create transactions randomly distributed between the accounts
    Random rand = new Random();
    Transaction transaction;                                           // Stores a transaction
    int amount = 0;                                                    // Stores an amount of money
    int select = 0;                                                    // Selects an account
    for(int i = 1; i <= transactionCount; i++) {
      // Choose an account at random for credit operation
      select = rand.nextInt(accounts.length);
      amount = 50 + rand.nextInt(26);                                  // Generate amount of $50 to $75
      transaction = new Transaction(accounts[select],                  // Account
                              TransactionType.CREDIT,                  // Credit transaction
                                    amount);                           //  of amount
      totalCredits[select] += amount;                                  // Keep total credit tally

      // Wait until the first clerk is free
      while(clerk1.isBusy()) {
        try {
          Thread.sleep(25);                                            // Busy so try later
        } catch(InterruptedException e) {
          System.out.println(e);
        }
      }
      clerk1.assignTransaction(transaction);                               // Now do the credit

      // choose an account at random for debit operation
      select = rand.nextInt(accounts.length);
      amount = 30 + rand.nextInt(31);                                  // Generate amount of $30 to $60
      transaction = new Transaction(accounts[select],                  // Account
                              TransactionType.DEBIT,                   // Debit transaction
                                    amount);                           //  of amount
      totalDebits[select] += amount;                                   // Keep total debit tally

      // Wait until the second clerk is free
      while(clerk2.isBusy()) {
        try {
          Thread.sleep(25);                                            // Busy so try later
        } catch(InterruptedException e) {
          System.out.println(e);
        }
      }
      clerk2.assignTransaction(transaction);                               // Now do the debit
    }

    // Wait until both clerks are done
    while(clerk1.isBusy() || clerk2.isBusy()) {
      try {
        Thread.sleep(25);					//Sleeps the main thread, so that threads can run

      } catch(InterruptedException e) {
        System.out.println(e);
      }
    }

    // Now output the results
    for(int i = 0; i < accounts.length; ++i) {
      System.out.println("Account Number:"+accounts[i].getAccountNumber()+"\n"+
         "Original balance    : $" + initialBalance[i] + "\n" +
         "Total credits       : $" + totalCredits[i] + "\n" +
         "Total debits        : $" + totalDebits[i] + "\n" +
         "Final balance       : $" + accounts[i].getBalance() + "\n" +
         "Should be           : $" + (initialBalance[i]
                                   + totalCredits[i]
                                   - totalDebits[i]) + "\n");
    }
  }
}
