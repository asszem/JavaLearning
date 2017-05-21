package Threads.Practice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Implementing_Callable_Sample<V> implements Callable<V>{

	@Override
	public V call() throws Exception {
		String msg ="call() method completed in thread ";
		return  (V) msg;
	}
	
	public static void main(String[] args) {
		Implementing_Callable_Sample<String> instance = new Implementing_Callable_Sample<>();
//		Thread thread = new Thread(instance); //Error, 
		//Need to use ExecutorService
		ExecutorService threadPoolToUse = Executors.newCachedThreadPool();		//new thread created when needed
		ExecutorService limitedNumberPool = Executors.newFixedThreadPool(10); 	//Max 10 threads
	}

}
