package thread;

/**
 * 不安全的买票
 *
 * @author wcl
 */
public class UnSafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket, "小明").start();
        new Thread(buyTicket, "app").start();
        new Thread(buyTicket, "黄牛").start();
    }
}

class BuyTicket implements Runnable {
    /**
     * 总票数
     */
    private int num = 10;
    /**
     * 是否有票
     */
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 模拟买票
     * 增加synchronized关键字即可实现线程排队进入此方法
     */
    private synchronized void buy() throws InterruptedException {
        if (num <= 0) {
            // 卖光了
            System.out.println("卖光了--》" + num);
            flag = false;
            return;
        }
        // 加大问题的发现性
        Thread.sleep(200);
        // 买票
        System.out.println(Thread.currentThread().getName() + "-->拿到了第" + num-- + "张票");
    }

}