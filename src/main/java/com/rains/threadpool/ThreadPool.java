package com.rains.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程�?
 * @author Administrator
 *
 */
public class ThreadPool {
	public static void main(String[] args) {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 10, 10, 
				TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new CustomRejectedExecutionHandler());
		for (int i=0;i<50;i++) {
			ThreadPoolTask t = new ThreadPoolTask("thread-" + i);
			System.out.println("start execute:thread-" + i);
			pool.execute(t);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
