package org.wangcl.thread;

/**
 * 线程礼让测试类
 * 礼让不一定会成功
 * 正在执行的线程转为就绪状态，重新与其他线程竞争CPU
 *
 * @author wangcl
 */
public class TestYield {
	public static void main(String[] args) {
		MyYield myYield = new MyYield();
		new Thread(myYield, "A").start();
		new Thread(myYield, "B").start();
	}
}


class MyYield implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "线程开始执行...");
		// 线程礼让
		Thread.yield();
		System.out.println(Thread.currentThread().getName() + "线程执行完成");
	}
}