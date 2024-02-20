package com.wangcl.thread;

/**
 * 观测线程状态
 */
public class TestState {

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			System.out.println("线程start..........");
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("线程end..........");
		});
		Thread.State state = thread.getState();
		System.out.println("线程状态1：" + state);
		thread.start();
		while (state != Thread.State.TERMINATED) {
			System.out.println("线程状态：" + thread.getState());
			state = thread.getState();
		}
	}
}