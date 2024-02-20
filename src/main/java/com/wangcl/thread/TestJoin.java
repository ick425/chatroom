package com.wangcl.thread;

/**
 * 线程插队
 *
 * @author wangcl
 */
public class TestJoin implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("vip线程执行中..." + i);
		}
	}

	public static void main(String[] args) throws InterruptedException {

		// 主线程和vip线程同时运行，到达指定条件时让vip线程插队执行
		TestJoin testJoin = new TestJoin();
		Thread thread = new Thread(testJoin);
		thread.start();


		for (int i = 0; i < 500; i++) {
			if (i == 300) {
				// main线程阻塞，vip线程插队
				thread.join();
			}
			System.out.println("main执行中..." + i);
		}
	}
}