package Threads;

public class Deadlock_sample {

	ObjectsForDeadlock object1 = new ObjectsForDeadlock();
	ObjectsForDeadlock object2 = new ObjectsForDeadlock();

	// Create two threads with separate run() methods
	class threadOne implements Runnable {

		@Override
		public void run() {
			// In thread one, lock object 1
			synchronized (object1) {
				System.out.println("Thread One, object 1 locked.");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Call object2's method
				System.out.println("Thread One, waiting for object 2");
				object2.getIntMethod();
			}
		}
	}

	class threadTwo implements Runnable {

		@Override
		public void run() {
			synchronized (object2) {
				System.out.println("Thread Two, object 2 locked.");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Call object2's methodOne
				System.out.println("Thread Two, waiting for object 1");
				object1.getIntMethod();
			}
		}
	}

	public static void main(String[] args) {
		Deadlock_sample instance = new Deadlock_sample();
		Thread thread0 = new Thread(instance.new threadOne());
		Thread thread1 = new Thread(instance.new threadTwo());
		thread0.start();
		thread1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}

class ObjectsForDeadlock {

	int aValue;

	//remove the synchronized keyword to see it's effect
	synchronized public void getIntMethod() {
		System.out.println(aValue);
	}
}
