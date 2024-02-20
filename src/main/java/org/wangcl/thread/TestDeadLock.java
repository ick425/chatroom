package org.wangcl.thread;

/**
 * 死锁
 */
public class TestDeadLock {
	public static void main(String[] args) {
		MakeUp makeUp = new MakeUp(0, "灰姑娘");
		MakeUp makeUp1 = new MakeUp(1, "白雪公主");
		makeUp.start();
		makeUp1.start();
	}
}

/**
 * 口红
 */
class Lipstick {

}

/**
 * 镜子
 */
class Mirror {

}

/**
 * 化妆
 */
class MakeUp extends Thread {
	// 用static保证使用的资源只有一份

	static Lipstick lipstick = new Lipstick();
	static Mirror mirror = new Mirror();
	/**
	 * 选择镜子还是口红
	 */
	int choice;
	/**
	 * 人名
	 */
	String girlName;

	public MakeUp(int choice, String girlName) {
		this.choice = choice;
		this.girlName = girlName;
	}


	@Override
	public void run() {
		try {
			makeUp();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 化妆，互相持有对方的锁，就是获得对方的资源
	 */
	private void makeUp() throws InterruptedException {
		if (choice == 0) {
			synchronized (lipstick) {
				System.out.println(girlName + "--》获得口红的锁");
				Thread.sleep(1000);
			}
			synchronized (mirror) {
				System.out.println(girlName + "--》获得镜子的锁");
			}

		} else {
			synchronized (mirror) {
				System.out.println(girlName + "--》获得镜子的锁");
				Thread.sleep(2000);
			}
			synchronized (lipstick) {
				System.out.println(girlName + "--》获得口红的锁");
			}
		}
	}
}
