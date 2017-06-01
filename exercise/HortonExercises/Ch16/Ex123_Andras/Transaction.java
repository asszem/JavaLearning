package HortonExercises.Ch16.Ex123_Andras;

import java.util.logging.Logger;

public class Transaction {

	private Account account;
	private int amount;
	private TransactionType type;
	private static String transactionID;
	private static int transactionNumber=0;
	public static final Logger logger=Logger.getLogger(Transaction.class.getName());

	// Constructor
	private Transaction(Account account, TransactionType type, int amount) {
		this.account = account;
		this.type = type;
		this.amount = amount;
		Transaction.transactionID=String.format("TransID%d [Acc%d::%s::$%d]",transactionNumber++,account.getAccountNumber(), type, amount);
		logger.info("Transaction created. ID: " + transactionID);
	}
	
	//Synchronized Factory method to make sure transaction ID is unique for each transaction in every thread 
	synchronized static public Transaction createTransaction(Account account, TransactionType type, int amount){
		return new Transaction(account, type, amount);
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

	public String getTransactionId() {
		return transactionID;
	}

	@Override
	public String toString() {
		return transactionID;
	}

}
