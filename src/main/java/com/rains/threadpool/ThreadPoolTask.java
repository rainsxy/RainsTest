package com.rains.threadpool;

public class ThreadPoolTask implements Runnable{
	private String name;
	
	public ThreadPoolTask(){
		
	}
	public ThreadPoolTask(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
//		System.out.println("start sleep:" + name);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("sleep finish:" + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
