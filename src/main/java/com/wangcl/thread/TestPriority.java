package com.wangcl.thread;

/**
 * 线程优先级测试类
 * 设置优先级只是提高了线程被执行的概率，并不是说一定就是先执行优先级高的
 */
public class TestPriority {

	public static void main(String[] args) {
		MyPriority myPriority = new MyPriority();
		Thread t1 = new Thread(myPriority, "t1");
		Thread t2 = new Thread(myPriority, "t2");
		Thread t3 = new Thread(myPriority, "t3");
		Thread t4 = new Thread(myPriority, "t4");


		t2.setPriority(4);
		t3.setPriority(5);
		t4.setPriority(10);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}
}

class MyPriority implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "--》优先级--》" + Thread.currentThread().getPriority());
	}
}
