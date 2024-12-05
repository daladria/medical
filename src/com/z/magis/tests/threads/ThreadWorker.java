package com.z.magis.tests.threads;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ThreadWorker  implements Runnable {
	String id = "";
	BlockingQueue<String> blockingQueue =null;
	public  ThreadWorker(String id,BlockingQueue<String> blockingQueue) {
		this.id = id;
		this.blockingQueue = blockingQueue;
		//blockingQueue.add(id);
	}
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		int max =10;
		int min = 0;
		Random rand = new Random();

		// nextInt as provided by Random is exclusive of the top value so you need to add 1 
		blockingQueue.add(id);
		int randomNum = rand.nextInt((max - min) + 1) + min;
		try {
			Thread.sleep(randomNum*100);
			System.out.println("id:" + id + " time difference:" + ((System.currentTimeMillis()-start) /1000));
			//blockingQueue.add(id);
		} catch (Exception e) {
			
		}
	}

}
