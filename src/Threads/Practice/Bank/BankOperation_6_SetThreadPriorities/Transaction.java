package Threads.Practice.Bank.BankOperation_6_SetThreadPriorities;

public class Transaction {

	private Account account;
	private int amount;
	private TransactionType type;
	private static String transactionID;		//An ID to identify C/D transactions

	// Constructor
	public Transaction(Account account, TransactionType type, int amount, int transactionNumber) {
		this.account = account;
		this.type = type;
		this.amount = amount;
		transactionID="ACC"+account.getAccountNumber()+""+type.toString()+"Num#"+(transactionNumber++); //Create a unique ID for each transaction
	}

	public Account getAccount() {
		return account;
	}

	public TransactionType getTransactionType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}
	
	public String getTransactionID(){
		return transactionID;
	}

	@Override
	public String toString() {
		return type + " Account: " + account + " balance: $" + amount;
	}

}
