package Threads.Practice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReturnFromThread_Sample {
	public static int randomNumber() {
		Random rnd = new Random();
		return rnd.nextInt(100);
	}

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Future<String> future = threadPool.submit(new CallableClass2());
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		Future<?> runnableThread = threadPool.submit(new runnableClass2());

	}

}

class CallableClass2<V> implements Callable<V> {

	@Override
	public V call() throws Exception {
		for (int i = 0; i < 100; i++) {
			int current = ReturnFromThread_Sample.randomNumber();
			System.out.println(i + "# " + current);
			if (current == 42) {
				return (V) "Return because of 42";
			}
		}
		return (V) "Return because end of call()";
	}

}

class runnableClass2 implements Runnable {

	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {
			int current = ReturnFromThread_Sample.randomNumber();
			System.out.println(i + "# " + current);
			if (current == 42) {
				System.out.println("Return because of return in for loop");
				return; // Can not define return value, as run() is void, does not return anything
			}
		}
		System.out.println("Return because end of method (no return value)");
		return;
	}

}
