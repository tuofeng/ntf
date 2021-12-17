package com.alipay.test;

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
 * 栈：先进后出
 * 进：从数组第一个开始，一次往后添加元素
 * 出：从数组之后一个元素开始，去除元素并删除
 * 下标：记录数组最后一个元素的位置，进的插入下标+1的位置，出的时候从下标直接出站
 *
 * @author: shuangfeng_li 2021/10/27 16:23
 */
public class Stack<T> {

    private Object[] arr;
    private int index = -1;
    private ReentrantLock lock = new ReentrantLock();

    public Stack(int initCapacity) {
        arr = new Object[initCapacity];
    }

    public void in(T entry) {
        lock.lock();
        try {
            int nextIndex = index + 1;
            if (nextIndex >= arr.length) {
                //扩容
                Object[] newArr = Arrays.copyOf(arr, arr.length * 2);
                arr = newArr;
            }
            arr[nextIndex] = entry;
            index++;
        } finally {
            lock.unlock();
        }
    }

    public T out() {
        lock.lock();
        try {
            if (index < 0) {
                return null;
            }
            if (index >= arr.length) {
                return null;
            }
            T t = (T) arr[index];
            arr[index] = null;
            index--;
            return t;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Stack{" +
                "arr=" + Arrays.toString(arr) +
                ", index=" + index +
                ", lock=" + lock +
                '}';
    }
}
