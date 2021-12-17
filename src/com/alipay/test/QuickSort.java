package com.alipay.test;

import java.util.Calendar;
import java.util.Random;

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
 * 快速：希尔：冒泡 = 1:3：100
 */
public class QuickSort {

    /**
     * 将数组第一个数看做是基数
     * 从数组左边开始遍历找到比基数大的数，从数组右边开始找比基数小的数，将两个数交换位置
     * 直到左右两边找的时候找到同一个下标，停止寻找，这时已经把所有小的数放在基数左边了，把所有比基数大的数放右边了
     * 将基数与遍历的中位数交换位置
     * 递归上述步骤
     */
    public static void sort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }

        int base = array[left];
        int ll = left;
        int rr = right;

        while (ll != rr) {
            // 先从右边开始往左找，直到找到比base值小的数
            while (array[rr] >= base && ll < rr) {
                rr--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while (array[ll] <= base && ll < rr) {
                ll++;
            }

            // 将上述找到的两个数交换位置，这样做能把小的数放左边，大的数放右边
            if (ll < rr) {
                int tmp = array[ll];
                array[ll] = array[rr];
                array[rr] = tmp;
            }
        }

        // 将基准数放到中间的位置，基准数默认取得第一个，所以将array[left] 与 array[ll]或者array[rr]交换即可
        int temp = array[left];
        array[left] = array[ll];
        array[ll] = temp;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        sort(array, left, ll - 1);
        sort(array, ll + 1, right);
    }

    public static void bubbleSort(int[] data) {

        int n = data.length;

        //1 2
        for (int i = 0; i < n - 1; i++) {    //排序的次数
            boolean flag = false;
            for (int j = 0; j < n - 1 - i; j++) {    //具体冒泡 n - 1 - i,6,5,4,3,2,1
                if (data[j] > data[j + 1]) {
                    int temp = data[j];    //用了第三个变量，不用第三个变量
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }

    }

    public static void shellSort(int[] a) {
        int n = a.length;

        for (int add = n / 2; add >= 1; add /= 2) {
            for (int i = add; i < n; i++) { // 为什么i要从1开始？
                int data = a[i];
                int j = i - add;
                for (; j >= 0; j -= add) {// 从尾到头 1+2+3+4+5+...+n=>
                    if (a[j] > data) {
                        a[j + add] = a[j]; // 数据往后移动
                    } else { // 因为前面已经是排好序的 那么找到一个比他小的就不用找了，因为前面的肯定更小
                        break; // O(1) 如果这个break执行的越多 那么我是不是效率就越高?
                    }
                }
                a[j + add] = data;
            }
        }

    }

    public static void main(String[] args) {


        int len = 100;

        int data[] = new int[len];
        int data2[] = new int[len];
        int data3[] = new int[len];
        for (int i = 0; i < len; i++) {
            int d = new Random().nextInt(1000000);
            data[i] = d;
            data2[i] = d;
            data3[i] = d;
        }

        {
            long t = Calendar.getInstance().getTime().getTime();
            sort(data, 0, data.length - 1);
            System.out.println("快速：" + (Calendar.getInstance().getTime().getTime() - t));
        }


        {
            long t = Calendar.getInstance().getTime().getTime();
            shellSort(data2);
            System.out.println("希尔：" + (Calendar.getInstance().getTime().getTime() - t));
        }

        {
            long t = Calendar.getInstance().getTime().getTime();
            bubbleSort(data3);
            System.out.println("冒泡：" + (Calendar.getInstance().getTime().getTime() - t));
        }

        for (int i = 0; i < len; i++) {
            if (data[i] != data2[i] && data[i] != data3[i]) {
                System.out.println("排序结果不一致");
                break;
            }
        }

    }
}
