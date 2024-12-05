package com.z.magis.tests.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMain {

	public static void main(String[] args) {
		int threadCount=1000; 
		int maxThread = 10000;
		//BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(threadCount);
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(maxThread * 3);
		
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		try {

			for (int i= 0;i<maxThread ;i++) {
				ThreadWorker worker = new ThreadWorker(i + "", blockingQueue); 
				executor.execute(worker);
			}
		
			executor.shutdown();
		    while (!executor.isTerminated()) {
		    	Thread.sleep(50);
		    }
		    String tmp =  "";
		    while(blockingQueue.peek()!=null) {
		    	tmp =  blockingQueue.poll();
		    	System.out.println(tmp);
		    }
//		    for (int i= 0;i<15;i++) {
//		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Threads Finished....");
	}

}
