package Threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Threads_Skeleton {

	public static void threadSleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Thread t1 = new ExtendingToThreadsSkeleton();
		t1.start();

		Thread t2 = new Thread(new RunnableClassSkeleton());
		t2.setDaemon(true);
		t2.start();

		ExecutorService threadPool = Executors.newCachedThreadPool();
		Future<String> t3 = threadPool.submit(new CallableClassSkeleton());
		Future<?> t4 = threadPool.submit(new RunnableClassSkeleton());
		try {
			t3.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}// end main
}// end class

class CallableClassSkeleton<V> implements Callable<V> {

	@Override
	public V call() throws Exception {
		return null;
	}

}

class RunnableClassSkeleton implements Runnable {

	@Override
	public void run() {
	}

}

class ExtendingToThreadsSkeleton extends Thread {
	public ExtendingToThreadsSkeleton() {
		setDaemon(true);
	}

	@Override
	public void run() {
	}
}