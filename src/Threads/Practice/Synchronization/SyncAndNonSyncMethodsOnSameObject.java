package Threads.Practice.Synchronization;


public class SyncAndNonSyncMethodsOnSameObject {

	public String value = "Start...";
	int repeat = 5;
	long sleepTime = 100;

	synchronized void sMethodA() {
		threadSleep(sleepTime);
		for (int i = 0; i < repeat; i++) {
			value += "\n" + tName() + "A" + i;
		}
	}

	synchronized void sMethodB() {
		threadSleep(sleepTime);
		for (int i = 0; i < repeat; i++) {
			value += "\n" + tName() + "B" + i;
		}
	}

	void nsMethodC() {
		for (int i = 0; i < repeat; i++) {
			threadSleep(sleepTime);
			value += "\n" + tName() + "C" + i;
		}
	}

	void nsMethodD() {
		for (int i = 0; i < repeat; i++) {
			threadSleep(sleepTime);
			value += "\n" + tName() + "D" + i;
		}
	}

	class ThreadRunner implements Runnable {

		@Override
		public void run() {
			// The methods operate on the same objects
			nsMethodC();
			nsMethodD();
			 sMethodA();
			 sMethodB();
			value += "\n" + Thread.currentThread().getName() + " completed.\n";
		}
	}

	class ThreadForResults implements Runnable {

		@Override
		public void run() {
			threadSleep(2000);
			System.out.println(value);

		}
	}

	public static void threadSleep(long milli) {
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String tName() {
		return Thread.currentThread().getName();
	}

	public static void main(String[] args) {
		System.out.println("Running synched (A, B) and non-synched (D, D) methods on same object");
		// Create two objects. The objects are not in a runnable class
		SyncAndNonSyncMethodsOnSameObject object1 = new SyncAndNonSyncMethodsOnSameObject();
		SyncAndNonSyncMethodsOnSameObject object2 = new SyncAndNonSyncMethodsOnSameObject ();
		// Create 3 threads
		Thread thread0 = new Thread(object1.new ThreadRunner(), "Thr0:");
		Thread thread1 = new Thread(object1.new ThreadRunner(), "Thr1:");
		Thread results = new Thread(object1.new ThreadForResults(), "Results");
		// Set the threads to User so that they continue even after main finished
		thread0.setDaemon(false);
		thread1.setDaemon(false);
		results.setDaemon(false);
		thread0.start();
		thread1.start();
		results.start();
	}

}
