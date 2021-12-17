package com.alipay.test.deadlock;

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
 * A给B转账，需要同时锁住AB两个账户，如果此时B也在给A转账
 * 死锁发生：线程1获得A锁，线程2获得B锁，线程1等待B锁，线程2等待A锁
 *
 * @author: shuangfeng_li 2021/10/28 9:53
 */
public class DeadLock {

    public static String obj1 = "obj1";
    public static String obj2 = "obj2";

    public static void main(String[] args) {
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }

    static class Lock1 implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Lock1 running");
                synchronized (DeadLock.obj1) {
                    System.out.println("Lock1 lock obj1");
                    Thread.sleep(5000);
                    synchronized (DeadLock.obj2) {
                        System.out.println("Lock1 lock obj2");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Lock2 implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Lock2 running");
                synchronized (DeadLock.obj2) {
                    System.out.println("Lock2 lock obj2");
                    Thread.sleep(5000);
                    synchronized (DeadLock.obj1) {
                        System.out.println("Lock2 lock obj1");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
