package Threads.Practice.Bank.BankOperation_4;
// Define the bank

public class Bank {
  // Perform a transaction
  public void executeTransaction(Transaction transaction) {
    switch(transaction.getTransactionType()) {						
      case CREDIT:
        synchronized(transaction.getAccount()) { 			//Start synchronized block on Object ACCOUNT
        System.out.println("Start credit of " +
                transaction.getAccount() + " amount: " +
                transaction.getAmount());

          // Get current balance
          int balance = transaction.getAccount().getBalance();

          // Credits require a lot of checks...
          try {
            Thread.sleep(100);

          } catch(InterruptedException e) {
            System.out.println(e);
          }
          balance += transaction.getAmount();                          // Increment the balance
          transaction.getAccount().setBalance(balance);                // Restore account balance
          System.out.println("  End credit of " +
                  transaction.getAccount() + " amount: " +
                  transaction.getAmount());
          break;
        } //End synchronized block
      case DEBIT:
        synchronized(transaction.getAccount()) {
          System.out.println("Start debit of " +
                  transaction.getAccount() + " amount: " +
                  transaction.getAmount());

          // Get current balance
          int balance = transaction.getAccount().getBalance();

          // Debits require even more checks...
          try {
            Thread.sleep(150);

          } catch(InterruptedException e) {
            System.out.println(e);
          }
          balance -= transaction.getAmount();                          // Decrement the balance...
          transaction.getAccount().setBalance(balance);                // Restore account balance

          System.out.println("  End debit of " +
                  transaction.getAccount() + " amount: " +
                  transaction.getAmount());
          break;
        }

      default:                                                         // We should never get here
        System.out.println("Invalid transaction");
        System.exit(1);
    }
  }
}
