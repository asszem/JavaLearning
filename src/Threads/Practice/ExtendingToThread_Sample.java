package Threads.Practice;

import java.util.Scanner;

public class ExtendingToThread_Sample extends Thread {

	String threadID;
	long sleepTime;
	boolean isDaemon;

	// Create a constructor for the objects that will be running in threads
	public ExtendingToThread_Sample(String threadID, long sleepTime, boolean isDaemon) {
		this.threadID = threadID;
		this.sleepTime = sleepTime;
		this.isDaemon = isDaemon;
		setDaemon(isDaemon);
	}

	// Override the Thread run method. This will be called with object.start() method
	@Override
	public void run() {
		System.out.println((isDaemon ? "Daemon" : "User") + " Thread " + threadID + " started.");
		try {
			// An infinite loop to produce output until either interrputed or daemon's parent died
			int counter = 0;
			while (true) {
				String threadIdDisplay="\n[" + threadID + "]";
				StringBuilder msg = new StringBuilder();
				msg.append(threadIdDisplay).append("before sleep, cycle ").append(counter);
				msg.append(threadIdDisplay).append("Interrupted? ").append(isInterrupted());
				msg.append(threadIdDisplay).append("Alive? ").append(isAlive());
				System.out.println(msg);
				sleep(sleepTime);
				msg = new StringBuilder();
				msg.append(threadIdDisplay).append("after sleep, cycle ").append(counter);
				System.out.println(msg);
				counter++;
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadID + " interrupted!");
		}
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Thread firstThread = new ExtendingToThread_Sample("Thread 1", 3000, true);
		Thread secondThread = new ExtendingToThread_Sample("Thread 2", 4000, true);
		Thread thirdThread = new ExtendingToThread_Sample("Thread 3", 5000, false);
		firstThread.start();
		secondThread.start();
		thirdThread.start();
		outer: while (true) {
			switch (keyboard.next()) {
			case "T1":
				System.out.println("Interrupting T1");
				firstThread.interrupt();
				break;
			case "T2":
				System.out.println("Interrupting T2");
				secondThread.interrupt();
				break;
			case "T3":
				System.out.println("Interrupting T3");
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
