package thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSleep {
	/**
	 * 10s倒计时
	 */
	public static void tenDown() throws InterruptedException {
		int num = 10;
		do {
			// 睡眠1s
			Thread.sleep(500);
			System.out.println("倒计时--》" + num);
			num--;
		} while (num >= 0);
	}

	/**
	 * 每隔一秒打印一次当前时间
	 */
	public static void printCurrentTime() throws InterruptedException {
		while (true) {
			Date date = new Date(System.currentTimeMillis());
			// 打印当前时间
			System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
			Thread.sleep(1000);
		}
	}

	public static void main(String[] args) {
		try {
			printCurrentTime();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
