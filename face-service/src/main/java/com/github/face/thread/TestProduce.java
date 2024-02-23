package com.github.face.thread;

/**
 * 测试： 生产者消费者模型--》管程法
 *
 * @author wcl
 */
public class TestProduce {
    public static void main(String[] args) {

        SynContainer synContainer = new SynContainer();
        new Productor(synContainer).start();
        new Consumer(synContainer).start();
    }
}

/**
 * 生产者
 */
class Productor extends Thread {

    SynContainer synContainer;

    public Productor(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            synContainer.push(new Chicken(i));
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {

    SynContainer synContainer;

    public Consumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            synContainer.pop();
        }
    }
}

/**
 * 产品
 */
class Chicken {
    /**
     * 产品编号
     */
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

/**
 * 同步缓冲区
 */
class SynContainer {
    /**
     * 容器大小
     */
    Chicken[] chickens = new Chicken[10];
    /**
     * 容器计数器
     */
    int count = 0;

    /**
     * 生产者生产产品
     */
    public synchronized void push(Chicken chicken) {
        if (count == 10) {
            // 通知消费者消费，生产等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 生产产品
        chickens[count] = chicken;
        count++;
        System.out.println("生产了-->第" + count + "只鸡");
        // 等待消费者消费
        this.notifyAll();
    }

    /**
     * 消费者消费产品
     */
    public synchronized Chicken pop() {
        // 判断能否消费
        if (count == 0) {
            // 等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 消费产品
        count--;
        Chicken chicken1 = chickens[count];
        System.out.println("消费了-->第" + (count) + "只鸡");
        // 吃完了，通知生产者生产
        this.notifyAll();
        return chicken1;

    }


}