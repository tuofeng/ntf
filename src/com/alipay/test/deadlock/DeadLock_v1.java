package com.alipay.test.deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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
 * 解决方案1：不使用ReentrantLock，因为synchronized获取不到资源会进入阻塞状态，无法释放其他资源了
 *
 * @author: shuangfeng_li 2021/10/28 9:53
 */
public class DeadLock_v1 {

    public static ReentrantLock obj1 = new ReentrantLock();
    public static ReentrantLock obj2 = new ReentrantLock();

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
                while (true){
                    System.out.println("Lock1 running");
                    if (obj1.tryLock(1, TimeUnit.MILLISECONDS)){
                        System.out.println("Lock1 lock obj1");
                        if (obj2.tryLock(1,TimeUnit.MILLISECONDS)){
                            System.out.println("Lock1 lock obj2");
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                obj1.unlock();
                obj2.unlock();
            }
        }
    }

    static class Lock2 implements Runnable {
        @Override
        public void run() {
            try {
                while (true){
                    System.out.println("Lock2 running");
                    if (obj2.tryLock(1, TimeUnit.MILLISECONDS)){
                        System.out.println("Lock2 lock obj2");
                        if (obj1.tryLock(1,TimeUnit.MILLISECONDS)){
                            System.out.println("Lock2 lock obj1");
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                obj1.unlock();
                obj2.unlock();
            }
        }
    }

}
