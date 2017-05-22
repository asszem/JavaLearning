package Threads;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Implementing_Callable_Sample<V> implements Callable<V>{

	@Override
	public V call() throws Exception {
		String msg ="call() method completed in thread "+Thread.currentThread().getName();
		return  (V) msg;
	}
	
	public static void main(String[] args) {
		Implementing_Callable_Sample<String> instance = new Implementing_Callable_Sample<>();
//		Thread thread = new Thread(instance); //Error, 
		//Need to use ExecutorService
		ExecutorService threadPool = Executors.newCachedThreadPool(); 	
		
		//Submit 20 threads
		ArrayList<Future<String>> threads=new ArrayList<>(); 
		for (int i=0;i<20;i++){
			threads.add(threadPool.submit(instance));
		}
		
		//Get the result msg for each thread
		for (Future<String> thread:threads){
			try {
				System.out.println(thread.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		//the task will be running until the threadpool is shut down
		threadPool.shutdown();
		
		//Terminating main
		System.out.println("Terminating main");
		return;
	}

}
