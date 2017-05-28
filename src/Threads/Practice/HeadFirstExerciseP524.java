package Threads.Practice;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class HeadFirstExerciseP524 {

	public static void main(String[] args) {
		ThreadOne t1 = new ThreadOne();
		ThreadTwo t2 = new ThreadTwo();
		Thread one = new Thread(t1, "ThreadOne");
		Thread two = new Thread(t2, "ThreadTwo");
		one.start();
		two.start();
	}

}

class ThreadOne implements Runnable {

	Accum a = Accum.getAccum();

	@Override
	public void run() {
		for (int x = 0; x < 98; x++) {
			a.updateCounter(1000);
		}
		Accum.logger.info(String.format("ThreadOne run completed, x=98, counter:%d",a.getCount()));
		System.out.println("one " + a.getCount());
	}
}

class ThreadTwo implements Runnable {

	Accum a = Accum.getAccum();

	@Override
	public void run() {
		for (int x = 0; x < 99; x++) {
			a.updateCounter(1);
		}
		Accum.logger.info(String.format("ThreadTwo run completed, x=99, counter:%d",a.getCount()));
		System.out.println("two " + a.getCount());
	}

}

class Accum {

	private int counter = 0;
	private static Accum a = new Accum();
	public static Logger logger;
	{
		Handler fileHandler;
		try {
			fileHandler = new FileHandler("J:/Logs/HeadsFirstP524log/HeadsFirstP524.log");
			logger = Logger.getLogger(Accum.class.getName());
			logger.addHandler(fileHandler);
			fileHandler.setFormatter(new Logging.Formatters.ThreadFormatter_Sample());
			fileHandler.setLevel(Level.FINE);
			logger.setLevel(Level.FINE);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	private Accum() {
	}

	public static Accum getAccum() {
		return a;
	}

	public void updateCounter(int add) {
		logger.fine(String.format("%s, update counter called. current counter value:%d, to add:%d",
				Thread.currentThread().getName(), counter, add));
		try {
			logger.fine(String.format("%s goes to sleep", Thread.currentThread().getName()));
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.fine(String.format("%s wakes up", Thread.currentThread().getName()));
		logger.fine(String.format("%s, counter value after sleep, before update:%d, value to add:%d", Thread.currentThread().getName(),
				counter, add));
		counter += add;
		logger.fine(String.format("%s completes counter increment, new counter value=%d", Thread.currentThread().getName(), counter));
	}

	public int getCount() {
		return counter;
	}

}
