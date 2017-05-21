package Threads;

import java.util.Scanner;

public class Implementing_Runnable_Sample implements Runnable {

	String threadID;
	String threadName;
	long sleepTime;
	boolean isThisADaemon;
	boolean isThisInterrupted;
	boolean isThisAlive;

	// Constructor
	public Implementing_Runnable_Sample(String threadID, long sleepTime) {
		this.threadID = threadID;
		this.sleepTime = sleepTime;
	}

	@Override
	public void run() {
		System.out.println((isThisADaemon ? "Daemon" : "User") + " Thread " + threadID + " started.");
		try {
			// An infinite loop to produce output until either interrputed or daemon's parent died
			int counter = 0;
			while (true) {
				// Displays the thread info and increments the thread name with one
				displayCurrentThreadInfo(counter++);
				/*
				 * String threadIdDisplay = "\n[" + threadID + " name: "+threadName+"]"; StringBuilder msg = new StringBuilder();
				 * msg.append(threadIdDisplay).append("before sleep (").append(sleepTime).append("ms), cycle ") .append(counter);
				 * msg.append(threadIdDisplay).append("Interrupted? ").append(isThisInterrupted); msg.append(threadIdDisplay).append("Alive? ").append(isThisAlive); System.out.println(msg);
				 */
				Thread.sleep(sleepTime);
				/*
				 * msg = new StringBuilder(); msg.append(threadIdDisplay).append("after sleep, cycle ").append(counter); System.out.println(msg); counter++;
				 */
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadID + " interrupted!");
		}
	}

	public void updateThreadInfo(Thread threadName) {
		isThisInterrupted = threadName.isInterrupted();
		isThisAlive = threadName.isAlive();
		isThisADaemon = threadName.isDaemon();
		threadName.setName("Rnd " + ((int) (Math.random() * 10)));
		this.threadName = threadName.getName();

	}

	public void displayCurrentThreadInfo(int threadNameCounter) {
		boolean isInterrupted = Thread.currentThread().isInterrupted();
		boolean isAlive = Thread.currentThread().isAlive();
		boolean isDaemon = Thread.currentThread().isDaemon();
		String threadStatus = Thread.currentThread().getState().toString();
		String threadName = Thread.currentThread().getName();
		System.out.printf(
				"Current thread%n\tName: [%s]%n\tState: [%s]%n\tCounter: [%d]%n\tInterrupted: [%b]%n\tAlive[%b]%n\tDaemon: [%b]%n",
				threadName, threadStatus, threadNameCounter, isInterrupted, isAlive, isDaemon);
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		// Instatiating objects that will be the arguments of Thread creation
		Implementing_Runnable_Sample firstThreadIRS = new Implementing_Runnable_Sample("ID0", 500);
		Implementing_Runnable_Sample secondThreadIRS = new Implementing_Runnable_Sample("ID1", 500);
		Implementing_Runnable_Sample thirdThreadIRS = new Implementing_Runnable_Sample("ID2", 500);
		// Creating threads with arguments!
		Thread firstThread = new Thread(firstThreadIRS);
		Thread secondThread = new Thread(secondThreadIRS);
		Thread thirdThread = new Thread(thirdThreadIRS, "Thread Three");

		// Setting daemons
		firstThread.setDaemon(true);
		secondThread.setDaemon(true);
		thirdThread.setDaemon(true);

		// Starting threads
		firstThread.start();
		secondThread.start();
		thirdThread.start();

		outer: while (true) {
			// Update Thread info
//			firstThreadIRS.updateThreadInfo(firstThread);
//			secondThreadIRS.updateThreadInfo(secondThread);
//			thirdThreadIRS.updateThreadInfo(thirdThread);
			switch (keyboard.next()) {
			case "T1":
				System.out.println("Interrupting T0");
				firstThread.interrupt();
				break;
			case "T2":
				System.out.println("Interrupting T1");
				secondThread.interrupt();
				break;
			case "T3":
				System.out.println("Interrupting T2");
				thirdThread.interrupt();
				break;
			case "Stop":
				break outer;
			}
		}
		System.out.println("Stopping main");
		return;
	}

}
