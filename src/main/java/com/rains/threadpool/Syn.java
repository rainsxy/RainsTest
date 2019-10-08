package com.rains.threadpool;

public class Syn {
	
	private static int count;

	public static void main(String[] args) {
		add();
		System.out.println(count);
	}
	public synchronized static void add() {
		count++;
	}
}
