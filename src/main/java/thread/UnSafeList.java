package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全的集合
 * 多个线程同事操作一个对象，重复写入了
 *
 * @author wcl
 */
public class UnSafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        // 如果这里不加延时，还是会数量不一致
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印结果都不是10000：多个线程同事操作一个对象，重复写入了
        System.out.println(list.size());
    }
}
