package com.alipay.test.deadlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\ = /O
 * ___/`---'\____
 * .   ' \\| |// `.
 * / \\||| : |||// \
 * / _||||| -:- |||||- \
 * | | \\\ - /// | |
 * | \_| ''\---/'' | |
 * \ .-\__ `-` ___/-. /
 * ___`. .' /--.--\ `. . __
 * ."" '< `.___\_<|>_/___.' >'"".
 * | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * 解决方案2：同时获取两个账户的锁，才进行转账操作
 *
 * @author: shuangfeng_li 2021/10/28 9:53
 */
public class DeadLock_v2 {

    public static void main(String[] args) throws InterruptedException {
        LockedAccount lockedAccount = LockedAccount.getSingLockedAccount();
        final Account a1 = new Account(lockedAccount, "account1", 100);
        final Account a2 = new Account(lockedAccount, "account2", 100);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a1.transfer(a2, 2);
                    a2.transfer(a1, 1);
                }
            }).start();
        }

        countDownLatch.countDown();

        Thread.sleep(2000);

        System.out.println("account1:" + a1.getBalance());
        System.out.println("account2:" + a2.getBalance());
    }

    static class Account {

        LockedAccount lockedAccount;
        private String name;
        private int balance;

        public Account(LockedAccount lockedAccount, String name, int balance) {
            this.lockedAccount = lockedAccount;
            this.name = name;
            this.balance = balance;
        }

        public void transfer(Account targetAccount, int count) {
            try {
                while (!lockedAccount.tryLock(this, targetAccount)) {
                    synchronized (this){
                        this.wait();
                    }
                }
                synchronized (this) {
                    System.out.println(this.name + ": lock " + this.name);
                    synchronized (targetAccount) {
                        this.setBalance(balance - count);
                        targetAccount.setBalance(targetAccount.getBalance() + count);
                        System.out.println(this.name + ": lock " + targetAccount.name);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockedAccount.unLock(this, targetAccount);
                System.out.println(this.name + ": unLock ");
            }
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }

    static class LockedAccount {

        private List<Account> accounts = new ArrayList<>();

        public static LockedAccount lockedAccount;

        public static LockedAccount getSingLockedAccount() {
            if (lockedAccount == null) {
                synchronized (LockedAccount.class) {
                    if (lockedAccount == null) {
                        lockedAccount = new LockedAccount();
                    }
                }
            }
            return lockedAccount;
        }

        public synchronized boolean tryLock(Account from, Account to) {
            if (accounts.contains(from) || accounts.contains(to)) {
                return false;
            }
            accounts.add(from);
            accounts.add(to);
            return true;
        }

        public synchronized void unLock(Account from, Account to) {
            synchronized (from) {
                from.notify();
            }
            synchronized (to) {
                to.notify();
            }
            accounts.remove(from);
            accounts.remove(to);
        }
    }

}
