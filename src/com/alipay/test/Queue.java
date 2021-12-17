package com.alipay.test;

import java.lang.reflect.Array;
import java.util.Arrays;
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
 * 队列：先进先出
 * 进：从数组第一个开始往后，依次插入元素，用一个下标记录插入位置
 * 出：从数组第一个元素开始往后，依次取出原始，用一个下标记录删除的位置
 * @author: shuangfeng_li 2021/10/27 14:49
 */
public class Queue<T> {

    private Object[] arr;
    private int inIndex = -1;
    private int outIndex = -1;
    private ReentrantLock lock = new ReentrantLock();

    public Queue(int initCapacity) {
        this.arr = new Object[initCapacity];
    }

    public void in(T entry) {
        lock.lock();
        try {
            int nextInIndex = inIndex + 1;
            if (nextInIndex >= arr.length) {
                //扩容
                int newArrLength = arr.length * 2;
                Object[] newArr = Arrays.copyOf(arr, newArrLength);
                arr = newArr;
            }
            arr[nextInIndex] = entry;
            inIndex++;
        } finally {
            lock.unlock();
        }
    }

    public T out() {
        lock.lock();
        try {
            int nextOutIndex = outIndex + 1;
            if (nextOutIndex >= arr.length) {
                //越界获取
                return null;
            }
            if (nextOutIndex > inIndex) {
                return null;//当前下标没有数据
            }
            T t = (T) arr[nextOutIndex];
            arr[nextOutIndex] = null;
            outIndex++;
            return t;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Queue{" +
                "arr=" + Arrays.toString(arr) +
                ", inIndex=" + inIndex +
                ", outIndex=" + outIndex +
                '}';
    }
}
