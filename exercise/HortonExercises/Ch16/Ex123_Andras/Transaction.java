package HortonExercises.Ch16.Ex123_Andras;

public class Transaction {

	private Account account;
	private int amount;
	private TransactionType type;
	private static String transactionID;
	private static int transactionNumber=0;

	// Constructor
	public Transaction(Account account, TransactionType type, int amount) {
		this.account = account;
		this.type = type;
		this.amount = amount;
		this.transactionID=String.format("TransID%d (Acc:%dType:%sAmount:%d",transactionNumber++,account.getAccountNumber(), type, amount);
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
		return type + " A//C: " + ": $" + amount;
	}

}
