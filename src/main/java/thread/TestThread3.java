package thread;

/**
 * 抢票
 * @author wcl
 */
public class TestThread3 implements Runnable {

    /**
     * 票数
     */
    private int ticketNum = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                System.out.println("=====》票卖光了，余量：" + ticketNum);
                break;
            }
            // 模拟延迟
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "==》抢到了第" + ticketNum-- + "张票");
        }
    }


    public static void main(String[] args) {
        TestThread3 thread = new TestThread3();
        new Thread(thread, "小明").start();
        new Thread(thread, "中明").start();
        new Thread(thread, "大明").start();
    }
}
