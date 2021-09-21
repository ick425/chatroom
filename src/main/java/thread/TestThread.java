package thread;

/**
 * 多线程
 * 继承thread类，重写run方法，调用start方法
 *
 * @author wcl
 */
public class TestThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("子线程----" + i);
        }
    }

    public static void main(String[] args) {
        TestThread threadTest = new TestThread();
        threadTest.start();

        for (int i = 0; i < 20; i++) {
            System.out.println("main线程--" + i);
        }
    }
}