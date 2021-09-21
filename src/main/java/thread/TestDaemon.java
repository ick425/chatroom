package thread;

/**
 * 守护线程
 * 线程分为用户线程和守护线程
 * 虚拟机必须确保用户线程执行完毕
 * 虚拟机不用等待守护线程执行完毕
 * 如：后台记录日志，监控内存，垃圾回收等
 */
public class TestDaemon {

	public static void main(String[] args) {
		God god = new God();
		You you = new You();

		Thread thread = new Thread(god);
		// 设置为守护线程，默认false
		thread.setDaemon(true);
		thread.start();
		new Thread(you).start();
	}
}


class God implements Runnable {
	@Override
	public void run() {
		while (true) {
			System.out.println("玉皇大帝保佑我");
		}
	}
}


class You implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i <= 30000; i++) {
			System.out.println("我还活着，真好--》第" + i + "天");
		}
		System.out.println("玉皇大帝不管我了...");
	}
}
