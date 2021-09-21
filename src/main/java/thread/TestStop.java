package thread;

/**
 * 线程终止测试类（经过测试，flag改变之后会有子线程仍然在运行的情况下，线程不安全）
 * 1.建议线程正常停止--》利用次数
 * 2.设置标志位
 * 3.不要使用过时方法或者jdk不建议使用的方法
 *
 * @author wangcl
 */
public class TestStop implements Runnable {
	private boolean flag = true;

	@Override
	public void run() {
		int i = 0;
		while (flag) {
			System.out.println("线程运行中...." + i++);
		}
	}

	/**
	 * 模拟线程停止条件
	 */
	public void setStop() {
		this.flag = false;
	}

	public static void main(String[] args) {
		TestStop testStop = new TestStop();
		new Thread(testStop).start();

		for (int i = 0; i < 1000; i++) {
			System.out.println("main-->" + i);
			if (i == 700) {
				testStop.setStop();
				System.out.println("--------》线程该停止了");
			}
		}
	}
}
