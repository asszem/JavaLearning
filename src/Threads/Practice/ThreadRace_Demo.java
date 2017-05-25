package Threads.Practice;

public class ThreadRace_Demo implements Runnable {

	int ranCounter;

	// Constructor that starts the thread immediately
	public ThreadRace_Demo() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		threadRunner();
	}

	public void threadRunner() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " started!");
		while (ranCounter++ < 10) {
			// System.out.println(name + " score: " + ranCounter);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\t"+name + " finished!");
	}

	public static void mainThreadRunner() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " started!");
		int ranCounter = 0; // Make this a local variable for the static method
		while (ranCounter++ < 10) {
			// System.out.println(name + " score: " + ranCounter++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\t"+name + " finished!");
	}

	public static void main(String[] args) {
		ThreadRace_Demo threadZero = new ThreadRace_Demo();
		ThreadRace_Demo threadOne = new ThreadRace_Demo();
		mainThreadRunner(); // the main thread enters the race!

		try {
			Thread.sleep(100); // sleeping main() giving advantage to the first two threads
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ThreadRace_Demo threadTwo = new ThreadRace_Demo();
	}

}
