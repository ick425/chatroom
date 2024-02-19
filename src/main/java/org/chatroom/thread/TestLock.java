package org.chatroom.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock，显式锁，只能锁代码块，性能更好
 * 优先使用顺序：lock》同步代码块（已经进入了方法体，分配了相应资源）》同步方法（在方法体之外）
 */
public class TestLock {

	public static void main(String[] args) {
		TestLock2 testLock2 = new TestLock2();
		new Thread(testLock2, "小明").start();
		new Thread(testLock2, "赵四").start();
		new Thread(testLock2, "黄牛").start();
	}
}

class TestLock2 implements Runnable {
	int ticketNum = 10;
	// 显示的锁
	private final ReentrantLock reentrantLock = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			try {
				reentrantLock.lock();
				if (ticketNum <= 0) {
					System.out.println("卖光了");
					break;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(ticketNum--);
			} finally {
				reentrantLock.unlock();
			}

		}
	}
}