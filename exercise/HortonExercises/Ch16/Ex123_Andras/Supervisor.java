/*
 * The supervisor threads should receive transactions from transaction sources 
 * and pass them to the clerks they supervise. 
 *
 * The supervisors' work should result in a variable time delay
 * in transferring transaction to the clerks of between 100 and 500 milliseconds.
 * */
package HortonExercises.Ch16.Ex123_Andras;

import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class Supervisor implements Callable<Integer> {

	public String supervisorID;
	public Vector<Clerk> myClerks = new Vector<Clerk>();
	public Vector<Transaction> transactionsToAssign = new Vector<>();
	public static final Logger logger = Logger.getLogger(Supervisor.class.getName());

	// Constructor
	public Supervisor(String supervisorID) {
		this.supervisorID=supervisorID;
	}
	
	public void assignTransactionToSupervisor(Transaction transaction){
		logger.fine("Transaction "+transaction.getTransactionId()+" assigned to " + supervisorID);
	transactionsToAssign.add(transaction);
	}

	@Override
	public Integer call()  {
		return null;
	}

}
