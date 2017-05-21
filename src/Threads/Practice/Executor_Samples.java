package Threads.Practice;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Executor_Samples {

	public static <V> V getFutureResults(Future<V> future) {
		V result;
		try {
			result = future.get();
			return result;
		} catch (CancellationException e) {
			System.out.println("Task was cancelled before a result was obtained.");
		} catch (InterruptedException e) {
			System.out.println("Current thread was interrupted while awaiting a result.");
		} catch (ExecutionException e) {
			System.out.println("Task threw an exception: ");
			Throwable cause = e.getCause();
			if (cause == null) {
				System.out.println("Cause could not be determined.");
			} else {
				System.out.println(cause);
			}
		}
		return null;
	}

	public static void shutdownAwaiteTermination(ExecutorService threadPool) {
		threadPool.shutdown();
		try {
			if (threadPool.awaitTermination(200, TimeUnit.MICROSECONDS)){
				System.out.println("All task completed");
			} else {
				System.out.println("Task failed to complete");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExecutorService threadPoolToUse = Executors.newCachedThreadPool();		// new thread created when needed
		ExecutorService limitedNumberPool = Executors.newFixedThreadPool(10); 	// Max 10 threads
		// Starting a runnable task
		Future<?> futureObjectRunnable = threadPoolToUse.submit(new RunnableClass());
		// Starting a callable task
		CallableClass<String> callableClassInstance = new CallableClass<>();
		Future<String> futureObjectCallable = threadPoolToUse.submit(callableClassInstance);
		// Cancel tasks using the future object
		System.out.println("isDone() before cancel=" + futureObjectRunnable.isDone());
		if (futureObjectRunnable.cancel(true)) {
			System.out.println("Task has been cancelled.");
		} else {
			System.out.println("Task could not be cancelled.");
		}
		System.out.println("isCancelled()=" + futureObjectRunnable.isCancelled());
		System.out.println("isDone()=" + futureObjectRunnable.isDone());
		String resultString = null;
		resultString = getFutureResults(futureObjectCallable);
		System.out.println(resultString);
		// Shutdown threadpool
		threadPoolToUse.shutdown();
		// Future<String> afterShutdown = threadPoolToUse.submit(callableClassInstance); //Error!
		shutdownAwaiteTermination(threadPoolToUse);
		List<Runnable> waitingForExecutionWhenShutdownWasIssued=threadPoolToUse.shutdownNow();
		System.out.println("isTerminated() = "+threadPoolToUse.isTerminated());
		System.out.println("isShutdown() = "+threadPoolToUse.isShutdown());
	}
}

class RunnableClass implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started.");
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}

class CallableClass<V> implements Callable<V> {

	@Override
	public V call() throws Exception {
		System.out.println(Thread.currentThread().getName() + " started.");
		return (V) "CallableClass call() completed";
	}

}
