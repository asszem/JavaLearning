package Threads.Practice.Bank.BankOperation_4;
public class Clerk implements Runnable {
  // Constructor
  public Clerk(Bank theBank) {
    this.theBank = theBank;                                            // Who the clerk works for
    inTray = null;                                                     // No transaction initially
  }

  // Receive a transaction
  public void assignTransaction(Transaction transaction) {
    inTray = transaction;
  }

  // The working clerk...
  public void run() {
    while(true) {                                                      // Non-stop work...
      while(inTray == null) {                                          // No transaction waiting?
        try {
          Thread.sleep(150);                                           // Then take a break...

        } catch(InterruptedException e) {
          System.out.println(e);
        }
      }

      theBank.executeTransaction(inTray);
      inTray = null;                                                   // In-tray is empty
    }
  }

  // Busy check
  public boolean isBusy() {
    return inTray != null;                                             // A full in-tray means busy!
  }

  private Bank theBank;                                                // The employer - an electronic marvel
  private Transaction inTray;                                          // The in-tray holding a transaction
}
