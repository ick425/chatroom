package org.example.thread;

import lombok.Data;

/**
 * 不安全的取钱
 *
 * @author wangcl
 */
public class UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "买房基金");
        WithdrawMoney withdrawMoney = new WithdrawMoney(account, 50, "我");
        WithdrawMoney withdrawMoney1 = new WithdrawMoney(account, 80, "古力娜扎");
        new Thread(withdrawMoney).start();
        new Thread(withdrawMoney1).start();

    }
}

@Data
class Account {
    /**
     * 余额
     */
    int money;

    /**
     * 卡名
     */
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

/**
 * 取钱
 */
class WithdrawMoney extends Thread {
    /**
     * 账户
     */
    Account account;
    /**
     * 取了多少钱
     */
    int drawingMoney;

    /**
     * 现在手里的钱
     */
    int nowMoney;

    public WithdrawMoney(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        // 锁的对象就是需要变化的量
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println("【" + this.getName() + "】想取" + drawingMoney + "，余额不足，剩余" + account.money);
                return;
            }
            // sleep可以放大问题的发生性
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【" + account.name + "】余额为" + account.money);
            nowMoney = nowMoney + drawingMoney;
            System.out.println("【" + this.getName() + "】取了--》" + nowMoney);
            account.money = account.money - drawingMoney;
            System.out.println("【" + this.getName() + "】取完钱，" + account.name + "-->还剩" + account.money);
        }
    }
}